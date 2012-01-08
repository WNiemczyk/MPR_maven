package services;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.ResultSet;

import project.Director;
import project.Film;
import project.Film_Director;

public class Film_DirectorDBManager {

	private Connection connection;
	private Statement statement;
	private PreparedStatement addFilm_DirectorStatement;
	private PreparedStatement getFilm_DirectorStatement;
	private PreparedStatement getFilmAndFilm_DirectorStatement;
	private PreparedStatement deleteAllFilmByDirectorStatement;
	private PreparedStatement deleteFilm_DirectorStatement;
	private PreparedStatement deleteAllFilm_DirectorsStatement;

	public Film_DirectorDBManager() {

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
			boolean Film_DirectorTableExists = false;

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);

			while (rs.next()) {
				if ("Film_Director"
						.equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					Film_DirectorTableExists = true;
					break;
				}
			}

			if (!Film_DirectorTableExists) {
				statement
						.executeUpdate("CREATE TABLE Film_Director(id_film int, id_director int, PRIMARY KEY (id_film, id_director), FOREIGN KEY (id_film) REFERENCES film (id),  FOREIGN KEY (id_director) REFERENCES director (id))");
				// CONSTRAIN id_film_fk FOREIGN KEY id_film REFERENCES film(id),
				// CONSTRAIN id_director_fk FOREIGN KEY id_director REFERENCES
				// director(id)

			}

			addFilm_DirectorStatement = connection
					.prepareStatement("INSERT INTO Film_Director (id_film, id_director) VALUES (?, ?)");

			
			getFilm_DirectorStatement = connection
					.prepareStatement("SELECT id_film, id_director FROM Film_Director");
			
			
			getFilmAndFilm_DirectorStatement = connection.prepareStatement("SELECT Film.title, Film.director, Film.year, Film.status FROM Film, Film_Director WHERE id_director = ? AND id_film = Film.id");
			
			deleteAllFilmByDirectorStatement = connection.prepareStatement("DELETE FROM Film_Director WHERE id_director = ?");

			deleteAllFilm_DirectorsStatement = connection
					.prepareStatement("DELETE FROM Film_Director");

		}

		catch (SQLException e) {

			e.printStackTrace();

		}
	}

	public void addFilm_Director(Film f, Director d) {

		try {

			DBFilm filmDBManager = new DBFilm();
			DBDirector directorDBManager = new DBDirector();

			addFilm_DirectorStatement.setInt(1,
					filmDBManager.getIdFilmByTitle(f.getTitle()));
			addFilm_DirectorStatement.setInt(2,
					directorDBManager.getIdDirectorBySurname(d.getSurname()));
			addFilm_DirectorStatement.executeUpdate();

		}

		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void addListFilm_Director(List<Integer> f, List<Integer> d) {

		try {

			for (Integer filmId : f) {
				for (Integer directorId : d) {

					addFilm_DirectorStatement.setInt(1, filmId);
					addFilm_DirectorStatement.setInt(2, directorId);
					addFilm_DirectorStatement.executeUpdate();

				}
			}
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Film_Director> getAllFilm_Directors() {

		List<Film_Director> film_directors = new ArrayList<Film_Director>();

		try {

			ResultSet rs = getFilm_DirectorStatement.executeQuery();

			while (rs.next()) {

				film_directors.add(new Film_Director(rs.getInt("id_film"), rs
						.getInt("id_director")));
			}

			return film_directors;

		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	/*
	public void deleteFilm_Director(int id_f, int id_d) {

		try {

			deleteFilm_DirectorStatement.setInt(1, id_f);
			deleteFilm_DirectorStatement.setInt(2, id_d);
			deleteFilm_DirectorStatement.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	*/
	
	public void deleteFilm_Director(List<Integer> d) {

		for(Integer id_d : d){
			try {
				deleteAllFilmByDirectorStatement.setInt(1, id_d);
				deleteAllFilmByDirectorStatement.executeUpdate();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteAllFilm_Directors() {

		try {

			deleteAllFilm_DirectorsStatement.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
