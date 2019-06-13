package com.mobtail.service;

import com.mobtail.DateUtil;
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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IncidentService {

    private final DateTimeFormatter dateTimeFormatter;
    private final IncidentRepository incidentRepository;
    private final DateUtil dateUtil;

    @Autowired
    public IncidentService(final IncidentRepository incidentRepository,
                           final DateUtil dateUtil) {
        this.incidentRepository = incidentRepository;
        this.dateUtil = dateUtil;
        this.dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(Locale.GERMANY)
                .withZone(ZoneId.systemDefault());

    }

    public Incident save(@NonNull final CreateIncidentRequest incidentRequest) {
        System.out.println(dateUtil.instantNow());
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
                .removed(Boolean.TRUE)
                .reportedAt(Instant.now()).build();
    }

    public List<IncidentResponse> findAll() {
        return StreamSupport.stream(incidentRepository.findAll().spliterator(), false).map(this::mapIncidentResponse).collect(Collectors.toList());
    }

    public IncidentResponse findById(@NonNull final UUID id) {
        return incidentRepository.findById(id).map(this::mapIncidentResponse).orElseThrow(() -> new UbanhShareInfoException("Incident not found for id=" + id));
    }

    private IncidentResponse mapIncidentResponse(@NotNull final Incident incident) {
        return IncidentResponse.builder()
                .id(incident.getId())
                .line(incident.getLine())
                .user(incident.getUser())
                .reportedAt(dateTimeFormatter.format(incident.getReportedAt()))
                .build();
    }

}
