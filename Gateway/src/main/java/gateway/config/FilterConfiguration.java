package gateway.config;

import gateway.filter.AddResponseFilter;
import gateway.filter.PreFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public PreFilter preFilter(){
        return new PreFilter();
    }

    @Bean
    public AddResponseFilter addResponseFilter(){
        return new AddResponseFilter();
    }
}
