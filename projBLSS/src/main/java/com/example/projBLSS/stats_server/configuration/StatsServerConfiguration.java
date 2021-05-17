package com.example.projBLSS.stats_server.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("stats")
@ComponentScan(basePackages = "com.example.projBLSS.stats_server")
public class StatsServerConfiguration {
}
