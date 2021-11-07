package zw.co.afrosoft.configs;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Date :October 14,2021
 * @author Tafadzwa Pundo
 * @version 1.0
 *
 */
@Configuration
@EnableWebMvc
public class Config implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:1999")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(-1);
    }
}
