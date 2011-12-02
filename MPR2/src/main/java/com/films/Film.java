package com.films;
import com.exceptions.YearNotFoundException;
import com.statuses.FilmStatus;


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

	public void setYearException(int year) throws YearNotFoundException{
		if (year > 2012)
			throw new YearNotFoundException("Year cannot be greater than 2011");
		else this.year = year;
	}
	
	public String toString() {

		String film = "";
		film = film + title + ", " + director + ", " + year;
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
