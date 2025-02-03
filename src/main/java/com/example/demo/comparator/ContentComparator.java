package com.example.demo.comparator;

public interface ContentComparator {
    boolean isContentChanged(
            String previousContent,
            String currentContent
    );
}
