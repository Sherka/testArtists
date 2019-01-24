package com.sherhan.testArtists.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sherhan.testArtists.entities.LastFmArtist;
import com.sherhan.testArtists.repository.LastFmArtistRepository;
import com.sherhan.testArtists.util.ParameterStringBuilder;
import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.catalina.util.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class LastFmApiService {

    @Value("${last-fm.api-url}")
    private String API_URL;

    @Value("${last-fm.api-key}")
    private String API_KEY;

    private final LastFmArtistRepository repository;

    @Autowired
    public LastFmApiService(LastFmArtistRepository repository) {
        this.repository = repository;
    }

    public Collection<LastFmArtist> searchArtist(String name) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            Map<String, String> params = new HashMap<>();
            params.put("method", "artist.search");
            params.put("artist", name);
            params.put("api_key", API_KEY);
            params.put("format", "json");

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(params));
            out.flush();
            out.close();

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return saveSearchResult(content.toString());
    }

    public Collection<LastFmArtist> saveSearchResult(String content) {
        try {
            final ObjectNode node = new ObjectMapper().readValue(content, ObjectNode.class);

            if(node.has("results")) {
                JsonNode artists = node.get("results").get("artistmatches").get("artist");
                Iterator<JsonNode> iter = artists.elements();
                while(iter.hasNext()) {
                    JsonNode item = iter.next();
                    LastFmArtist existed = repository.findByName(item.get("name").asText());
                    if(existed == null) {
                        repository.save(new LastFmArtist(null, item.get("name").asText(), item.get("url").asText()));
                    } else {
                        repository.save(new LastFmArtist(existed.getId(), item.get("name").asText(), item.get("url").asText()));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return repository.findAll();
    }

    public Collection<LastFmArtist> findAll() {
        return repository.findAll();
    }
}
