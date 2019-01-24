package com.sherhan.testArtists.repository;

import com.sherhan.testArtists.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
