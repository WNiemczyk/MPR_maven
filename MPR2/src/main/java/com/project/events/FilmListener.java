package com.project.events;

public interface FilmListener {

public void filmBorrowed(FilmEvent event);
public void filmReturned(FilmEvent event);
public void filmReserved(FilmEvent event);
public void filmAdded(FilmEvent event);

}