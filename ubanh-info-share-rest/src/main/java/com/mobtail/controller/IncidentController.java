package com.mobtail.controller;

import com.mobtail.entity.Incident;
import com.mobtail.service.IncidentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController("incidents")
public class IncidentController {

    private IncidentService incidentService;

    @Autowired
    public IncidentController(@NonNull final IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Set<Incident> list() {
        return incidentService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@NonNull final Incident incident) {
        incidentService.save(incident);
    }

}
