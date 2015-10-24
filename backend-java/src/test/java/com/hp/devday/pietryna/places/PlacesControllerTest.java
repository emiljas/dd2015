package com.hp.devday.pietryna.places;

import com.hp.devday.pietryna.users.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlacesControllerTest {

    @Mock
    private PlacesService placesService;

    @InjectMocks
    private PlacesController placesController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(placesController).build();

        List<Place> places = new ArrayList<>();
        Place place = new Place();
        place.setId(1);
        place.setName("Place");
        place.setCategory("Category");
        place.setPhone("Phone");
        place.setEmail("Email");
        place.setDescription("Desc");
        place.setAddress("Address");
        Location location = new Location(1.1,2.2);
        place.setLocation(location);
        User user = new User();
        user.setId(1);
        user.setFirstName("First");
        user.setLastName("Last");
        user.setEmail("Email");
        place.setAddedBy(user);
        places.add(place);
        Mockito.when(placesService.getAll()).thenReturn(places);
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/api/places"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Place"))
                .andExpect(jsonPath("$[0].category").value("Category"))
                .andExpect(jsonPath("$[0].description").value("Desc"))
                .andExpect(jsonPath("$[0].address").value("Address"))
                .andExpect(jsonPath("$[0].email").value("Email"))
                .andExpect(jsonPath("$[0].phone").value("Phone"))
                .andExpect(jsonPath("$[0].location.lat").value(1.1))
                .andExpect(jsonPath("$[0].location.lng").value(2.2))
                .andExpect(jsonPath("$[0].addedBy.id").value(1))
                .andExpect(jsonPath("$[0].addedBy.firstName").value("First"))
                .andExpect(jsonPath("$[0].addedBy.lastName").value("Last"))
                .andExpect(jsonPath("$[0].addedBy.email").value("Email"));
    }
}
