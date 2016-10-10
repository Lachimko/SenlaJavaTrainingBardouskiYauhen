package controllers;

import interfaces.IAlbumComposer;
import model.parents.Composition;

/**
 * Methods setCollectionTemplateWithCompositions() - create compositions array;
 * from parameters defineGenres() - set into this.genre String with all unique genres; 
 * calculateDuration() - convert seconds into min:sec;
 * getInfo() - print info using console;
 */
public class AlbumComposer implements IAlbumComposer {

	private Composition[] compositions;
	private String genres;
	private String duration;

	private void setGenres(String genres) {
		this.genres = genres;
	}

	private void setDuration(String duration) {
		this.duration = duration;
	}

	public String getGenres() {
		return genres;
	}

	public String getDuration() {
		return duration;
	}

	private void setCompositions(Composition[] compositions) {
		this.compositions = compositions;
	}

	@Override
	public Composition[] getCompositions() {
		return compositions;
	}

	@Override
	public void getInfo() {
		
		System.out.println("**** ALBUM INFO ****");
		
		for (Composition composition : compositions) {
			
			System.out.printf("Composition: %s; time: %s seconds; genre: %s. \n", composition.getTitle(), composition.getSecondsDuration(), composition.getGenre());
		}
		
		System.out.println("Genres: " + this.getGenres());
		System.out.println("Full duration: " + this.getDuration());
		
		System.out.println("**** ********* ****");
	}

	@Override
	public void setCollectionTemplateWithCompositions(Composition... tracks) {
		this.setCompositions(tracks);
		defineGenres();
		calculateDuration();
	}

	private void calculateDuration() {

		StringBuffer sb = new StringBuffer();
		int summaryDuration = 0;

		for (Composition composition : compositions) {
			summaryDuration += composition.getSecondsDuration();
		}

		int minutes = summaryDuration / 60;
		int seconds = summaryDuration % 60;

		sb.append(minutes).append(" : ").append(seconds);

		setDuration(sb.toString());
	}

	private void defineGenres() {

		StringBuffer sb = new StringBuffer();
		sb.append(compositions[0].getGenre()).append(", ");

		for (int i = 1; i < compositions.length; i++) {
			if (!sb.toString().contains(compositions[i].getGenre())) {
				sb.append(compositions[i].getGenre()).append(", ");
			}
		}
		// Trimming last comma with space
		sb.setLength(sb.length() - 2);

		setGenres(sb.toString());
	}

}
