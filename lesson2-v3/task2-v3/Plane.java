class Plane{

	private long id;
	Pilot pilot;
	Route route;

	public void setPilot(Pilot pilot){

		this.pilot = pilot;

	}


	public void setRoute(Route route){

		this.route = route;

	}

	public Plane(){

		System.out.println("Plane constructor");	

	}
}