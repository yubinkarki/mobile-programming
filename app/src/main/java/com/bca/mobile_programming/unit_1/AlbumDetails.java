package com.bca.mobile_programming.unit_1;

public class AlbumDetails {
    private final int image;
    private final String title;
    private final String description;

    public AlbumDetails(String n, String d, int i) {
        this.image = i;
        this.title = n;
        this.description = d;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
