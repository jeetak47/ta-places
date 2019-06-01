package com.ta.places.services;

import com.ta.places.model.PlaceException;
import com.ta.places.model.SearchResult;

import java.util.List;
import java.util.Map;

public interface IPlacesService {
   List<SearchResult> findPlaces(Map param) throws PlaceException;
}
