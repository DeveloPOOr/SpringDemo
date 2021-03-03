package org.example.SpringDemo;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.example.SpringDemo")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {
}
