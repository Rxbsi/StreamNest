package de.rxbsi.streamnest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Alle Endpunkte
                .allowedOrigins(StreamNestApplication.URL)  // Die erlaubte Herkunft
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Erlaubte HTTP-Methoden
                .allowedHeaders("*")  // Erlaubte Header
                .allowCredentials(true);  // Erlaubt die Nutzung von Cookies oder HTTP-Authentifizierungsdaten
    }
}
