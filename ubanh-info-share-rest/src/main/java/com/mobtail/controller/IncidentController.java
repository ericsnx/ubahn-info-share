package com.mobtail.controller;

import com.mobtail.entity.Incident;
import com.mobtail.request.IncidentRequest;
import com.mobtail.service.IncidentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController("incidents")
public class IncidentController {

    private IncidentService incidentService;

    @Autowired
    public IncidentController(@NonNull final IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Incident> list() {
        return incidentService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody final IncidentRequest incidentRequest) {
        incidentService.save(incidentRequest);
    }

}