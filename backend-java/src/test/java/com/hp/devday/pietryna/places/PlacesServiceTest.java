package com.hp.devday.pietryna.places;

import com.hp.devday.pietryna.users.UserEntity;
import com.hp.devday.pietryna.users.UserFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;

public class PlacesServiceTest {

    private UserFactory userFactory = new UserFactory();
    @Spy
    private PlacesFactory placesFactory = new PlacesFactory(userFactory);
    @Mock
    private PlacesRepository placesRepository;
    @InjectMocks
    private PlacesService placesService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        PlaceEntity place = new PlaceEntity();
        place.setId(1);
        place.setEmail("email");
        place.setAddress("address");
        place.setDescription("desc");
        place.setName("name");
        place.setPhone("phone");
        CategoryEntity category = new CategoryEntity();
        category.setId(1);
        category.setName("category");
        place.setCategory(category);
        place.setLocation(new Location(1.0, 2.0));
        UserEntity addedBy = new UserEntity();
        addedBy.setId(1);
        place.setAddedBy(addedBy);
        List<PlaceEntity> places = new ArrayList<>();
        places.add(place);
        Mockito.when(placesRepository.findAll()).thenReturn(places);
    }

    @Test
    public void testGet() {
        Iterable<Place> places = placesService.getAll();
        Assert.assertNotNull(places);
        Assert.assertTrue(places.iterator().hasNext());
        Place place = places.iterator().next();
        Assert.assertSame(1, place.getId());
        Assert.assertSame("email", place.getEmail());
        Assert.assertSame("desc", place.getDescription());
        Assert.assertSame("address", place.getAddress());
        Assert.assertSame("phone", place.getPhone());
        Assert.assertSame("name", place.getName());
        Assert.assertSame("category", place.getCategory());
        Assert.assertNotNull(place.getLocation());
        Assert.assertTrue(1. == place.getLocation().getLat());
        Assert.assertTrue(2. == place.getLocation().getLng());
        Assert.assertNotNull(place.getAddedBy());
        Assert.assertSame(1, place.getAddedBy().getId());
    }
}
