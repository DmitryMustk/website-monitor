package com.example.demo.service.changesDetector;

import com.example.demo.comparator.ContentComparator;
import com.example.demo.domain.WebsiteChangeReport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DefaultChangesDetector implements ChangesDetector {
    private final ContentComparator contentComparator;

    @Override
    public WebsiteChangeReport detectChanges(
            Map<URI, String> previousState,
            Map<URI, String> currentState
    ) {
        return WebsiteChangeReport.builder()
                .newUrls(findNewUrls(previousState, currentState))
                .changedUrls(findChangedUrls(previousState, currentState))
                .disappearedUrls(findDisappearedUrls(previousState, currentState))
                .build();
    }

    private Set<URI> findNewUrls(
            Map<URI, String> previousState,
            Map<URI, String> currentState
    ) {
        return currentState.keySet().stream()
                .filter(uri -> !previousState.containsKey(uri))
                .collect(Collectors.toSet());
    }

    private Set<URI> findChangedUrls(
            Map<URI, String> previousState,
            Map<URI, String> currentState
    ) {
        return currentState.keySet().stream()
                .filter(previousState::containsKey)
                .filter(uri -> contentComparator.isContentChanged(
                        previousState.get(uri),
                        currentState.get(uri))
                )
                .collect(Collectors.toSet());
    }

    private Set<URI> findDisappearedUrls(
            Map<URI, String> previousState,
            Map<URI, String> currentState
    ) {
        return previousState.keySet().stream()
                .filter(uri -> !currentState.containsKey(uri))
                .collect(Collectors.toSet());
    }
}
