package com.sherhan.testArtists.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "artist_card")
public class ArtistCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "city")
    private String city;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "artistCard")
    private Collection<AlbumCard> albumCardList = new ArrayList<>();

    public ArtistCard() {
    }

    public ArtistCard(Long id, String name, String groupName, String city, Collection<AlbumCard> albumCardList) {
        this.id = id;
        this.name = name;
        this.groupName = groupName;
        this.city = city;
        this.albumCardList = albumCardList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<AlbumCard> getAlbumCardList() {
        return albumCardList;
    }

    public void setAlbumCardList(Collection<AlbumCard> albumCardList) {
        this.albumCardList = albumCardList;
    }
}
