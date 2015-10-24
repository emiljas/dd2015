package com.hp.devday.pietryna.places;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hp.devday.pietryna.users.User;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public final class Place {

    private Integer id;
    private String name;
    private String category;
    private String description;
    private String address;
    private String email;
    private String phone;
    private Location location;
    private User addedBy;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(final User addedBy) {
        this.addedBy = addedBy;
    }
}
