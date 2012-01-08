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

import project.Director;
import project.Film;

import services.DBFilm;
import statuses.FilmStatus;

public class DBFilmTest {

	DBFilm filmDBManager = new DBFilm();

	@Test
	public void testAddFilm() {
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		filmDBManager.addFilm(new Film("Snatch", directorsSnatch, 2000,
				FilmStatus.Available));
		assertTrue(filmDBManager.getAllFilms().size() > 0);
		assertEquals(1, filmDBManager.getAllFilms().size());
	}

	@Test
	public void testAddListOfFilms() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		filmDBManager.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertNotNull(filmDBManager.getAllFilms());
	}

	@Test
	public void testGetListIdFilm() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		filmDBManager.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertTrue(filmDBManager.getAllFilms().size() > 0);
	}

	@Test
	public void testGetIdFilmByTitle() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		filmDBManager.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertNotNull(filmDBManager.getIdFilmByTitle("Snatch"));
	}


	@Test
	public void testGetListIdFilmByTitle() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		filmDBManager.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertEquals(1, filmDBManager.getListIdFilmByTitle("Chinatown").size());
		assertTrue(filmDBManager.getListIdFilmByTitle("Chinatown").size() == 1);
		
	}

	@Test
	public void testGetListIdFilmByDirector() {
		

		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		filmDBManager.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertEquals(2, filmDBManager.getListIdFilmByDirector("Roman Polański").size());
		assertTrue(filmDBManager.getListIdFilmByDirector("Roman Polański").size() == 2);
	}

	@Test
	public void testGetIdFilmByYear() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		filmDBManager.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertEquals(1, filmDBManager.getIdFilmByYear(2000).size());
		assertTrue(filmDBManager.getIdFilmByYear(1974).size() == 1);
	}

	@Test
	public void testGetAllFilms() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		filmDBManager.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertTrue(filmDBManager.getAllFilms().size() == 3);
	}

	@Test
	public void testDeleteAllFilms() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		filmDBManager.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		filmDBManager.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		filmDBManager.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		filmDBManager.deleteAllFilms();
		assertEquals(0, filmDBManager.getAllFilms().size());

	}

}
