package com.hp.devday.pietryna.places;

import com.hp.devday.pietryna.users.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlacesFactory {

    private UserFactory userFactory;

    @Autowired
    public PlacesFactory(final UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    public Place createPlace(final PlaceEntity placeEntity) {
        Place place = new Place();
        place.setId(placeEntity.getId());
        place.setName(placeEntity.getName());
        place.setAddress(placeEntity.getAddress());
        place.setEmail(placeEntity.getEmail());
        place.setDescription(placeEntity.getDescription());
        place.setPhone(placeEntity.getPhone());
        place.setAddedBy(userFactory.createUser(placeEntity.getAddedBy()));
        if (placeEntity.getCategory() != null) {
            place.setCategory(placeEntity.getCategory().getName());
        }
        place.setLocation(placeEntity.getLocation());
        return place;
    }

    public Iterable<Place> createPlacesList(final Iterable<PlaceEntity> entities) {
        List<Place> places = new ArrayList<>();
        for (PlaceEntity placeEntity : entities) {
            Place place = createPlace(placeEntity);
            places.add(place);
        }
        return places;
    }
}
