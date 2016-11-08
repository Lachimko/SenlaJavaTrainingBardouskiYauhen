package bardouski.senla.training.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{

	private static final String PATTERN_CLASSNAME = "Task/";
	private static final String PATTERN_TODO = "toDo=";
	private static final String PATTERN_REQDATE = "requestDate=";
	private static final String PATTERN_STARTDATE = "startDate=";
	private static final String PATTERN_COMPLDATE = "completeDate=";
	private static final String PATTERN_PRICE = "price=";
	private static final String PATTERN_ENDFIELD = ";";

	private String toDo;
	private Date requestDate;
	private Date startDate;
	private Date completeDate;
	private double price;

	public Task(String toDo, Date requestDate, Date startDate, Date completeDate, double price) {
		this.toDo = toDo;
		this.requestDate = requestDate;
		this.startDate = startDate;
		this.completeDate = completeDate;
		this.price = price;
	}

	public String getToDo() {
		return toDo;
	}

	public void setToDo(String toDo) {
		this.toDo = toDo;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(PATTERN_CLASSNAME);
		sb.append(PATTERN_TODO).append(this.toDo).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_REQDATE).append(this.requestDate).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_STARTDATE).append(this.startDate).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_COMPLDATE).append(this.completeDate).append(PATTERN_ENDFIELD);
		sb.append(PATTERN_PRICE).append(this.price).append(PATTERN_ENDFIELD);

		return sb.toString();

	}

}
