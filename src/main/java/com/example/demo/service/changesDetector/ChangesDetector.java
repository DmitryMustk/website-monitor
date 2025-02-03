package com.example.demo.service.changesDetector;

import com.example.demo.domain.WebsiteChangeReport;

import java.net.URI;
import java.util.Map;

public interface ChangesDetector {
    WebsiteChangeReport detectChanges(
            Map<URI, String> previousState,
            Map<URI, String> currentState
    );
}
