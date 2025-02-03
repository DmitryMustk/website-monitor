package com.example.demo.domain;


import lombok.Builder;

import java.net.URI;
import java.util.Set;

@Builder
public record WebsiteChangeReport(
        Set<URI> newUrls,
        Set<URI> changedUrls,
        Set<URI> disappearedUrls
) {
    public boolean hasChanges() {
        return !disappearedUrls.isEmpty() || !newUrls.isEmpty() || !changedUrls.isEmpty();
    }
}
