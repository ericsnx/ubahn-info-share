package com.mobtail.service;

import com.mobtail.entity.Incident;
import com.mobtail.repository.IncidentRepository;
import com.mobtail.request.IncidentRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class IncidentService {

    private IncidentRepository incidentRepository;

    @Autowired
    public IncidentService(final IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public Incident save(@NonNull final IncidentRequest incidentRequest) {

        final Incident incident = Incident.builder()
                .id(UUID.randomUUID())
                .line(incidentRequest.getLine())
                .user(incidentRequest.getUser())
                .createdAt(Instant.now()).build();

        return incidentRepository.save(incident);
    }

    public List<Incident> findAll() {
        return (List<Incident>) incidentRepository.findAll();
    }

    public Incident findById(@NonNull final UUID id) {
        return incidentRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
