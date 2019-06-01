package com.ta.places.provider.impl.foursquar;

import com.ta.places.model.PlaceException;
import com.ta.places.model.SearchResult;
import com.ta.places.provider.IServiceProvider;
import com.ta.places.provider.impl.foursquar.model.Response;
import com.ta.places.provider.impl.foursquar.model.Venue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FourSquareProvider implements IServiceProvider {

    Logger logger = LoggerFactory.getLogger(FourSquareProvider.class);
    @Value( "${foursquare.active}" )
    private boolean active;

    @Value( "${foursquare.client_secret}" )
    private String clientSecret;

    @Value( "${foursquare.client_id}" )
    private String clientId;

    @Value( "${foursquare.url}" )
    private String url;


    @Override
    public List<SearchResult> searchPlaces(String query, String catgory) throws PlaceException {
        List<SearchResult> result = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String fsurl=String.format(url,clientId,clientSecret,query);
        ResponseEntity<Response> response = restTemplate.getForEntity(fsurl, Response.class);
       logger.error(fsurl);

        if(response.getStatusCode()== HttpStatus.OK){
            List<Venue> venues = response.getBody().getVenues();
            venues.stream().filter(e->
                e.getCategories().stream().filter(c->c.getShortName().contains(catgory)).count()>0
                ).map(e->{
                    SearchResult sr= new SearchResult();
                List<String> formattedAddress = e.getLocation().getFormattedAddress();
                sr.setAddress(formattedAddress.size()>0?formattedAddress.get(0):"");
                return sr;
            }).collect(Collectors.toList());
        }
        return result;
    }


    @Override
    public boolean isActive() throws PlaceException{
        return active;
    }

    @Override
    public String getName() {
        return "FourSquare";
    }
}
