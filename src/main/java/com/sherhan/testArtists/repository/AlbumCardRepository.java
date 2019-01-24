package com.sherhan.testArtists.repository;

import com.sherhan.testArtists.entities.AlbumCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumCardRepository extends JpaRepository<AlbumCard, Long> {
}
