package se.ifmo.ru.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("se.ifmo.ru")
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
}
