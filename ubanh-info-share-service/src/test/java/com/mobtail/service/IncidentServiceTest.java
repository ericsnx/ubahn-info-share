package com.mobtail.service;

import com.mobtail.repository.IncidentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class IncidentServiceTest {

    private IncidentRepository incidentRepository = Mockito.mock(IncidentRepository.class);

    private IncidentService incidentService;

    @BeforeEach
    void setup() {
        this.incidentService = new IncidentService(incidentRepository);
    }

    @Test
    public void saveWithSuccesTest() {
        //Testing service should be mostly about rules.
    }

}