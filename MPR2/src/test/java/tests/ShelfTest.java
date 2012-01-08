package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import project.Director;
import project.Film;
import project.Location;
import project.Shelf;
import statuses.FilmStatus;

import exceptions.*;


import exceptions.EndOfShelfException;
import exceptions.FilmNotFoundException;
import exceptions.LocationIsNullException;



public class ShelfTest {

	Shelf s = new Shelf();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		List<Director> directorsDzienSwira = new ArrayList<Director>();
		directorsDzienSwira.add(new Director("Marek", "Koterski", "Poland", 1942));
		
		Location l = new Location(0, 0);
		s.put(l, new Film("Dzień świra", directorsDzienSwira, 2002, FilmStatus.Available));
		
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testShow() {
		
		assertEquals(s.getExistedFilms().get(new Location(0, 0)).toString(), "Dzień świra, Marek Koterski, 2002");
		
	}

	
	@Test
	public void testPutInFreeLocation() {
	
		assertTrue(s.getExistedFilms().size() > 0);
	}


	@Test
	public void testPutFilm() throws LocationIsNullException, EndOfShelfException{
		
		List<Director> directorsDzienSwira = new ArrayList<Director>();
		directorsDzienSwira.add(new Director("Marek", "Koterski", "Poland", 1942));
		
		s.put(new Film("Dzień świra", directorsDzienSwira, 2002, FilmStatus.Available));		
		assertTrue(s.getExistedFilms().size() > 0);
		
	}

	@Test
	public void testRemoveByLocation() {

		Location l = new Location(0, 0);
		s.removeByLocation(l);
		assertNull(s.getExistedFilms().get(l));
		
	}

	@Test
	public void testFindByLocation() throws LocationIsNullException{
	
		Location l = new Location(0, 0);
		assertSame(s.findByLocation(l), l);

	}

	@Test
	public void testChangeLocation() {
		
	
		List<Director> directorsDzienSwira = new ArrayList<Director>();
		directorsDzienSwira.add(new Director("Marek", "Koterski", "Poland", 1942));
		
		List<Director> directorsWszyscyJestesmyChrystusami = new ArrayList<Director>();
		directorsWszyscyJestesmyChrystusami.add(new Director("Marek", "Koterski", "Poland", 1942));
		
		
		Location l1 = new Location(0, 0);
		Location l2 = new Location(0, 1);
		s.put(l1, new Film("Dzień świra", directorsDzienSwira, 2002, FilmStatus.Available));
		s.put(l2, new Film("Wszyscy jesteśmy Chrystusami", directorsWszyscyJestesmyChrystusami, 2007, FilmStatus.Available));
		Film f1 = s.getExistedFilms().get(l1);
		Film f2 = s.getExistedFilms().get(l2);
		
		s.getExistedFilms().put(l1, (Film) f2);
		s.getExistedFilms().put(l2, (Film) f1);
		
		assertSame(s.getExistedFilms().get(l2), f1);
	}

	@Test
	public void testClearAll() {
		
		s.getExistedFilms().clear();
		assertTrue(s.getExistedFilms().size() == 0);
	}

	@Test
	public void testFindByYear() throws FilmNotFoundException{
		
		Location l = new Location(0, 0);
		assertSame(s.findByYear(2002).get(l), s.getExistedFilms().get(l));
	}


	@Test
	public void testFindLocationByDirector() throws LocationIsNullException{
		
		List<Director> directorsDzienSwira = new ArrayList<Director>();
		directorsDzienSwira.add(new Director("Marek", "Koterski", "Poland", 1942));
		
		Location l = new Location(0, 0);
		Film f = new Film("Dzień świra", directorsDzienSwira, 2002, FilmStatus.Available);
		s.put(l, f);
	
		Film film = new Film();
		String surname = null;
		List<Director> directors = film.getDirectors();
		for(Director d : directors)
			surname = d.getSurname();
		
		assertEquals(s.findLocationByDirector(surname), l);
		
	}

	@Test
	public void testGetExistedFilms() {
		
		assertTrue(s.getExistedFilms().size() > 0);
		
	}

	@Test
	public void testSetExistedFilms() {
		
		List<Director> directorsDzienSwira = new ArrayList<Director>();
		directorsDzienSwira.add(new Director("Marek", "Koterski", "Poland", 1942));
		
		List<Director> directorsWszyscyJestesmyChrystusami = new ArrayList<Director>();
		directorsWszyscyJestesmyChrystusami.add(new Director("Marek", "Koterski", "Poland", 1942));
		
		s.put(new Location(0, 0), new Film("Wszyscy jesteśmy Chrystusami", directorsDzienSwira, 2007, FilmStatus.Available));
		Map<Location, Film> newFilm = new HashMap<Location, Film>();
		newFilm.put(new Location(0, 0), new Film("Dzień świra", directorsWszyscyJestesmyChrystusami, 2002, FilmStatus.Available));
		s.setExistedFilms(newFilm);
		
		assertSame(s.getExistedFilms().get(new Location(0, 0)), newFilm.get(new Location(0, 0)));
	}

}
