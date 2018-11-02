package com.mobtail.service;

import com.mobtail.entity.Incident;
import com.mobtail.exception.UbanhShareInfoException;
import com.mobtail.repository.IncidentRepository;
import com.mobtail.request.CreateIncidentRequest;
import com.mobtail.request.UpdateIncidentRequest;
import com.mobtail.response.IncidentResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IncidentService {

    private IncidentRepository incidentRepository;

    @Autowired
    public IncidentService(final IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public Incident save(@NonNull final CreateIncidentRequest incidentRequest) {

        final Incident incident = buildIncidentEntity(UUID.randomUUID(), incidentRequest.getLine(), incidentRequest.getUser());
        return incidentRepository.save(incident);
    }

    public Incident update(@NonNull final UpdateIncidentRequest incidentRequest) {

        findById(incidentRequest.getId());
        final Incident incident = buildIncidentEntity(incidentRequest.getId(), incidentRequest.getLine(), incidentRequest.getUser());
        return incidentRepository.save(incident);
    }

    private Incident buildIncidentEntity(final UUID uuid, final String line, final String user) {
        return Incident.builder()
                .id(uuid)
                .line(line)
                .user(user)
                .createdAt(Instant.now()).build();
    }

    public List<IncidentResponse> findAll() {
        return StreamSupport.stream(incidentRepository.findAll().spliterator(), false).map(this::mapIncidentResponse).collect(Collectors.toList());
    }

    public IncidentResponse findById(@NonNull final UUID id) {
        return incidentRepository.findById(id).map(this::mapIncidentResponse).orElseThrow(() -> new UbanhShareInfoException("Incident not found for id=" + id));
    }

    private IncidentResponse mapIncidentResponse(@NotNull final Incident incident) {
        return IncidentResponse.builder().id(incident.getId()).line(incident.getLine()).user(incident.getUser()).reportedAt(incident.getCreatedAt()).build();
    }

}
