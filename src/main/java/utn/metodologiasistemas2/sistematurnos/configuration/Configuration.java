package utn.metodologiasistemas2.sistematurnos.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import utn.metodologiasistemas2.sistematurnos.session.SessionFilter;

@org.springframework.context.annotation.Configuration
@EnableScheduling
public class Configuration {

    @Autowired
    SessionFilter sessionFilter;
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/api/*");
        return registration;
    }
}