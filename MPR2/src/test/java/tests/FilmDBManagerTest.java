package tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import project.Film;

import services.FilmDBManager;
import statuses.FilmStatus;

public class FilmDBManagerTest {

	FilmDBManager filmDBManager = new FilmDBManager();

	@Test
	public void testAddFilm() {
		filmDBManager.addFilm(new Film("Snatch", "Guy Ritchie", 2000,
				FilmStatus.Available));
		assertTrue(filmDBManager.getAllFilms().size() > 0);
		assertEquals(1, filmDBManager.getAllFilms().size());
	}

	@Test
	public void testAddListOfFilms() {
		filmDBManager.addFilm(new Film("Chinatown", "Roman Polański", 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", "Guy Ritchie", 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", "Roman Polański", 1961,
				FilmStatus.Available));
		assertNotNull(filmDBManager.getAllFilms());
	}

	@Test
	public void testGetListIdFilm() {
		filmDBManager.addFilm(new Film("Chinatown", "Roman Polański", 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", "Guy Ritchie", 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", "Roman Polański", 1961,
				FilmStatus.Available));
		assertTrue(filmDBManager.getAllFilms().size() > 0);
	}

	@Test
	public void testGetIdFilmByTitle() {
		filmDBManager.addFilm(new Film("Chinatown", "Roman Polański", 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", "Guy Ritchie", 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", "Roman Polański", 1961,
				FilmStatus.Available));
		assertNotNull(filmDBManager.getIdFilmByTitle("Snatch"));
	}


	@Test
	public void testGetListIdFilmByTitle() {
		filmDBManager.addFilm(new Film("Chinatown", "Roman Polański", 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", "Guy Ritchie", 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", "Roman Polański", 1961,
				FilmStatus.Available));
		assertEquals(1, filmDBManager.getListIdFilmByTitle("Chinatown").size());
		assertTrue(filmDBManager.getListIdFilmByTitle("Chinatown").size() == 1);
		
	}

	@Test
	public void testGetListIdFilmByDirector() {
		filmDBManager.addFilm(new Film("Chinatown", "Roman Polański", 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", "Guy Ritchie", 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", "Roman Polański", 1961,
				FilmStatus.Available));
		assertEquals(2, filmDBManager.getListIdFilmByDirector("Roman Polański").size());
		assertTrue(filmDBManager.getListIdFilmByDirector("Roman Polański").size() == 2);
	}

	@Test
	public void testGetIdFilmByYear() {
		filmDBManager.addFilm(new Film("Chinatown", "Roman Polański", 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", "Guy Ritchie", 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", "Roman Polański", 1961,
				FilmStatus.Available));
		assertEquals(1, filmDBManager.getIdFilmByYear(2000).size());
		assertTrue(filmDBManager.getIdFilmByYear(1974).size() == 1);
	}

	@Test
	public void testGetAllFilms() {
		filmDBManager.addFilm(new Film("Chinatown", "Roman Polański", 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", "Guy Ritchie", 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", "Roman Polański", 1961,
				FilmStatus.Available));
		assertTrue(filmDBManager.getAllFilms().size() == 3);
	}

	@Test
	public void testDeleteAllFilms() {
		filmDBManager.addFilm(new Film("Chinatown", "Roman Polański", 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", "Guy Ritchie", 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", "Roman Polański", 1961,
				FilmStatus.Available));
		filmDBManager.deleteAllFilms();
		assertEquals(0, filmDBManager.getAllFilms().size());

	}

}
