package com.hp.devday.pietryna.places;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/places")
public class PlacesController {

    private static final Logger LOG = Logger.getLogger(PlacesController.class);

    private PlacesService placesService;

    @Autowired
    public PlacesController(final PlacesService placesService) {
        this.placesService = placesService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Place> get() {
        LOG.debug("Get all places");
        return placesService.getAll();
    }

}
