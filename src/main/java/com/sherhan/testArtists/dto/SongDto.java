package com.sherhan.testArtists.dto;

public class SongDto {
    private String name;

    public SongDto() {
    }

    public SongDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
