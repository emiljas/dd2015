package com.hp.devday.pietryna.users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    private String email;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "person_friendship",
               joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
               inverseJoinColumns = {@JoinColumn(name = "friend_id", referencedColumnName = "user_id")})
    private List<UserEntity> friends;

    public UserEntity() {
        friends = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public List<UserEntity> getFriends() {
        return Collections.unmodifiableList(friends);
    }

    public void setFriends(final List<UserEntity> friends) {
        this.friends = friends;
    }

    public void addFriend(final UserEntity friend) {
        friends.add(friend);
    }

    public void removeFriend(final UserEntity friend) {
        friends.remove(friend);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
