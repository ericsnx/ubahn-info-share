package com.mobtail.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"com.mobtail"})
public class IntegrationTestConfiguration {

}
