package bardouski.senla.training.converter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConverter<T> {

	public abstract String serialize(T goal);

	public String[] serialize(List<T> goalList) {

		String[] serializedValues = new String[goalList.size()];

		int i = 0;
		for (T listItem : goalList) {

			serializedValues[i++] = serialize(listItem);
		}
		return serializedValues;

	};

	public abstract T deserialize(String goal);

	public List<T> deserialize(Object[] goals) {

		List<T> list = new ArrayList<T>();

		for (int i = 0; i < goals.length; i++) {

			list.add(deserialize((String) goals[i]));
		}

		return list;
	}

}
