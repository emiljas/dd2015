package com.hp.devday.pietryna.friends;

import com.hp.devday.pietryna.users.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FriendsControllerTest {

    @Mock
    private FriendsService friendsService;
    @Mock
    private Principal principal;
    @InjectMocks
    private FriendsController friendsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(friendsController)
                                 .build();

        Mockito.when(principal.getName()).thenReturn("user");

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setEmail("user");
        user.setFirstName("first");
        user.setLastName("last");
        users.add(user);

        Mockito.when(friendsService.get("user")).thenReturn(users);
        Mockito.when(friendsService.add("user", 1)).thenReturn(user);
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/api/friends")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("user"))
                .andExpect(jsonPath("$[0].firstName").value("first"))
                .andExpect(jsonPath("$[0].lastName").value("last"));
    }

    @Test
    public void testPost() throws Exception {
        mockMvc.perform(post("/api/friends")
                .principal(principal)
                .content("{\"id\":1}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("user"))
                .andExpect(jsonPath("$.firstName").value("first"))
                .andExpect(jsonPath("$.lastName").value("last"));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/friends/1")
                .principal(principal))
                .andExpect(status().isNoContent());
    }
}
