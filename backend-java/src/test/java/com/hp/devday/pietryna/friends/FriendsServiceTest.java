package com.hp.devday.pietryna.friends;

import com.hp.devday.pietryna.users.User;
import com.hp.devday.pietryna.users.UserEntity;
import com.hp.devday.pietryna.users.UserFactory;
import com.hp.devday.pietryna.users.UserRepository;
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

public class FriendsServiceTest {

    @Spy
    private UserFactory userFactory = new UserFactory();
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private FriendsService friendsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        UserEntity friend = new UserEntity();
        friend.setId(1);
        friend.setEmail("Email");
        friend.setFirstName("First");
        friend.setLastName("Last");

        UserEntity friend2 = new UserEntity();
        friend2.setId(2);
        friend2.setEmail("Email2");
        friend2.setFirstName("First2");
        friend2.setLastName("Last2");

        UserEntity user = new UserEntity();
        List<UserEntity> friends = new ArrayList<>();
        friends.add(friend);
        user.setFriends(friends);
        Mockito.when(userRepository.findByEmail("e1")).thenReturn(user);
        Mockito.when(userRepository.findFriendsByEmail("e1")).thenReturn(friends);
        Mockito.when(userRepository.findOne(1)).thenReturn(friend);
        Mockito.when(userRepository.findOne(2)).thenReturn(friend2);
    }

    @Test
    public void testAdd() {
        User user = friendsService.add("e1", 2);
        Assert.assertNotNull(user);
        Assert.assertSame("Email2", user.getEmail());
        Assert.assertSame("First2", user.getFirstName());
        Assert.assertSame("Last2", user.getLastName());
        Assert.assertSame(2, user.getId());
    }

    @Test(expected = FriendshipExistsException.class)
    public void testAddAlreadyExistingFriendship() {
        User user = friendsService.add("e1", 1);
    }

    @Test
    public void testGet() {
        Iterable<User> users = friendsService.get("e1");
        Assert.assertNotNull(users);
        Assert.assertTrue(users.iterator().hasNext());
        User user = users.iterator().next();
        Assert.assertSame("Email", user.getEmail());
        Assert.assertSame("First", user.getFirstName());
        Assert.assertSame("Last", user.getLastName());
        Assert.assertSame(1, user.getId());
    }

    @Test
    public void testDelete() {
        friendsService.delete("e1", 1);
        List<User> friends = (List<User>) friendsService.get("e1");
        Assert.assertSame(0, friends.size());
    }
}
