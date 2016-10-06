class Main{

	public static void main(String[] args){

		Plane plane = new Plane();
		CargoPlane cargoPlane = new CargoPlane();
		
		Pilot pilot = new Pilot();
		
		Passenger passenger = new Passenger();

		Route route = new Route();
		route.setPassengers(new Passenger[10]);		
		
		PassengerPlane passPlane = new PassengerPlane();
		passPlane.setPilot(pilot);		
		passPlane.setRoute(route);
	
	}

}