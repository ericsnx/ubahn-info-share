package com.mobtail.repository;

import com.mobtail.entity.Incident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IncidentRepository extends CrudRepository<Incident, UUID> {
}
