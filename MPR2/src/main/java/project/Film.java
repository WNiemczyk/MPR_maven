package project;

import java.util.ArrayList;
import java.util.List;

import statuses.FilmStatus;

import exceptions.YearNotFoundException;

public class Film {

	private String title;
	private List<Director> directors;
	private int year;
	private FilmStatus status;

	public Film() {

	}

	public Film(String title, List<Director> directors, int year, FilmStatus status) {

		this.title = title;
		this.directors = directors;
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

		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
	
		List<Director> directorsTaxiDriver = new ArrayList<Director>();
		directorsTaxiDriver.add(new Director("Martin", "Scorsese", "USA", 1942));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorFrantic = new ArrayList<Director>();
		directorFrantic.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsCasino = new ArrayList<Director>();
		directorsCasino.add(new Director("Martin", "Scorsese", "USA", 1942));
		
		List<Director> directorsGangsOfNY = new ArrayList<Director>();
		directorsGangsOfNY.add(new Director("Martin", "Scorsese", "USA", 1942));
		
		List<Director> directorsASeriousMan = new ArrayList<Director>();
		directorsASeriousMan.add(new Director("Ethan", "Coen", "USA", 1957));
		directorsASeriousMan.add(new Director("Joel", "Coen", "USA", 1954));
		
		List<Film> films = new ArrayList<Film>();
		films.add(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		films.add(new Film("Taxi Driver", directorsTaxiDriver, 1976,
				FilmStatus.Available));
		films.add(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		films.add(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		films.add(new Film("Frantic", directorFrantic, 1988, FilmStatus.Available));
		films.add(new Film("Casino", directorsCasino, 1995, FilmStatus.Available));
		films.add(new Film("Gangs of New York", directorsGangsOfNY, 2002, FilmStatus.Available));
		films.add(new Film("A serious man", directorsASeriousMan, 2009, FilmStatus.Available));
	
		
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
		film = film + title + ", " + directors + ", " + year + ", " + status;
		return film;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Director> directors) {
		this.directors = directors;
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
