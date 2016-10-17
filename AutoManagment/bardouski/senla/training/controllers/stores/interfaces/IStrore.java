package bardouski.senla.training.controllers.stores.interfaces;

public interface IStrore<T> {

	//void create(T obj);
	
	void read(T obj);
	
	void delete(T obj);
	
//	default String viewAll(){
//
//		String string = getDBString();
//
//		ArrayList<String> arrayofStringObjects = new ArrayList<>();
//
//		System.out.println("CURRENT: " + string);
//
//		String whatFindStart = "{!@";
//		String whatFindEnd = "}";
//
//		int lastStringIndex = 0;
//		int startStringIndex = 0;
//
//		while (true) {
//
//			startStringIndex = string.indexOf(whatFindStart, startStringIndex);
//			lastStringIndex = string.indexOf(whatFindEnd, startStringIndex);
//
//			if (startStringIndex < 0) {
//				break;
//			}
//
//			String curr = string.substring(startStringIndex, lastStringIndex);
//			arrayofStringObjects.add(curr);
//			startStringIndex = lastStringIndex + 1;
//		}
//
//		return arrayofStringObjects;
//	};
}
