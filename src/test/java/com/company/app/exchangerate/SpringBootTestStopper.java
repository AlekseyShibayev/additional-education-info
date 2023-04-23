package com.company.app.exchangerate;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan(basePackages = "com.company.app.exchangerate", lazyInit = true)
public class SpringBootTestStopper {
}
