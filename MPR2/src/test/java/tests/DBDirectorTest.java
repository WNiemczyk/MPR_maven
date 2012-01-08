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

	DBDirector directorDBManager = new DBDirector();
	
	@Test
	public void testDirectorDBManager() {
		directorDBManager.addDirector(new Director("Roman", "Polański", "France", 1933));
		assertTrue(directorDBManager.getAllDirectors().size() > 0);
	}

	@Test
	public void testAddDirector() {
		directorDBManager.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		assertNotNull(directorDBManager.getAllDirectors());
	}

	@Test
	public void testAddListOfDirectors() {
		directorDBManager.addDirector(new Director("Roman", "Polański", "France", 1933));
		directorDBManager.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		directorDBManager.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertEquals(3, directorDBManager.getAllDirectors().size());
	}

	@Test
	public void testGetAllDirectors() {
		directorDBManager.addDirector(new Director("Roman", "Polański", "France", 1933));
		directorDBManager.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		directorDBManager.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertTrue(directorDBManager.getAllDirectors().size() > 0);
	}

	@Test
	public void testGetListIdDirector() {
		directorDBManager.addDirector(new Director("Roman", "Polański", "France", 1933));
		directorDBManager.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		directorDBManager.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertTrue(directorDBManager.getListIdDirector().size() > 0);
	}

	@Test
	public void testGetIdDirectorBySurname() {
		directorDBManager.addDirector(new Director("Roman", "Polański", "France", 1933));
		directorDBManager.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		directorDBManager.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertEquals(1, directorDBManager.getListIdDirectorBySurname("Scorsese"));
	}

	@Test
	public void testDeleteDirector() {
		directorDBManager.addDirector(new Director("Roman", "Polański", "France", 1933));
		directorDBManager.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		directorDBManager.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertTrue(directorDBManager.getAllDirectors().size() == 3);
		directorDBManager.deleteDirector(0);
		assertTrue(directorDBManager.getAllDirectors().size() == 2);
	}

	@Test
	public void testDeleteAllDirectors() {
		directorDBManager.addDirector(new Director("Roman", "Polański", "France", 1933));
		directorDBManager.addDirector(new Director("Martin", "Scorsese", "USA", 1942));
		directorDBManager.addDirector(new Director("Guy", "Ritchie", "Great Britain", 1968));
		assertTrue(directorDBManager.getAllDirectors().size() == 3);
		directorDBManager.deleteAllDirectors();
		assertEquals(0, directorDBManager.getAllDirectors().size());
	}

}
