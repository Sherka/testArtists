package com.sherhan.testArtists.services;

import com.sherhan.testArtists.dto.AlbumCardDto;
import com.sherhan.testArtists.dto.ArtistCardDto;
import com.sherhan.testArtists.dto.SongDto;
import com.sherhan.testArtists.entities.AlbumCard;
import com.sherhan.testArtists.entities.ArtistCard;
import com.sherhan.testArtists.entities.Song;
import com.sherhan.testArtists.repository.ArtistCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ArtistCardService {

    private final ArtistCardRepository repository;

    @Autowired
    public ArtistCardService(ArtistCardRepository repository) {
        this.repository = repository;
    }

    public ArtistCardDto get (Long id) {
        return convertArtistToDto(repository.getOne(id), new ArtistCardDto());
    }

    public Collection<ArtistCardDto> get (String name) {
        Collection<ArtistCardDto> artistCardDtos = new ArrayList<>();
        for(ArtistCard entity: repository.getByName(name)) {
            artistCardDtos.add(convertArtistToDto(entity, new ArtistCardDto()));
        }
        return artistCardDtos;
    }

    public ArtistCardDto save (ArtistCard artistCard) {
        for(AlbumCard albumCard: artistCard.getAlbumCardList()) {
            for(Song song: albumCard.getSongs()) {
                song.setAlbumCard(albumCard);
            }
            albumCard.setArtistCard(artistCard);
        }
        ArtistCard savedCard = repository.save(artistCard);
        return convertArtistToDto(savedCard, new ArtistCardDto());
    }

    public ArtistCardDto update (ArtistCard artistCard, Long id) {
        for(AlbumCard albumCard: artistCard.getAlbumCardList()) {
            for(Song song: albumCard.getSongs()) {
                song.setAlbumCard(albumCard);
            }
            albumCard.setArtistCard(artistCard);
        }
        return convertArtistToDto(repository.save(artistCard), new ArtistCardDto());
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }

    public Collection<ArtistCardDto> findAll () {
        Collection<ArtistCardDto> artistCardDtos = new ArrayList<>();
        for(ArtistCard entity: repository.findAll()) {
            artistCardDtos.add(convertArtistToDto(entity, new ArtistCardDto()));
        }
        return artistCardDtos;
    }

    private ArtistCardDto convertArtistToDto (ArtistCard entity, ArtistCardDto dto) {
        dto.setGroupName(entity.getGroupName());
        dto.setName(entity.getName());
        dto.setCity(entity.getCity());
        dto.setAlbumCardDtos(new ArrayList<>());
        for(AlbumCard albumCard: entity.getAlbumCardList()) {
            dto.getAlbumCardDtos().add(convertAlbumToDto(albumCard, new AlbumCardDto()));
        }
        return dto;
    }

    private AlbumCardDto convertAlbumToDto (AlbumCard entity, AlbumCardDto dto) {
        dto.setName(entity.getName());
        dto.setSongDtos(new ArrayList<>());
        for(Song song: entity.getSongs()) {
            dto.getSongDtos().add(convertSongToDto(song, new SongDto()));
        }
        return dto;
    }

    private SongDto convertSongToDto (Song entity, SongDto dto) {
        dto.setName(entity.getName());
        return dto;
    }
}
