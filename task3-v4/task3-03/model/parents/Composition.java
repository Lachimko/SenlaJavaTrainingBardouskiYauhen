package model.parents;

public class Composition {

	private String title;
	private String genre;
	private int secondsDuration;

	public Composition(String title, String genre, int secondsDuration) {
		this.title = title;
		this.genre = genre;
		this.secondsDuration = secondsDuration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String author) {
		this.title = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getSecondsDuration() {
		return secondsDuration;
	}

	public void setSecondsDuration(int secondsDuration) {
		this.secondsDuration = secondsDuration;
	}

}
