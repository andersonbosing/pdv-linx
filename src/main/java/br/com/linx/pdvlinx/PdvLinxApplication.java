package br.com.linx.pdvlinx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PdvLinxApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdvLinxApplication.class, args);
	}

}
