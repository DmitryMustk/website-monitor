package com.example.demo;

import com.example.demo.service.WebsiteMonitoringService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WebsiteMonitorApplicationTests {
    @Autowired
    private WebsiteMonitoringService monitoringService;

	@Test
	void contextLoads() {
        Map<URI, String> yesterday = Map.of(
                URI.create("https://changed.com"), "old",
                URI.create("https://removed.com"), "content"
        );

        Map<URI, String> today = Map.of(
                URI.create("https://changed.com"), "new",
                URI.create("https://new.com"), "content"
        );

        Optional<String> report = monitoringService.buildDailyReport(yesterday, today);
        assertThat(report).isPresent();
        assertThat(report.get().replace("\n", ""))
                .contains("Исчезли следующие страницы: - https://removed.com")
                .contains("Появились следующие новые страницы: - https://new.com")
                .contains("Изменились следующие страницы: - https://changed.com");

	}

}
