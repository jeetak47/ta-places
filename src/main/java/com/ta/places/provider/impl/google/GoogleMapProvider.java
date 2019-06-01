package com.ta.places.provider.impl.google;

import com.ta.places.model.PlaceException;
import com.ta.places.model.SearchResult;
import com.ta.places.provider.IServiceProvider;
import com.ta.places.provider.impl.foursquar.model.Response;
import com.ta.places.provider.impl.google.model.GoogleSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleMapProvider implements IServiceProvider {

    @Value( "${google.active}" )
    private boolean active;

    @Value( "${google.active}" )
    private String apiKey;

    @Value( "${google.url}" )
    private String url;

    @Override
    public List<SearchResult> searchPlaces(String query, String catgory) throws PlaceException {
        List<SearchResult> result = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String fsurl=String.format(url,apiKey,query);
        ResponseEntity<GoogleSearchResponse> response
                = restTemplate.getForEntity(fsurl, GoogleSearchResponse.class);

        SearchResult sr= new SearchResult();
        sr.setAddress("google");
        result.add(sr);


        return result;
    }

    @Override
    public boolean isActive() throws PlaceException {
        return active;
    }

    @Override
    public String getName() {
        return "Google";
    }
}
