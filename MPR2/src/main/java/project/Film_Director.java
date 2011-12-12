package project;

public class Film_Director {

	private int id_film;
	private int id_director;
	
	public Film_Director(){
		
	}
	
	public Film_Director(int id_f, int id_d){
		this.id_film = id_f;
		this.id_director = id_d;
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
