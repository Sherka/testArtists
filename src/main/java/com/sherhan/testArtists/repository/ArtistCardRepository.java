package com.sherhan.testArtists.repository;

import com.sherhan.testArtists.entities.ArtistCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ArtistCardRepository extends JpaRepository<ArtistCard, Long> {
    Collection<ArtistCard> getByName (String name);
}
