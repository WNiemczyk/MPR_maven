package project;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import services.DirectorDBManager;
import services.FilmDBManager;
import statuses.FilmStatus;



import events.FilmEvent;
import events.FilmListener;
import exceptions.EndOfShelfException;
import exceptions.FilmNotFoundException;
import exceptions.LocationIsNullException;



public class Rental implements FilmListener{

	private static Logger logger = Logger.getLogger(Rental.class);
	public Shelf shelf;
	
	
	public Rental(Shelf shelf){
		this.shelf = shelf;	
	}
	
	
	public Rental(){
		super();
	}
	

	public static void main(String[] args) {

		PropertyConfigurator.configure("Log4J.properties");

		Rental rental = new Rental();
		Shelf shelf = new Shelf();
		shelf.addFilmListener(rental);
		shelf.init();
		
		shelf.show();
		
		try {
			shelf.putInLocation(new Location(5, 0), new Film("Bracia", "Susanne Bier", 2004, FilmStatus.Available));
		} catch (LocationIsNullException e) {
			logger.warn(e.toString());
		}
		
		shelf.show();

		try {
			shelf.findByLocation(new Location(1, 0));
		} catch (LocationIsNullException e) {
			logger.warn(e.toString());
		}

		try {
			logger.info(shelf.findByYear(2045));
		} catch (FilmNotFoundException e) {
			logger.warn(e.toString());
		}

		try {
			logger.info(shelf.findByDirector("Jim Jarmusch"));
		} catch (FilmNotFoundException e) {
			logger.warn(e.toString());
		}

		try {
			logger.info(shelf.findLocationByDirector("Jarmusch"));
		} catch (LocationIsNullException e) {
			logger.warn(e.toString());
		}

		try {
			logger.info(shelf.changeLocation(new Location(2, 0), new Location(3, 0)));
		} catch (LocationIsNullException e) {
			logger.warn(e.toString());
		}
		
		shelf.show();
		

		try {
			Film f = shelf.findByLocation(new Location(1, 0));
			shelf.reserve(f);
			shelf.borrow(f);
			shelf.returnFilm(f);
		} catch (LocationIsNullException e) {
			logger.warn(e.toString());
		}
		

		ArrayList<Film> newFilms = new ArrayList<Film>();
		newFilms.add(new Film("Chinatown", "Roman Polański", 1974, FilmStatus.Available));
		newFilms.add(new Film("Taxi Driver", "Martin Scorsese", 1976, FilmStatus.Available));
		newFilms.add(new Film("Snatch", "Guy Ritchie", 2000, FilmStatus.Available));
		newFilms.add(new Film("Nóż w wodzie", "Roman Polański", 1961, FilmStatus.Available));
	
		
		
		try {
			shelf.put(newFilms);
		} catch (EndOfShelfException e) {
			logger.warn(e.toString());
		}
		
		shelf.show();

		try {
			try {
				try {
					logger.info(shelf.insertNewFilmOnOccupiedLocation("La Comunidad", new Location(0, 0), new Film("Mulholland Drive",
							"David Lynch", 2002, FilmStatus.Available)));
				} catch (EndOfShelfException e) {
					
					logger.info(e.toString());
				}
			} catch (LocationIsNullException e) {
				logger.warn(e.toString());
			}
		} catch (FilmNotFoundException e) {
			logger.warn(e.toString());
		}
		
		shelf.show();
		
		shelf.removeByLocation(new Location(1, 0));
		
		shelf.show();
		
		try {
			Film film = shelf.findByLocation(new Location(1,1));
			shelf.reserve(film);
		} catch (LocationIsNullException e) {
			logger.warn(e.toString());
		}
	
		
		shelf.clearAll();
		shelf.show();
		
		List<Film> films = new ArrayList<Film>();
		films.add(new Film("Chinatown", "Roman Polański", 1974, FilmStatus.Available));
		films.add(new Film("Taxi Driver", "Martin Scorsese", 1976, FilmStatus.Available));
		films.add(new Film("Snatch", "Guy Ritchie", 2000, FilmStatus.Available));
		films.add(new Film("Nóż w wodzie", "Roman Polański", 1961, FilmStatus.Available));
	
		FilmDBManager filmDBManager = new FilmDBManager();
		filmDBManager.addListOfFilms(films);
	
		List<Director> directors = new ArrayList<Director>();
		directors.add(new Director("Roman", "Polański", "France", 1933));
		directors.add(new Director("Martin", "Scorsese", "USA", 1942));
		directors.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		directors.add(new Director("Susanne", "Bier", "Denmark", 1960));
		directors.add(new Director("Alex", "de la Iglesia", "Spain", 1965));
		
		DirectorDBManager directorDBManager = new DirectorDBManager();
		directorDBManager.addListOfDirectors(directors);
		
		
		
		int i = filmDBManager.getIdFilmByTitle("Snatch");

		System.out.println("\nId szukane: " + i);
		
		filmDBManager.deleteFilm(i);
		
		System.out.println(films);
	
		
		int y = 1961;
		System.out.println("\nId filmu z roku: " + y + " to: " + filmDBManager.getIdFilmByYear(y));
		
		
		
		
	}


	
	
	
	
	
	
	public void filmBorrowed(FilmEvent event) {
		event.getFilm().setStatus(FilmStatus.Available);
		System.out.println("Borrowed film: " + event.getFilm().getTitle());		
		
	}

	public void filmReturned(FilmEvent event) {
		event.getFilm().setStatus(FilmStatus.Available);
		System.out.println("Returned film: " + event.getFilm().getTitle());
	}


	public void filmReserved(FilmEvent event) {
		event.getFilm().setStatus(FilmStatus.Reserved);
		System.out.println("Reserved film: " + event.getFilm().getTitle());
	}

	
	public void filmAdded(FilmEvent event) {
		event.getFilm().setStatus(FilmStatus.Available);
		System.out.println("Added film: " + event.getFilm().getTitle());
	}

}
