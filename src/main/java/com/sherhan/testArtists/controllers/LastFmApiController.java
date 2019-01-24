package com.sherhan.testArtists.controllers;

import com.sherhan.testArtists.entities.LastFmArtist;
import com.sherhan.testArtists.services.LastFmApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "last-fm")
public class LastFmApiController {
    private final LastFmApiService lastFmApiService;

    @Autowired
    public LastFmApiController(LastFmApiService lastFmApiService) {
        this.lastFmApiService = lastFmApiService;
    }

    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
    public Collection<LastFmArtist> searchArtistAtLastFm (@PathVariable String name) {
        return lastFmApiService.searchArtist(name);
    }

    @RequestMapping(value = "/find-all")
    public Collection<LastFmArtist> findAllArtists() {
        return lastFmApiService.findAll();
    }
}
