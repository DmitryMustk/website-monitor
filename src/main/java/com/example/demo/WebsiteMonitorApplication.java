package com.example.demo;

import com.example.demo.service.WebsiteMonitoringService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@EnableCaching
public class WebsiteMonitorApplication {
	public static void main(String[] args) {
		Map<URI, String> yesterday = Map.of(
				URI.create("https://site1.com/page1"), "<html>Old Content</html>",
				URI.create("https://site2.com/about"), "<html>About Us</html>"
		);

		Map<URI, String> today = Map.of(
				URI.create("https://site1.com/page1"), "<html>New Content</html>",
				URI.create("https://site3.com/contact"), "<html>Contact Info</html>"
		);

		ApplicationContext context = SpringApplication.run(WebsiteMonitorApplication.class, args);
		WebsiteMonitoringService service = context.getBean(WebsiteMonitoringService.class);

		Optional<String> report = service.buildDailyReport(yesterday, today);
		report.ifPresent(System.out::println);
	}
}
