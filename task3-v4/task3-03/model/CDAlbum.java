package model;

import model.parents.Album;
import model.parents.Composition;

public class CDAlbum extends Album {

	public CDAlbum(String title, String duration, String genres, Composition[] compositions) {
		super(title, duration, genres, compositions);
	}


}
