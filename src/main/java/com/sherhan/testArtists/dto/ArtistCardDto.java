package com.sherhan.testArtists.dto;

import java.util.Collection;

public class ArtistCardDto {
    private String name;
    private String groupName;
    private String city;
    private Collection<AlbumCardDto> albumCardDtos;

    public ArtistCardDto() {
    }

    public ArtistCardDto(String name, String groupName, String city, Collection<AlbumCardDto> albumCardDtos) {
        this.name = name;
        this.groupName = groupName;
        this.city = city;
        this.albumCardDtos = albumCardDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Collection<AlbumCardDto> getAlbumCardDtos() {
        return albumCardDtos;
    }

    public void setAlbumCardDtos(Collection<AlbumCardDto> albumCardDtos) {
        this.albumCardDtos = albumCardDtos;
    }
}
