package controllers;

import interfaces.IAlbumGrabber;
import model.CDAlbum;
import model.parents.Album;

public class AlbumGrabber implements IAlbumGrabber {

	@Override
	public Album makeCDwithComposerPlaylist(String albumTitle, AlbumComposer composer) {

		Album alb = new CDAlbum(albumTitle, composer.getDuration(), composer.getGenres(), composer.getCompositions());
		return alb;
	}

}
