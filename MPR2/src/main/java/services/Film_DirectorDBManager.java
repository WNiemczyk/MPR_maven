package services;
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
			private PreparedStatement deleteFilm_DirectorStatement;
			private PreparedStatement deleteAllFilm_DirectorsStatement;
		
			
			public Film_DirectorDBManager() {

				try {

					connection = DriverManager
							.getConnection("jdbc:hsqldb:hsql://localhost/workdb");

					statement = connection.createStatement();
					boolean Film_DirectorTableExists = false;

					ResultSet rs = connection.getMetaData().getTables(null, null, null,
							null);

					while (rs.next()) {
						if ("Film_Director".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
							Film_DirectorTableExists = true;
							break;
						}
					}

					if (!Film_DirectorTableExists) {
						statement
								.executeUpdate("CREATE TABLE Film_Director(id_film INTEGER IDENTITY PRIMARY KEY, id_director Id INTEGER IDENTITY PRIMARY KEY)");
					}

					addFilm_DirectorStatement = connection
							.prepareStatement("INSERT INTO Film_Director (id_film, id_director) VALUES (?, ?)");

					getFilm_DirectorStatement = connection
							.prepareStatement("SELECT id_film, id_director FROM Film_Director");
				
					deleteFilm_DirectorStatement = connection
							.prepareStatement("DELETE FROM Film_Director WHERE id_film = ? AND id_director = ?");		
					
					deleteAllFilm_DirectorsStatement = connection
							.prepareStatement("DELETE FROM Film_Director");		
					
				}

				catch (SQLException e) {

					e.printStackTrace();

				}
			}

			public void addFilm_Director(Film f, Director d) {
				
				try {

					FilmDBManager filmDBManager = new FilmDBManager();
					DirectorDBManager directorDBManager = new DirectorDBManager();
					
					addFilm_DirectorStatement.setInt(1, filmDBManager.getIdFilmByTitle(f.getTitle()));
					addFilm_DirectorStatement.setInt(2, directorDBManager.getIdDirectorBySurname(d.getSurname())); 
					addFilm_DirectorStatement.executeUpdate();
				
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

			public void deleteFilm_Director(int id_f, int id_d){
				
				try {
					
					deleteFilm_DirectorStatement.setInt(1, id_f);
					deleteFilm_DirectorStatement.setInt(2, id_d);
					deleteFilm_DirectorStatement.executeUpdate();
				
				} 
				
				catch (SQLException e) {
					e.printStackTrace();
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


