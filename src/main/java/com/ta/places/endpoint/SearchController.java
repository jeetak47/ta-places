package com.ta.places.endpoint;

import com.ta.places.model.PlaceException;
import com.ta.places.model.PlaceResponse;
import com.ta.places.model.SearchResult;
import com.ta.places.services.IPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {


    IPlacesService placesService;

    @Autowired
    public void setPlacesService(IPlacesService placesService) {
        this.placesService = placesService;
    }

    @RequestMapping("/search")
    public PlaceResponse greeting(@RequestParam(value="query", defaultValue="") String query, @RequestParam(value="category", defaultValue="") String category) {
        Map<String,String> param = new HashMap<>();
        PlaceResponse response = new PlaceResponse();
        param.put("query",query);
        param.put("type",category);
        try {
            List<SearchResult> places = placesService.findPlaces(param);
            response.setData(places);
        } catch (PlaceException e) {
             response.setError(e.getErrorMessage());
        }
        return response;

    }
}