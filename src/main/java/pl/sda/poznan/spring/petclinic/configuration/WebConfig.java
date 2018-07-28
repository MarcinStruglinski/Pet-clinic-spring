package pl.sda.poznan.spring.petclinic.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.sda.poznan.spring.petclinic.conversion.ApplicationUserToApplicationUserDtoConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new ApplicationUserToApplicationUserDtoConverter());
  }
}
