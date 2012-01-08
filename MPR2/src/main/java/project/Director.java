package project;

import java.util.ArrayList;
import java.util.List;

public class Director {

	private String name;
	private String surname;
	private String countryOfBirth;
	private int yearOfBirth;
	
	public Director(){
		
	}
	
	public Director(String n, String s, String cob, int yob){
		
		this.name = n;
		this.surname = s;
		this.countryOfBirth = cob;
		this.yearOfBirth = yob;
	}

	public List<Director> init(){
		
		List<Director> directors = new ArrayList<Director>();
		directors.add(new Director("Roman", "Polański", "France", 1933));
		directors.add(new Director("Martin", "Scorsese", "USA", 1942));
		directors.add(new Director("Guy", "Ritchie", "Great Britain", 1968));
		directors.add(new Director("Susanne", "Bier", "Denmark", 1960));
		directors.add(new Director("Alex", "de la Iglesia", "Spain", 1965));
		directors.add(new Director("Ethan", "Coen", "USA", 1957));
		directors.add(new Director("Joel", "Coen", "USA", 1954));
		
		return directors;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountryOfBirth() {
		return countryOfBirth;
	}

	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
	
	
}
