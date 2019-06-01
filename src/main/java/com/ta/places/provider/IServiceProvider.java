package com.ta.places.provider;

import com.ta.places.model.PlaceException;
import com.ta.places.model.SearchResult;

import java.util.List;

public interface IServiceProvider {
        public List<SearchResult> searchPlaces(String query, String catgory) throws PlaceException;
        public  boolean isActive() throws PlaceException;
        public  String getName();

}
