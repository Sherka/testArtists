package com.sherhan.testArtists.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_card_id")
    private AlbumCard albumCard;

    @Column(name = "name")
    private String name;

    public Song() {
    }

    public Song(Long id, AlbumCard albumCard, String name) {
        this.id = id;
        this.albumCard = albumCard;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AlbumCard getAlbumCard() {
        return albumCard;
    }

    public void setAlbumCard(AlbumCard albumCard) {
        this.albumCard = albumCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
