package model.parents;

public class Album {
	
	private String title;
	private String duration;
	private String genres;
	private Composition[] compositions;
	
	public Album(String title, String duration, String genres, Composition[] compositions) {
		super();
		this.title = title;
		this.duration = duration;
		this.genres = genres;
		this.compositions = compositions;
	}

	public String getDuration() {
		return duration;
	}

	public String getGenres() {
		return genres;
	}

	public String getTitle() {
		return title;
	}

	public Composition[] getCompositions() {
		return compositions;
	}

}
