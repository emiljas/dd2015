package com.hp.devday.pietryna.places;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlacesService {

    private static final Logger LOG = Logger.getLogger(PlacesService.class);

    private PlacesRepository placesRepository;
    private PlacesFactory placesFactory;

    @Autowired
    public PlacesService(final PlacesRepository placesRepository, final PlacesFactory placesFactory) {
        this.placesRepository = placesRepository;
        this.placesFactory = placesFactory;
    }

    public Iterable<Place> getAll() {
        Iterable<PlaceEntity> entities = placesRepository.findAll();
        return placesFactory.createPlacesList(entities);
    }

}
