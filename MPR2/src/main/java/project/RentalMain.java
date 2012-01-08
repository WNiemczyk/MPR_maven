package project;

import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import services.DBDirector;
import services.DBFilm;
import services.DBFilmCondition;
import services.Film_DirectorDBManager;
import statuses.FilmStatus;

import events.FilmEvent;
import events.FilmListener;
import exceptions.EndOfShelfException;
import exceptions.FilmNotFoundException;
import exceptions.LocationIsNullException;

public class RentalMain implements FilmListener {

	private static Logger logger = Logger.getLogger(RentalMain.class);
	public Shelf shelf;

	public RentalMain(Shelf shelf) {
		this.shelf = shelf;
	}

	public RentalMain() {
		super();
	}

	public static void main(String[] args) {

		PropertyConfigurator.configure("Log4J.properties");

		RentalMain rental = new RentalMain();
		Shelf shelf = new Shelf();
		shelf.addFilmListener(rental);
		//shelf.init();

		shelf.show();

		List<Director> directorBracia = new ArrayList<Director>();
		directorBracia.add(new Director("Susane", "Bier", "Denmark", 1960));
		
		try {
			shelf.putInLocation(new Location(5, 0), new Film("Bracia",
					directorBracia, 2004, FilmStatus.Available));
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
			logger.info(shelf.changeLocation(new Location(2, 0), new Location(
					3, 0)));
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

		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
	
		List<Director> directorsTaxiDriver = new ArrayList<Director>();
		directorsTaxiDriver.add(new Director("Martin", "Scorsese", "USA", 1942));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		ArrayList<Film> newFilms = new ArrayList<Film>();
		newFilms.add(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		newFilms.add(new Film("Taxi Driver", directorsTaxiDriver, 1976,
				FilmStatus.Available));
		newFilms.add(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		newFilms.add(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));

		try {
			shelf.put(newFilms);
		} catch (EndOfShelfException e) {
			logger.warn(e.toString());
		}

		shelf.show();
		
		
		List<Director> directorsMulhollandDrive = new ArrayList<Director>();
		directorsMulhollandDrive.add(new Director("David", "Lynch", "USA", 1946));
		
		try {
			try {
				try {
					logger.info(shelf.insertNewFilmOnOccupiedLocation(
							"La Comunidad", new Location(0, 0), new Film(
									"Mulholland Drive", directorsMulhollandDrive, 2002,
									FilmStatus.Available)));
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
			Film film = shelf.findByLocation(new Location(1, 1));
			shelf.reserve(film);
		} catch (LocationIsNullException e) {
			logger.warn(e.toString());
		}

		shelf.clearAll();
		shelf.show();
		
		
		

		//------------------------D A T A B A S E------------------------
		
		
		System.out
				.println("-----------------D A T A B A S E-----------------\n");

		
		final String director = "Roman Polański";

		System.out.println("\n\nAnonymous method with condition: director equals 'Roman Polański'\n");
		Film f = new Film();
		f.addCondition(new FilmCondition() {

			public boolean getCondition(Film f) {
				if (f.getDirectors().equals(director))
					return true;
				return false;
			}

		});

		System.out.println("\n\nAnonymous method with condition: year == 1976\n");
		
		f.addCondition(new FilmCondition() {

			public boolean getCondition(Film f) {
				if (f.getYear() == 1976)
					return true;
				return false;
			}
		});

		System.out.println("\n\nAnonymous method with condition: title contains word 'Taxi'\n");
		f.addCondition(new FilmCondition() {

			public boolean getCondition(Film f) {
				if (f.getTitle().contains("Taxi"))
					return true;
				return false;
			}
		});

		DBDirector dbDirector = new DBDirector();
		Director d = new Director();
		dbDirector.addListOfDirectors(d.init());
		
		DBFilm dbFilm = new DBFilm();
		dbFilm.addListOfFilms(f.init());
		
		System.out.println("\n\nAnonymous method with condition: director equals 'Roman Polański' has more than one film in DB\n");
		dbFilm.addCondition("Roman Polański", new DBFilmCondition() {

			public boolean getCondition(Integer i) {
				if (i > 1)
					return true;
				return false;
			}
		});
		
		System.out.println("\nList of all films: " + dbFilm.getAllFilms());
		dbFilm.deleteFilm(2);
		System.out.println("\nList of all films after delete: " + dbFilm.getAllFilms());

		System.out.println("\nList of id_film: "
				+ dbFilm.getListIdFilmByDirector("Polański")); // 0,
																			// 3
		System.out.println("\nList of id_director: "
				+ dbDirector.getListIdDirectorBySurname("Scorsese")); // 0

	}
	
	
	
	
	
/*		fdDBManager.addListFilm_Director(
				filmDBManager.getListIdFilmByDirector("Roman Polański"),
				directorDBManager.getListIdDirectorBySurname("Polański"));*/
		

		// SELECT title, name, surname FROM film INNER JOIN film_director ON
		// (film.id=film_director.id_film) INNER JOIN director ON
		// (film_director.id_director=director.id) WHERE countryofbirth LIKE
		// '%France%'


	// ------------------------E V E N T S------------------------//

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
