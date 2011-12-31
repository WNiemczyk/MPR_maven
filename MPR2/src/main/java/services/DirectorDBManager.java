package services;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.ResultSet;

import project.Director;

public class DirectorDBManager {
		
		private Connection connection;
		private Statement statement;
		private PreparedStatement addDirectorStatement;
		private PreparedStatement getDirectorStatement;
		private PreparedStatement findDirectorStatement;
		private PreparedStatement findDirectorStatementBySurname;
		private PreparedStatement deleteDirectorStatement;
		private PreparedStatement deleteAllDirectorsStatement;
	
		
		public DirectorDBManager() {

				try {
				
				Properties props = new Properties();

				try {
				props.load(ClassLoader.getSystemResourceAsStream("jdbc.properties"));
				} 
				
				catch (IOException e) {
					
				e.printStackTrace();
				
				}
			
				connection = DriverManager.getConnection(props.getProperty("url"));


				statement = connection.createStatement();
				boolean directorTableExists = false;

				ResultSet rs = connection.getMetaData().getTables(null, null, null,
						null);

				while (rs.next()) {
					if ("Director".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
						directorTableExists = true;
						break;
					}
				}

				if (!directorTableExists) {
					statement
							.executeUpdate("CREATE TABLE director(id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, name varchar(20), surname varchar(40), countryOfBirth varchar(20), yearOfBirth integer)");
				}

				addDirectorStatement = connection
						.prepareStatement("INSERT INTO director (name, surname, countryOfBirth, yearOfBirth) VALUES (?, ?, ?, ?)");

				getDirectorStatement = connection
						.prepareStatement("SELECT id, name, surname, countryOfBirth, yearOfBirth FROM director");
			
				findDirectorStatement = connection.prepareStatement("SELECT id FROM director");
				
				findDirectorStatementBySurname = connection.prepareStatement("SELECT id FROM director WHERE surname = ?");
				
				deleteAllDirectorsStatement = connection
						.prepareStatement("DELETE FROM director");
			
				deleteDirectorStatement = connection.prepareStatement("DELETE FROM director WHERE id = ?");
				
				
				
			}

			catch (SQLException e) {

				e.printStackTrace();

			}
		}

		public void addDirector(Director d) {
			
			try {

				addDirectorStatement.setString(1, d.getName());
				addDirectorStatement.setString(2, d.getSurname());
				addDirectorStatement.setString(3, d.getCountryOfBirth());
				addDirectorStatement.setInt(4, d.getYearOfBirth()); 
				addDirectorStatement.executeUpdate();
			
			}

			catch (SQLException e) {

				e.printStackTrace();
			}

		}

		public void addListOfDirectors(List<Director> director) {

			for (Director d : director)
				addDirector(d);
		}

		
		
		public List<Director> getAllDirectors() {

			List<Director> directors = new ArrayList<Director>();

			try {

				ResultSet rs = getDirectorStatement.executeQuery();

				while (rs.next()) {
					
					directors.add(new Director(rs.getString("name"), rs
							.getString("surname"), rs.getString("countryOfBirth"), rs.getInt("yearOfBirth")));
				}
				
				return directors;

			}

			catch (SQLException e) {

				e.printStackTrace();
			}

			return null;
		}
		
		public List<Integer> getListIdDirector(){
			
			List<Integer> foundedIds = new ArrayList<Integer>();
			
			try {
				ResultSet rs = findDirectorStatement.executeQuery();
				while(rs.next())
					foundedIds.add(rs.getInt("ID"));
				
				return foundedIds;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			return null;
		}
		
		
		
		public int getIdDirectorBySurname(String s) {

			int foundedId = -1;
			
			try {
				
				findDirectorStatementBySurname.setString(1, s);
				ResultSet rs = findDirectorStatementBySurname.executeQuery();
				while(rs.next()){
					
					foundedId = rs.getInt("ID");
					return foundedId;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return -1;
		}
		

		public List<Integer> getListIdDirectorBySurname(String s) {

			List<Integer> foundedIds = new ArrayList<Integer>();
			
			try {
				
				findDirectorStatementBySurname.setString(1, s);
				ResultSet rs = findDirectorStatementBySurname.executeQuery();
				while(rs.next()){
					
					foundedIds.add(rs.getInt("ID"));
					return foundedIds;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		public void deleteDirector(int id){
			
			try {
				
				deleteDirectorStatement.setInt(1, id);
				deleteDirectorStatement.executeUpdate();
			
			} 
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void deleteAllDirectors() {

			try {

				deleteAllDirectorsStatement.executeUpdate();
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


