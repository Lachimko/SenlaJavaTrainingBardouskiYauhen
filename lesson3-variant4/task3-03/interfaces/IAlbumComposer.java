package interfaces;

import model.parents.Composition;

public interface IAlbumComposer {
	
	void setCollectionTemplateWithCompositions(Composition ...tracks);
	
	Composition[] getCompositions();
	
	void getInfo();

}
