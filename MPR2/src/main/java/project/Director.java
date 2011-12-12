package project;

public class Director {

	private String name;
	private String surname;
	private String countryOfBirth;
	private int yearOfBirth;
	
	public Director(String n, String s, String cob, int yob){
		
		this.name = n;
		this.surname = s;
		this.countryOfBirth = cob;
		this.yearOfBirth = yob;
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
