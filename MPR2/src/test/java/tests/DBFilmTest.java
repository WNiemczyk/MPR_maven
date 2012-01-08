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

	DBFilm DBfilm = new DBFilm();
	

	@Test
	public void testAddFilm() {

		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		DBfilm.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));

		assertTrue(DBfilm.getAllFilms().size() > 0);
		assertEquals(1, DBfilm.getAllFilms().size());
	}

	@Test
	public void testAddListOfFilms() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		DBfilm.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		DBfilm.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		DBfilm.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertNotNull(DBfilm.getAllFilms());
	}

	@Test
	public void testGetListIdFilm() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		DBfilm.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		DBfilm.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		DBfilm.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertTrue(DBfilm.getListIdFilm().size() > 0);
	}

	@Test
	public void testGetIdFilmByTitle() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		DBfilm.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		DBfilm.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		DBfilm.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertNotNull(DBfilm.getIdFilmByTitle("Snatch"));
	}


	@Test
	public void testGetListIdFilmByTitle() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		DBfilm.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		DBfilm.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		DBfilm.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertEquals(1, DBfilm.getListIdFilmByTitle("Chinatown").size());
		assertTrue(DBfilm.getListIdFilmByTitle("Chinatown").size() == 1);
		
	}

	@Test
	public void testGetListIdFilmByDirector() {
		

		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		DBfilm.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		DBfilm.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		DBfilm.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertEquals(2, DBfilm.getListIdFilmByDirector("Roman Polański").size());
		assertTrue(DBfilm.getListIdFilmByDirector("Roman Polański").size() == 2);
	}

	@Test
	public void testGetIdFilmByYear() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		DBfilm.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		DBfilm.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		DBfilm.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertEquals(1, DBfilm.getIdFilmByYear(2000).size());
		assertTrue(DBfilm.getIdFilmByYear(1974).size() == 1);
	}

	@Test
	public void testGetAllFilms() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		DBfilm.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		DBfilm.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		DBfilm.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		assertTrue(DBfilm.getAllFilms().size() == 3);
	}

	@Test
	public void testDeleteAllFilms() {
		
		List<Director> directorsChinatown = new ArrayList<Director>();
		directorsChinatown.add(new Director("Roman", "Polański", "France", 1933));
		
		List<Director> directorsSnatch = new ArrayList<Director>();
		directorsSnatch.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		
		List<Director> directorsNozWWodzie = new ArrayList<Director>();
		directorsNozWWodzie.add(new Director("Roman", "Polański", "France", 1933));
		
		DBfilm.addFilm(new Film("Chinatown", directorsChinatown, 1974,
				FilmStatus.Available));
		DBfilm.addFilm(new Film("Snatch", directorsSnatch, 2000, FilmStatus.Available));
		DBfilm.addFilm(new Film("Nóż w wodzie", directorsNozWWodzie, 1961,
				FilmStatus.Available));
		DBfilm.deleteAllFilms();
		assertEquals(0, DBfilm.getAllFilms().size());

	}

}
