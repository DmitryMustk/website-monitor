package com.example.demo.comparator;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HashBasedContentComparator implements ContentComparator {
    @Override
    public boolean isContentChanged(String previousContent, String currentContent) {
        if (previousContent == null || currentContent == null) {
            return !Objects.equals(previousContent, currentContent);
        }
        return !previousContent.equals(currentContent);
    }
}
