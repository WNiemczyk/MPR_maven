package com.project.events;

import java.util.EventObject;

import com.project.films.Film;
import com.project.films.Shelf;



public class FilmEvent extends EventObject {

private static final long serialVersionUID = 1L;

private Film _film;

public FilmEvent(Object source, Film film) {
super(source);
_film = film;
}

public Film getFilm() {
return _film;
}

}