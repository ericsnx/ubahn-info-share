package com.mobtail.service;

import com.mobtail.entity.Incident;
import com.mobtail.repository.IncidentRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IncidentService {

    private IncidentRepository incidentRepository;

    @Autowired
    public IncidentService(final IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public void save(@NonNull final Incident incident) {
        incidentRepository.save(incident);
    }

    public List<Incident> findAll() {
        return (List<Incident>) incidentRepository.findAll();
    }

    public Incident findById(@NonNull final UUID id) {
        return incidentRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
