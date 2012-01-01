package services;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import project.Film;
import statuses.FilmStatus;

public class FilmDBManager {

	private Connection connection;
	private Statement statement;
	private PreparedStatement addFilmStatement;
	private PreparedStatement getFilmStatement;
	private PreparedStatement deleteFilmStatement;
	private PreparedStatement deleteAllFilmsStatement;
	private PreparedStatement findFilmStatement;
	private PreparedStatement findFilmStatementByTitle;
	private PreparedStatement findFilmStatementByDirector;
	private PreparedStatement findFilmStatementByYear;

	public FilmDBManager() {

		try {

			Properties props = new Properties();

			try {
				props.load(ClassLoader
						.getSystemResourceAsStream("jdbc.properties"));
			}

			catch (IOException e) {

				e.printStackTrace();

			}

			connection = DriverManager.getConnection(props.getProperty("url"));

			statement = connection.createStatement();
			boolean filmTableExists = false;

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);

			while (rs.next()) {
				if ("Film".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					filmTableExists = true;
					break;
				}
			}

			if (!filmTableExists) {
				statement
						.executeUpdate("CREATE TABLE film(id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, title varchar(20), director varchar(40), year integer, status varchar(20))");
			}

			addFilmStatement = connection
					.prepareStatement("INSERT INTO film (title, director, year, status) VALUES (?, ?, ?, ?)");

			getFilmStatement = connection
					.prepareStatement("SELECT id, title, director, year, status FROM film");

			deleteAllFilmsStatement = connection
					.prepareStatement("DELETE FROM film");

			findFilmStatement = connection
					.prepareStatement("SELECT id FROM Film");

			findFilmStatementByTitle = connection
					.prepareStatement("SELECT id FROM Film WHERE title = ?");

			findFilmStatementByDirector = connection
					.prepareStatement("SELECT id FROM Film WHERE director = ?");

			findFilmStatementByYear = connection
					.prepareStatement("SELECT id FROM Film WHERE year = ?");

			deleteFilmStatement = connection
					.prepareStatement("DELETE FROM Film WHERE id = ?");
			
			deleteAllFilmsStatement = connection.prepareStatement("DELETE FROM Film");

		}

		catch (SQLException e) {

			e.printStackTrace();

		}
	}

	public void addFilm(Film film) {

		try {

			addFilmStatement.setString(1, film.getTitle());
			addFilmStatement.setString(2, film.getDirector());
			addFilmStatement.setInt(3, film.getYear());
			addFilmStatement.setString(4, film.getStatus().toString()); // obiekt
			addFilmStatement.executeUpdate();

		}

		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void addListOfFilms(List<Film> films) {

		for (Film f : films)
			addFilm(f);
	}

	public List<Integer> getListIdFilm() {

		List<Integer> foundedIds = new ArrayList<Integer>();

		try {
			ResultSet rs = findFilmStatement.executeQuery();
			while (rs.next())
				foundedIds.add(rs.getInt("ID"));

			return foundedIds;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int getIdFilmByTitle(String t) {

		int foundedId = -1;

		try {

			findFilmStatementByTitle.setString(1, t);
			ResultSet rs = findFilmStatementByTitle.executeQuery();
			while (rs.next()) {

				foundedId = rs.getInt("ID");
				return foundedId;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}


	public List<Integer> getListIdFilmByTitle(String t) {

		List<Integer> foundedIds = new ArrayList<Integer>();

		try {
			findFilmStatementByTitle.setString(1, t);
			ResultSet rs = findFilmStatementByTitle.executeQuery();

			while (rs.next()) {

				foundedIds.add(rs.getInt("ID"));
			}
			return foundedIds;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	public void addCondition(String d, FilmDBManagerCondition fc) {
		List<Integer> ids = getListIdFilmByDirector(d);
		for (Integer i : ids) {
			if (fc.getCondition(i)) {
				System.out.println("Counter for director " + d + " who has more than one film in database.");
			}

		}

	}

	public List<Integer> getListIdFilmByDirector(String d) {

		List<Integer> foundedIds = new ArrayList<Integer>();

		try {
			findFilmStatementByDirector.setString(1, d);
			ResultSet rs = findFilmStatementByDirector.executeQuery();
			while (rs.next()) {
				foundedIds.add(rs.getInt("ID"));
			}
			return foundedIds;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	public List<Integer> getIdFilmByYear(int y) {

		List<Integer> foundedIds = new ArrayList<Integer>();

		try {

			findFilmStatementByYear.setInt(1, y);
			ResultSet rs = findFilmStatementByYear.executeQuery();
			while (rs.next()) {

				foundedIds.add(rs.getInt("ID"));
			}
			return foundedIds;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Film> getAllFilms() {

		List<Film> films = new ArrayList<Film>();

		try {

			ResultSet rs = getFilmStatement.executeQuery();

			while (rs.next()) {

				FilmStatus status = null;

				if (rs.getString("status").equals("Available"))
					status = FilmStatus.Available;
				if (rs.getString("status").equals("Borrowed"))
					status = FilmStatus.Borrowed;
				if (rs.getString("status").equals("Reserved"))
					status = FilmStatus.Reserved;

				films.add(new Film(rs.getString("title"), rs
						.getString("director"), rs.getInt("year"), status));
			}

		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		return films;
	}

	public void deleteFilm(int id) {

		try {

			deleteFilmStatement.setInt(1, id); // w miejsce ? wstawia zmienną
												// (tutaj id)
			deleteFilmStatement.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAllFilms() {

		try {

			deleteAllFilmsStatement.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
