package org.clipboardHealth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.clipboardHealth")
@PropertySource("classpath:App.properties")
public class SpringConfig {

}
