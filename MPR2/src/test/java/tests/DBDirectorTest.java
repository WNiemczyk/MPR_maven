package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import project.Director;

import services.DBDirector;

public class DBDirectorTest {

	DBDirector DBdirector = new DBDirector();
	
	@Test
	public void testDBdirector() {
	
		DBdirector.addDirector(new Director("Roman", "Polański", "France", 1933));
		assertTrue(DBdirector.getAllDirectors().size() > 0);
	}

	@Test
	public void testAddDirector() {
		DBdirector.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		assertNotNull(DBdirector.getAllDirectors());
	}

	@Test
	public void testAddListOfDirectors() {
		DBdirector.addDirector(new Director("Roman", "Polański", "France", 1933));
		DBdirector.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		DBdirector.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertEquals(5, DBdirector.getAllDirectors().size());
	}

	@Test
	public void testGetAllDirectors() {
		DBdirector.addDirector(new Director("Roman", "Polański", "France", 1933));
		DBdirector.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		DBdirector.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertTrue(DBdirector.getAllDirectors().size() > 0);
	}

	@Test
	public void testGetListIdDirector() {
		DBdirector.addDirector(new Director("Roman", "Polański", "France", 1933));
		DBdirector.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		DBdirector.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertTrue(DBdirector.getListIdDirector().size() > 0);
	}

	@Test
	public void testGetIdDirectorBySurname() {
		DBdirector.addDirector(new Director("Roman", "Polański", "France", 1933));
		DBdirector.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		DBdirector.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertEquals(13, DBdirector.getListIdDirectorBySurname("Scorsese"));
	}

	@Test
	public void testDeleteDirector() {
		DBdirector.addDirector(new Director("Roman", "Polański", "France", 1933));
		DBdirector.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		DBdirector.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertTrue(DBdirector.getAllDirectors().size() == 3);
		DBdirector.deleteDirector(0);
		assertTrue(DBdirector.getAllDirectors().size() == 2);
	}

	@Test
	public void testDeleteAllDirectors() {
		DBdirector.addDirector(new Director("Roman", "Polański", "France", 1933));
		DBdirector.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		DBdirector.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertTrue(DBdirector.getAllDirectors().size() == 3);
		DBdirector.deleteAllDirectors();
		assertEquals(0, DBdirector.getAllDirectors().size());
	}

}
