package project;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import statuses.FilmStatus;



import events.FilmEvent;
import events.FilmListener;
import exceptions.EndOfShelfException;
import exceptions.FilmNotFoundException;
import exceptions.LocationIsNullException;



public class Shelf {

	private static Logger logger = Logger.getLogger(Shelf.class);
	private final static int SHELF_HEIGHT = 9;
	private final static int SHELF_WIDTH = 5;

	private Film film;
	private List<FilmListener> filmListeners = new ArrayList<FilmListener>();

	private Map<Location, Film> existedFilms;

	public Shelf() {

		this.existedFilms = new HashMap<Location, Film>();

	}

	public void init() {
		this.existedFilms.put(new Location(0, 0), new Film("La Comunidad",
				"de la Iglesia", 2004, FilmStatus.Available));
		this.existedFilms.put(new Location(1, 0), new Film("Soul Kitchen",
				"Fatih Akin", 2010, FilmStatus.Available));
		this.existedFilms.put(new Location(2, 0), new Film(
				"The Limits of Control", "Jim Jarmusch", 2009,
				FilmStatus.Available));
		this.existedFilms.put(new Location(3, 0), new Film("Broken Flowers",
				"Jim Jarmusch", 2005, FilmStatus.Available));
		this.existedFilms.put(new Location(4, 0), new Film("Dom zły",
				"Wojciech Smarzowski", 2009, FilmStatus.Available));

	}

	public synchronized void addFilmListener(FilmListener listener) {

		filmListeners.add(listener);
	}

	public synchronized void removeFilmListener(FilmListener listener) {

		filmListeners.remove(listener);
	}

	public String toString() {

		int minX = 0;
		int maxX = 0;
		int minY = 0;
		int maxY = 0;

		String map = "";

		Location l = null;

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			l = e.getKey();

			if (minX > l.getX())
				minX = l.getX();
			if (maxX < l.getX())
				maxX = l.getX();
			if (minY > l.getY())
				minY = l.getY();
			if (maxY < l.getY())
				maxY = l.getY();

		}

		for (int j = maxY; j >= minY; j--) {
			for (int i = minX; i <= maxX; i++) {
				map = map + "(" + i + "," + j + ") " + "["
						+ existedFilms.get(new Location(i, j)) + "] ";
			}
			map = map + "\n";
		}

