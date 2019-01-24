package com.sherhan.testArtists.dto;

import java.util.Collection;

public class AlbumCardDto {
    private String name;
    private Collection<SongDto> songDtos;

    public AlbumCardDto() {
    }

    public AlbumCardDto(String name, Collection<SongDto> songDtos) {
        this.name = name;
        this.songDtos = songDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<SongDto> getSongDtos() {
        return songDtos;
    }

    public void setSongDtos(Collection<SongDto> songDtos) {
        this.songDtos = songDtos;
    }
}
