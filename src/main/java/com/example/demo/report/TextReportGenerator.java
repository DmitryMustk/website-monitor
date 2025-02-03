package com.example.demo.report;

import com.example.demo.domain.WebsiteChangeReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TextReportGenerator implements ReportGenerator {
    private final String reportTemplate;

    public TextReportGenerator(
            @Value("classpath:templates/report-template.txt")
            Resource templateResource
    ) throws IOException {
        this.reportTemplate = StreamUtils.copyToString(
                templateResource.getInputStream(),
                StandardCharsets.UTF_8
        );
    }

    @Override
    public String generate(WebsiteChangeReport report) {
        return String.format(reportTemplate,
                formatSection(report.disappearedUrls()),
                formatSection(report.newUrls()),
                formatSection(report.changedUrls())
        );
    }

    private String formatSection(Set<URI> uris) {
        if (uris.isEmpty()) {
            return "(нет изменений)";
        }
        return uris.stream()
                .map(URI::toString)
                .collect(Collectors.joining("\n- ", "\n- ", ""));
    }
}
