package com.hp.devday.pietryna.users;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserFactory {

    public User createUser(final UserEntity entity) {
        User user = null;
        if (entity != null) {
            user = new User();
            user.setId(entity.getId());
            user.setFirstName(entity.getFirstName());
            user.setLastName(entity.getLastName());
            user.setEmail(entity.getEmail());
        }
        return user;
    }

    public Iterable<User> createUserList(final Iterable<UserEntity> entities) {
        List<User> users = new ArrayList<>();
        for (UserEntity entity : entities) {
            users.add(createUser(entity));
        }
        return users;
    }
}
