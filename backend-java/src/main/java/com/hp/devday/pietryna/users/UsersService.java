package com.hp.devday.pietryna.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersService {

    private UserRepository userRepository;
    private UserFactory userFactory;

    @Autowired
    public UsersService(final UserRepository userRepository, final UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public User get(final String email) {
        UserEntity entity = userRepository.findByEmail(email);
        return userFactory.createUser(entity);
    }

    public Iterable<User> getAll() {
        Iterable<UserEntity> entities = userRepository.findAll();
        return userFactory.createUserList(entities);
    }
}
