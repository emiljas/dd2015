package com.hp.devday.pietryna.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query("select u.friends from UserEntity u where u.email = ?1")
    List<UserEntity> findFriendsByEmail(String email);

    UserEntity findByEmail(String email);
}
