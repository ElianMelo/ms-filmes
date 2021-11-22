package com.iftm.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iftm.moviecatalogservice.models.CatalogItem;
import com.iftm.moviecatalogservice.models.Movie;
import com.iftm.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCAtalog(@PathVariable("userId") String userId){
		
		


		RestTemplate restTemplate = new RestTemplate();

		//1) Obter todos os movies IDs
		List<Rating> ratings = Arrays.asList(
				new Rating("12",5),
				new Rating("15",2)
			);

		return ratings.stream().map(rating -> {
	    	//2) Para cada movid ID, chamar movie info service e get details
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(),"FILMAO",rating.getRating());
		})
				//3) Colocar tudo junto
			.collect(Collectors.toList());
		
		
		//return Collections.singletonList(new CatalogItem("Caça Fantasmas", "Filme de Gasparzinho", 8));
        //esse retorno acima foi criado para ele retornar algo "a força" sendo que ainda não temos dados
    	
    	//RestTemplate restTemplate = new RestTemplate();//como ja temos o restTemplate com Autowired acima, 
		//não precisaremos mais desta linha aqui
    	//restTemplat.getForObject("http://localhost:8082/movies/foo", Movie.class);
    	//Sobre o restTemplate:
    	//o primeiro argumento faz uma chamada para o que vc quer chamar via rest
    	//o retorno é uma string
    	//ou seja, cria uma intancia de uma classe, enche de dados e devove um objeto formado.
    	//O segundo argumento seria o filme e a avaliacao (a classe de onde vai puxar que gerará o novo objeto)
    	//- vamos copiar a classe Movie para esta projeto para usar as funcoes já implementadas
    	//tecnicamente deveria ser criado uma "call" dessas para cada filme avaliado
    	//essa função será movida para dentro do retorno para enfim trazer um retorno real
    	//fazendo com que o micro service funcione de verdade
	}
}
