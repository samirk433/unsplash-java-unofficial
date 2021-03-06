package com.github.rye761.unsplash;

import com.github.scribejava.core.model.Verb;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Photo {
    private static final Unsplash INSTANCE = Unsplash.getInstance();
    private static final Gson GSON = Unsplash.GSON;
    
    public final String id;
    @SerializedName("created_at") public final String createdAt;
    public final int width;
    public final int height;
    public final String color;
    public final int likes;
    @SerializedName("liked_by_user") public final boolean likedByUser;
    public final Exif exif;
    public final User user;
    @SerializedName("current_user_collections") public final Collection[] currentUserCollections;
    public final Url urls;
    public final Link links;

    public Photo(String id,
            String createdAt,
            int width,
            int height,
            String color,
            int likes,
            boolean likedByUser,
            Exif exif,
            User user,
            Collection[] currentUserCollections,
            Url urls,
            Link links) {
        this.id = id;
        this.createdAt = createdAt;
        this.width = width;
        this.height = height;
        this.color = color;
        this.likes = likes;
        this.likedByUser = likedByUser;
        this.exif = exif;
        this.user = user;
        this.currentUserCollections = currentUserCollections;
        this.urls = urls;
        this.links = links;
    }
    
    public static Photo[] all(Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "photos", params);
        return GSON.fromJson(data, Photo[].class);
    }
    
    public static Photo[] all() {
        return all(null);
    }
    
    public static Photo[] curated(Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "photos/curated", params);
        return GSON.fromJson(data, Photo[].class);
    }
    
    public static Photo[] curated() {
        return curated(null);
    }
    
    public static Photo find(String id, Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "photos/" + id, params);
        return GSON.fromJson(data, Photo.class);
    }
    
    public static Photo find(String id) {
        return find(id, null);
    }
    
    public Photo find() {
        return find(this.id);
    }
    
    public Photo find(Map<String, String> params) {
        return find(this.id, params);
    }
    
    public static Photo random(Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "photos/random", params);
        return GSON.fromJson(data, Photo.class);
    }
    
    public static Photo random() {
        return random(null);
    }
    
    public static Photo[] search(Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "photos/search", params);
        return GSON.fromJson(data, Photo[].class);
    }
    
    public PhotoStats stats() {
        final String data = INSTANCE.request(Verb.GET, "photos/"
                + this.id + "/stats");
        return GSON.fromJson(data, PhotoStats.class);
    }
    
    public void like() {
        INSTANCE.request(Verb.POST, "photos/" + this.id + "/like");
    }
    
    public void unlike() {
        INSTANCE.request(Verb.DELETE, "photos/" + this.id + "/like");
    }
    
}
