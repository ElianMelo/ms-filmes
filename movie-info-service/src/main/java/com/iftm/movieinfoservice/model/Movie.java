package com.iftm.movieinfoservice.model;

public class Movie {
	
	private String movieId;
	private String nome;
	
	public Movie() {
		super();
	}

	public Movie(String movieId, String nome) {
		this.movieId = movieId;
		this.nome = nome;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
