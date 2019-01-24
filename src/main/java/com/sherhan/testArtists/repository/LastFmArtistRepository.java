package com.sherhan.testArtists.repository;

import com.sherhan.testArtists.entities.LastFmArtist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastFmArtistRepository extends JpaRepository<LastFmArtist, Long> {
    LastFmArtist findByName(String name);
}
