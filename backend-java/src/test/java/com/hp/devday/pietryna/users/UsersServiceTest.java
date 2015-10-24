package com.hp.devday.pietryna.users;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

public class UsersServiceTest {

    @Spy
    private UserFactory userFactory = new UserFactory();
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UsersService usersService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        UserEntity user = new UserEntity();
        user.setId(1);
        user.setEmail("email");
        List<UserEntity> users = new ArrayList<>();
        users.add(user);
        Mockito.when(userRepository.findByEmail("e1")).thenReturn(user);
        Mockito.when(userRepository.findAll()).thenReturn(users);
    }

    @Test
    public void testGet() {
        User user = usersService.get("e1");
        Assert.assertNotNull(user);
        Assert.assertSame(1, user.getId());
        Assert.assertSame("email", user.getEmail());
    }

    @Test
    public void testGetAll() {
        Iterable<User> users = usersService.getAll();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.iterator().hasNext());
        User user = users.iterator().next();
        Assert.assertSame(1, user.getId());
        Assert.assertSame("email", user.getEmail());
    }
}
