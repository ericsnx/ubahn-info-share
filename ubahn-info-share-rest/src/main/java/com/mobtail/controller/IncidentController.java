package com.mobtail.controller;

import com.mobtail.request.CreateIncidentRequest;
import com.mobtail.request.UpdateIncidentRequest;
import com.mobtail.response.IncidentResponse;
import com.mobtail.service.IncidentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController("incidents")
public class IncidentController {

    private final IncidentService incidentService;

    @Autowired
    public IncidentController(@NonNull final IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public IncidentResponse findById(@Param("id") final UUID id) {
        return incidentService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<IncidentResponse> list() {
        return incidentService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody final CreateIncidentRequest incidentRequest) {
        incidentService.save(incidentRequest);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody final UpdateIncidentRequest incidentRequest) {
        incidentService.update(incidentRequest);
    }

}
