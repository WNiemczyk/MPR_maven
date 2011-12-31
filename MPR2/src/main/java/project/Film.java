package project;

import java.util.ArrayList;
import java.util.List;

import statuses.FilmStatus;

import exceptions.YearNotFoundException;

public class Film {

	private String title;
	private String director;
	private int year;
	private FilmStatus status;

	public Film() {

	}

	public Film(String title, String director, int year, FilmStatus status) {

		this.title = title;
		this.director = director;
		this.year = year;
		this.status = status;
	}

	public void setYearException(int year) throws YearNotFoundException {
		if (year > 2012)
			throw new YearNotFoundException("Year cannot be greater than 2011");
		else
			this.year = year;
	}

	public List<Film> init() {

		List<Film> films = new ArrayList<Film>();
		films.add(new Film("Chinatown", "Roman Polański", 1974,
				FilmStatus.Available));
		films.add(new Film("Taxi Driver", "Martin Scorsese", 1976,
				FilmStatus.Available));
		films.add(new Film("Snatch", "Guy Ritchie", 2000, FilmStatus.Available));
		films.add(new Film("Nóż w wodzie", "Roman Polański", 1961,
				FilmStatus.Available));
		
		return films;
	}

	public void addCondition(FilmCondition c) {
		List<Film> filmsToCondition = init();
		for (Film f : filmsToCondition) {
			if (c.getCondition(f)) {
				System.out.println(f + "\n");
			}
		}
	}

	public String toString() {

		String film = "";
		film = film + title + ", " + director + ", " + year + ", " + status;
		return film;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public FilmStatus getStatus() {
		return status;
	}

	public void setStatus(FilmStatus status) {
		this.status = status;
	}

}
