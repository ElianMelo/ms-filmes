package com.iftm.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MovieCatalogServiceApplication {
	
	//@Bean serve para exportar uma classe para o Spring, para que ele consiga carregar essa
	//classe e fazer injeção de dependência dela em outra classes.
	//o Bean informa que tem algum dado disponível e o Autowired informa que precisa desse dado
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
