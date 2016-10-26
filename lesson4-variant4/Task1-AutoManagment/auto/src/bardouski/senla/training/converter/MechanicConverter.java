package bardouski.senla.training.converter;

import bardouski.senla.training.model.Mechanic;

public class MechanicConverter extends AbstractConverter<Mechanic> {

	private static final String PATTERN_ID = "id=";
	private static final String PATTERN_FULLNAME = "fullName=";
	private static final String PATTERN_ENDFIELD = ";";

	@Override
	public String serialize(Mechanic goal) {

		return goal.toString();
	}

	@Override
	public Mechanic deserialize(String goal) {

		int mechanicId;
		String mechanicFullName;

		int startFieldPosition;
		int endFieldPosition;

		// mechanic ID parse
		startFieldPosition = goal.indexOf(PATTERN_ID) + PATTERN_ID.length();
		endFieldPosition = goal.indexOf(PATTERN_ENDFIELD, startFieldPosition);
		mechanicId = Integer.parseInt((goal.substring(startFieldPosition, endFieldPosition)));

		// mechanic FULLNAME parse
		startFieldPosition = goal.indexOf(PATTERN_FULLNAME) + PATTERN_FULLNAME.length();
		endFieldPosition = goal.indexOf(PATTERN_ENDFIELD, startFieldPosition);
		mechanicFullName = goal.substring(startFieldPosition, endFieldPosition);

		return new Mechanic(mechanicId, mechanicFullName, null);
	}

}
