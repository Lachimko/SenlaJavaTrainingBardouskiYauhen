package interfaces;

import controllers.AlbumComposer;
import model.parents.Album;

public interface IAlbumGrabber {

	Album makeCDwithComposerPlaylist(String albumTitle, AlbumComposer composer);
}
