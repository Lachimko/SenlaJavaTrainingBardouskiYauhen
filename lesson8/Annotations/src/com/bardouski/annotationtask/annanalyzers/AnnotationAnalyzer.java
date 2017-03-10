package com.bardouski.annotationtask.annanalyzers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.annotationtask.annotations.PrintableRef;

public class AnnotationAnalyzer {

	private static final String ERROR_DATA = "Error data";
	private List<Object> referencesMirror = new ArrayList<>();

	/**
	 * Check annotations in Class of instance passed by method parameter, if
	 * instance class has not @PrintableObject annotation, it can't be returned
	 * and scanned further. If class has @PrintableObject annotation it means
	 * that the instance can be written in line and it's fields will be scanned
	 * ;
	 * 
	 */
	public String inspectAnnotations(Object instance) {

		Class<?> clazz = instance.getClass();
		referencesMirror.add(instance);
		StringBuilder string = new StringBuilder();

		if (clazz.isAnnotationPresent(PrintableRef.class)) {
			PrintableRef printableRef = clazz.getAnnotation(PrintableRef.class);
			string.append(printableRef.name()).append("{");
			try {
				return inspectFieldsOfObject(instance);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return null;
			}

		} else if (clazz.isAnnotationPresent(PrintableObject.class)) {
			PrintableObject printableObject = clazz.getAnnotation(PrintableObject.class);
			string.append(printableObject.name()).append("{");
			try {
				return inspectFieldsOfObject(instance);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return null;
			}
		}

		return string.toString();
	}

	/**
	 * Inspect field annotations, and build string value. Check @PrintableRef
	 * and @Printable annotations. Slice Object in parameter in fields, build
	 * String view, send it to temporary ArrayList. After convert ArrayList to
	 * Strings
	 */
	private String inspectFieldsOfObject(Object instance) throws IllegalArgumentException, IllegalAccessException {

		Field[] fields = instance.getClass().getDeclaredFields();
		StringBuilder string = new StringBuilder();
		List<String> tempList = new ArrayList<>();

		for (Field field : fields) {

			if (field.isAnnotationPresent(PrintableRef.class)) {
				PrintableRef printableRef = field.getAnnotation(PrintableRef.class);
				string.append(printableRef.name()).append("{");

				if (printableRef.isDetailedView()) {
					Object objectInThisField = getFieldValue(field, instance);

					if (objectInThisField == null) {
						String prepareObjectToString = new StringBuilder().append(field.getName()).append("=null")
								.toString();
						tempList.add(printableRef.order(), prepareObjectToString);
					} else {

						if (referencesMirror.contains(objectInThisField)) {
							String prepareObjectToString = new StringBuilder().append(field.getName()).append("=")
									.append(objectInThisField).toString();
							tempList.add(printableRef.order(), prepareObjectToString);
						} else {
							referencesMirror.add(objectInThisField);
							String prepareObjectToString = new StringBuilder().append(field.getName()).append("=")
									.append(inspectFieldsOfObject(objectInThisField)).toString();

							tempList.add(printableRef.order(), prepareObjectToString);
						}
					}

				} else {
					tempList.add(printableRef.order(), getFieldValue(field, instance).toString());
				}

			} else {

				if (field.isAnnotationPresent(Printable.class)) {
					Printable printable = field.getAnnotation(Printable.class);

					if (printable.isDetailedOnly()) {
						String tempString = new StringBuffer().append(printable.name()).append("=")
								.append(getFieldValue(field, instance)).toString();
						tempList.add(printable.order(), tempString);
					} else {
						tempList.add(printable.order(), printable.name());
					}

				}
			}
		}

		return convertListToString(tempList, string);
	}

	/**
	 * Return Object from field value. If field access is closed method opens
	 * it, return value, and set access modifier back. Return "Error data" if
	 * method is unable to change access modifier or wrong Object instance was
	 * passed in.
	 */
	private Object getFieldValue(Field field, Object instance) {

		if (!field.isAccessible()) {
			try {
				field.setAccessible(true);
				return field.get(instance);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return ERROR_DATA;
			} finally {
				field.setAccessible(false);
			}

		} else {
			try {
				return field.get(instance).toString();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return ERROR_DATA;
			}
		}

	}

	/**
	 * Convert list values to String. Replace last element to "}" for objects
	 * visually be closed
	 */
	private String convertListToString(List<String> list, StringBuilder StringContainer) {

		for (String listString : list) {
			StringContainer.append(listString).append(",");
		}
		StringContainer.replace(StringContainer.length() - 1, StringContainer.length(), "}");

		return StringContainer.toString();
	}
}
