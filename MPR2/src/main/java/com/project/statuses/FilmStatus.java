package com.project.statuses;

public enum FilmStatus {
	
	Available("Available"),
	Borrowed("Borrowed"),
	Reserved("Reserved");
	
	private String description;
	
	private FilmStatus(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
}


