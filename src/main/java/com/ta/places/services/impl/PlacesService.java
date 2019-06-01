package com.ta.places.services.impl;

import com.ta.places.model.PlaceException;
import com.ta.places.model.SearchResult;
import com.ta.places.provider.IServiceProvider;
import com.ta.places.services.IPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PlacesService implements IPlacesService {


    List<IServiceProvider>  serviceProviders;

    @Autowired
    public void setServiceProviders(List<IServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    @Override
    public List<SearchResult> findPlaces(Map param) throws PlaceException {
        List<SearchResult> results = new ArrayList<>();
        String query = (String) param.get("query");
        String type = (String) param.get("type");
        for (IServiceProvider provider :serviceProviders) {
           if(provider.isActive()){
               results.addAll(provider.searchPlaces(query,type));
           }

        }
        return results;
    }
}
