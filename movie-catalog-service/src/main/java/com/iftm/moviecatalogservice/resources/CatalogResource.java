package com.iftm.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iftm.moviecatalogservice.models.CatalogItem;
import com.iftm.moviecatalogservice.models.Movie;
import com.iftm.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {
	
	//o Bean informa que tem algum dado disponível e o Autowired informa que precisa desse dado
	@Autowired
	private RestTemplate restTemplate;
	//o Autowired aqui informa ao Spring que em algum lugar (MovieCatalogServiceApplication)
	//existe um BEAN desse RestTemplate e o inject precisa desse dado e é injetado aqui
	
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);

        return userRating.getRatings().stream().map(rating -> {
        	Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
        }).collect(Collectors.toList());
	}
}
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

