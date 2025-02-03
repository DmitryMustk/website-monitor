package com.example.demo.service;

import com.example.demo.domain.WebsiteChangeReport;
import com.example.demo.report.ReportGenerator;
import com.example.demo.service.changesDetector.ChangesDetector;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultWebsiteMonitoringService implements WebsiteMonitoringService {
    private final ChangesDetector changeDetection;
    private final ReportGenerator reportGenerator;

    @Override
    public Optional<String> buildDailyReport(
            Map<URI, String> previousState,
            Map<URI, String> currentState
    ) {
        WebsiteChangeReport report = changeDetection.detectChanges(previousState, currentState);
        return report.hasChanges()
                ? Optional.of(reportGenerator.generate(report))
                : Optional.empty();
    }
}
