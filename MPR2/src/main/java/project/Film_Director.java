package project;

public class Film_Director {

	private int id_film;
	private int id_director;
	
	public Film_Director(){
		
	}
	
	public Film_Director(int filmId, int directorId){
		
		this.id_film = filmId;
		this.id_director = directorId;
		
	}

	public int getId_film() {
		return id_film;
	}

	public void setId_film(int id_film) {
		this.id_film = id_film;
	}

	public int getId_director() {
		return id_director;
	}

	public void setId_director(int id_director) {
		this.id_director = id_director;
	}

	
}
