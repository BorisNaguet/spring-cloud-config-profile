package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Value("${couchbase.static.host}")
    private String simple;
    
	@Bean
	public StartupRunner configRunner() { 
	    return new StartupRunner(simple); 
	}
	
	@Bean
	@Profile("product")
    public StartupRunner optRunner() { 
        return new StartupRunner("from opt profile"); 
    }
	   
	public static class StartupRunner implements CommandLineRunner {
	    private String s;
        public StartupRunner(String s) {
            this.s = s;
        }
	    
        @Override 
        public void run(String... args) throws Exception {
            System.out.println("==>> " + s);
            
        } 
    }
}