		return map;
	}

	public void show() {

		logger.info("Current films: " + this.toString());
	}

	public void put(Location location, Film film) {

		this.existedFilms.put(location, film);
		this.film = film;
		this.fireFilmAddedEvent();
		logger.info("Added film " + film + " in location " + location);
	}

	private synchronized void fireFilmAddedEvent() {
		FilmEvent event = new FilmEvent(this, film);
		Iterator<FilmListener> iterator = filmListeners.iterator();
		while (iterator.hasNext()) {
			iterator.next().filmAdded(event);
		}
	}

	public void borrow(Film film) {
		if ((film.getStatus() == FilmStatus.Available)
				|| (film.getStatus() == FilmStatus.Reserved)) {
			this.film = film;
			film.setStatus(FilmStatus.Borrowed);
			fireFilmBorrowedEvent();
		}
	}

	private synchronized void fireFilmBorrowedEvent() {
		FilmEvent event = new FilmEvent(this, film);
		Iterator<FilmListener> iterator = filmListeners.iterator();
		while (iterator.hasNext()) {
			iterator.next().filmBorrowed(event);
		}
	}

	public void returnFilm(Film film) {
		this.film = film;
		film.setStatus(FilmStatus.Available);
		fireFilmReturnedEvent();
	}

	private synchronized void fireFilmReturnedEvent() {
		FilmEvent event = new FilmEvent(this, film);
		Iterator<FilmListener> iterator = filmListeners.iterator();
		while (iterator.hasNext()) {
			iterator.next().filmReturned(event);
		}
	}

	public void reserve(Film film) {
		if (film.getStatus() == FilmStatus.Available) {
			this.film = film;
			film.setStatus(FilmStatus.Reserved);
			fireFilmReservedEvent();
		}
	}

	private synchronized void fireFilmReservedEvent() {
		FilmEvent event = new FilmEvent(this, film);
		Iterator<FilmListener> iterator = filmListeners.iterator();
		while (iterator.hasNext()) {
			iterator.next().filmReserved(event);
		}
	}

	private Location getFreeLocation() throws EndOfShelfException {
		Location l = new Location(0, 0);

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if (l.compareTo(e.getKey()) < 1){
				l.setX(e.getKey().getX());
				l.setY(e.getKey().getY());
			}
		}

		if ((l.getX() == Shelf.SHELF_WIDTH) && (l.getY() == Shelf.SHELF_HEIGHT))
			throw new EndOfShelfException("All shelf is occupied");

		if (l.getX() < this.SHELF_WIDTH)
			l.setX(l.getX() + 1);
		else if (l.getX() == this.SHELF_WIDTH) {
			l.setX(0);
			l.setY(l.getY() + 1);

		}

		logger.info("Found free location: " + l);
		
		return l;
	}

	public void put(Film film) throws EndOfShelfException {
		Location location = this.getFreeLocation();
		
		if (location == null) throw new EndOfShelfException("All shelf is occupied");
		
		this.put(location, film);
	}

	public void put(ArrayList<Film> films) throws EndOfShelfException {
		
		for (Film f : films) {
			put(f);
		}
	}

	public void putInLocation(Location l, Film f)
			throws LocationIsNullException {

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if ((e.getKey() != null) && (e.getKey().equals(l))) {

				throw new LocationIsNullException(
						"Cannot put film in occupied location");
			}

			else if ((e.getKey() == null) && (e.getKey().equals(l))) {
				this.existedFilms.put(l, f);
			}

		}

		this.existedFilms.put(l, f);

		logger.info("Added film " + f + " in location " + l);

	}

	public void removeByLocation(Location l) {

		this.getExistedFilms().remove(l);
		logger.info("Removed film from location " + l);

	}

	public Film findByLocation(Location l) throws LocationIsNullException {

		Film film = this.getExistedFilms().get(l);
		if (film == null)
			throw new LocationIsNullException("There is no film in location: "
					+ l);

		logger.info("Found film " + film + " in location" + l);

		return film;
	}
	
	public Map<Location, Film> insertNewFilmOnOccupiedLocation(String t,
			Location l, Film f) throws FilmNotFoundException,
			LocationIsNullException, EndOfShelfException {

		Film film = null;

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if (e.getValue().getTitle().equals(t)) {
				this.existedFilms.put(e.getKey(), f);
				film = e.getValue();
				//this.put(film);
				break;
			}

			if (film == null)
				throw new FilmNotFoundException("There is not film with title"
						+ film.getTitle());
		}

		logger.info("New film " + f + " is putted instead of film " + t);

		return existedFilms;
	}

	public Location changeLocation(Location l1, Location l2)
			throws LocationIsNullException {

		Film location1 = existedFilms.get(l1);
		Film location2 = existedFilms.get(l2);

		if ((location1 == null) || (location2 == null)) {
			throw new LocationIsNullException("Cannot give empty location");
		} else {
			this.existedFilms.put(l1, (Film) location2);
			this.existedFilms.put(l2, (Film) location1);
		}

		logger.info("Changed films from location " + l1 + " into " + l2
				+ " and viceversa");

		return l2;
	}

	public void clearAll() {

		this.getExistedFilms().clear();
		logger.info("All films are cleared");
	}

	public Map<Location, Film> findByYear(int y) throws FilmNotFoundException {

		Map<Location, Film> foundedFilms = new HashMap<Location, Film>();

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if (e.getValue().getYear() == y)
				foundedFilms.put(e.getKey(), e.getValue());
		}

		if (foundedFilms.size() == 0)
			throw new FilmNotFoundException("There are not films from year: "
					+ y);

		logger.info("There are " + foundedFilms.size() + " films from year "
				+ y);

		return foundedFilms;
	}

	public Map<Location, Film> findByDirector(String d)
			throws FilmNotFoundException {

		Map<Location, Film> foundedFilms = new HashMap<Location, Film>();

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if (e.getValue().getDirector().contains(d))
				foundedFilms.put(e.getKey(), e.getValue());
		}

		if (foundedFilms.size() == 0)
			throw new FilmNotFoundException("There are not films by director: "
					+ d);

		logger.info("There are " + foundedFilms.size() + " films made by " + d);

		return foundedFilms;

	}

	public ArrayList<Location> findLocationByDirector(String d)
			throws LocationIsNullException {

		ArrayList<Location> locations = new ArrayList<Location>();

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if (e.getValue().getDirector().contains(d))
				locations.add(e.getKey());
		}

		if (locations.isEmpty())
			throw new LocationIsNullException(
					"There are not films by director: " + d);

		logger.info("Films made by " + d + " you can find in locations:");

		return locations;

	}

	public Map<Location, Film> getExistedFilms() {
		return existedFilms;
	}

	public void setExistedFilms(Map<Location, Film> existedFilms) {
		this.existedFilms = existedFilms;
	}

}
