package com.example.parkingchallenge.repository;

import com.example.parkingchallenge.model.ClientParamsEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientParamsRepository extends JpaRepository<ClientParamsEntity,Long> {
    @EntityGraph(value = "clientParams-with-params-item-parser")
    List<ClientParamsEntity> findByCity(String cityOrTown);
}
