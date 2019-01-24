package com.sherhan.testArtists.controllers;

import com.sherhan.testArtists.dto.ArtistCardDto;
import com.sherhan.testArtists.entities.ArtistCard;
import com.sherhan.testArtists.services.ArtistCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(value = "artist-card")
public class ArtistCardController {
    private final ArtistCardService service;

    @Autowired
    public ArtistCardController(ArtistCardService service) {
        this.service = service;
    }

    @RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
    public String healthCheck () {
        return "App is Running";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Collection<ArtistCardDto> getCard (@PathVariable String id) {
        if(id.chars().allMatch(Character::isDigit)) {
            Collection<ArtistCardDto> result = new ArrayList<>();
            result.add(service.get(Long.parseLong(id)));
            return result;
        } else {
            return service.get(id);
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ArtistCardDto saveCard (@RequestBody ArtistCard card) {
        return service.save(card);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ArtistCardDto updateCard (@RequestBody ArtistCard card, @PathVariable Long id) {
        return service.update(card, id);
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public Collection<ArtistCardDto> findAllCards () {
        return service.findAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCard (@PathVariable Long id) {
        service.delete(id);
    }
}
