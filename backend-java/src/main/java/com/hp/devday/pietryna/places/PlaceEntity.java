package com.hp.devday.pietryna.places;

import com.hp.devday.pietryna.users.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "place")
@NamedEntityGraph(name = "PlaceEntity.all", attributeNodes = {@NamedAttributeNode("addedBy"), @NamedAttributeNode("category")})
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "place_id")
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private CategoryEntity category;

    private String description;

    private String address;

    private String email;

    private String phone;

    @Embedded
    private Location location;

    @OneToOne
    @JoinColumn(name = "added_by_user_id")
    private UserEntity addedBy;

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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(final CategoryEntity category) {
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

    public UserEntity getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(final UserEntity addedBy) {
        this.addedBy = addedBy;
    }
}
