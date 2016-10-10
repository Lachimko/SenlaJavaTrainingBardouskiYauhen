import controllers.AlbumComposer;
import controllers.AlbumGrabber;
import model.AnyTypeOfComposition;
import model.SomeTypeOfComposition;
import model.parents.Album;
import model.parents.Composition;

public class Main {

	public static void main(String[] args) {
		
		Composition authorName0_Track0 = new SomeTypeOfComposition("authorName0 - Track0", "Rock", 255);
		Composition authorName1_Track1 = new SomeTypeOfComposition("authorName1 - Track1", "Pop", 210);
		Composition authorName2_Track2 = new SomeTypeOfComposition("authorName2 - Track2", "Pop", 214);
		Composition authorName3_Track3 = new AnyTypeOfComposition("authorName3 - Track3", "House", 365);
		Composition authorName4_Track4 = new AnyTypeOfComposition("authorName4 - Track4", "House", 400);
		Composition authorName5_Track5 = new AnyTypeOfComposition("authorName5 - Track5", "Techno", 335);
		
		AlbumComposer composer = new AlbumComposer();
		composer.setCollectionTemplateWithCompositions(authorName0_Track0, authorName1_Track1, authorName2_Track2);
		composer.getInfo();
		
		AlbumComposer composer2 = new AlbumComposer();
		composer.setCollectionTemplateWithCompositions(authorName3_Track3, authorName4_Track4, authorName5_Track5);
		composer.getInfo();
		
		AlbumGrabber grabber = new AlbumGrabber();
		Album album0 = grabber.makeCDwithComposerPlaylist("AlbumName", composer);
		Album album1 = grabber.makeCDwithComposerPlaylist("AlbumName2", composer2);
	}

}
