package com.hp.devday.pietryna.friends;

import com.hp.devday.pietryna.users.User;
import com.hp.devday.pietryna.users.UserEntity;
import com.hp.devday.pietryna.users.UserFactory;
import com.hp.devday.pietryna.users.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendsService {

    private static final Logger LOG = Logger.getLogger(FriendsService.class);

    private UserRepository userRepository;
    private UserFactory userFactory;

    @Autowired
    public FriendsService(final UserRepository userRepository, final UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public User add(final String email, final Integer friendId) {
        UserEntity friend = userRepository.findOne(friendId);
        UserEntity user = userRepository.findByEmail(email);
        if (user.getFriends().contains(friend)) {
            LOG.error("Friendship already exists");
            throw new FriendshipExistsException();
        }
        user.addFriend(friend);
        friend.addFriend(user);
        userRepository.save(user);
        userRepository.save(friend);
        return userFactory.createUser(friend);
    }

    public Iterable<User> get(final String email) {
        Iterable<UserEntity> entities = userRepository.findFriendsByEmail(email);
        return userFactory.createUserList(entities);
    }

    public void delete(final String email, final Integer friendId) {
        UserEntity friend = userRepository.findOne(friendId);
        UserEntity user = userRepository.findByEmail(email);
        user.removeFriend(friend);
        friend.removeFriend(user);
        userRepository.save(user);
        userRepository.save(friend);
    }
}
