package com.example.demo.report;

import com.example.demo.domain.WebsiteChangeReport;

public interface ReportGenerator {
    String generate(WebsiteChangeReport report);
}
