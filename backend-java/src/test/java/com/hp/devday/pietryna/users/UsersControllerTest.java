package com.hp.devday.pietryna.users;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsersControllerTest {

    @Mock
    private UsersService usersService;
    @Mock
    private UserFactory userFactory;
    @Mock
    private Principal principal;

    @InjectMocks
    private UsersController usersController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();

        Mockito.when(principal.getName()).thenReturn("user");

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setEmail("user");
        user.setFirstName("first");
        user.setLastName("last");
        users.add(user);

        Mockito.when(usersService.getAll()).thenReturn(users);
        Mockito.when(usersService.get("user")).thenReturn(user);
    }

    @Test
    public void testGetMe() throws Exception {
        mockMvc.perform(get("/api/users/me")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("user"))
                .andExpect(jsonPath("$.firstName").value("first"))
                .andExpect(jsonPath("$.lastName").value("last"));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/api/users")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("user"))
                .andExpect(jsonPath("$[0].firstName").value("first"))
                .andExpect(jsonPath("$[0].lastName").value("last"));
    }
}
