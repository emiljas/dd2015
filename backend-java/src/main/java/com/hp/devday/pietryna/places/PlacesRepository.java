package com.hp.devday.pietryna.places;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface PlacesRepository extends CrudRepository<PlaceEntity, Integer> {

    @EntityGraph(value = "PlaceEntity.all", type = EntityGraph.EntityGraphType.LOAD)
    Iterable<PlaceEntity> findAll();
}
