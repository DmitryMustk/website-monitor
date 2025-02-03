package com.example.demo.service;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

public interface WebsiteMonitoringService {
    Optional<String> buildDailyReport(
            Map<URI, String> previousState,
            Map<URI, String> currentState
    );
}
