package bardouski.senla.training.model.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;

import bardouski.senla.training.com.danco.training.TextFileWorker;
import bardouski.senla.training.controllers.managers.OrderManager;

/**
 * Contains itself the DB Object in String format, and provide functional to
 * initialize, check if db connect and etc.After instance of DBDriver was
 * create, need to initialize.
 * 
 * Methods: void init(String dbFilePath); boolean isConnected(); String
 * getDBString(); void writeToDB(String ObjectToWrite); syncronize()
 * 
 */
public class DBDriver {

	private DB db;
	private TextFileWorker textFileWorker;

	/**
	 * textFileWorker check filepath, and read strings from file. If file not
	 * found textFileWorker itself process exception. If readed string is empty,
	 * textFileWorker will return new String[0]. Next step create locally create
	 * instance of DB using returned textFileWorker String param in constructor
	 * 
	 * @param dbFilePath
	 *            path at your local db(dbFilePath)
	 */
	public void init(String dbFilePath) {

		if (textFileWorker == null) {
			textFileWorker = new TextFileWorker(dbFilePath);
		}

		String stringFromDB = getRemoteDBString();
		this.db = (stringFromDB.length() > 0) ? new DB(stringFromDB) : new DB("");
	}

	/** Check value of DB String */
	public boolean isConnected() {

		return (db == null && db.getDb().isEmpty()) ? false : true;
	}

	/** Return a String cloned from DB object */
	public String getDBString() {

		return (isConnected()) ? this.db.getDb() : null;
	}

	/** Return a String from remote DB */
	private String getRemoteDBString() {

		String[] arrFromDB = textFileWorker.readFromFile();
		return (arrFromDB.length > 0) ? arrFromDB[0] : "";
	}

	/** fill DB by new content */
	public void writeToRemoteDB(String ObjectToWrite) {

		String[] values = new String[] { ObjectToWrite };
		
		try {
	        FileWriter fstream1 = new FileWriter(OrderManager.DB_PATH);// конструктор с одним параметром - для перезаписи
	        BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
	        out1.write(""); // очищаем, перезаписав поверх пустую строку
	         out1.close(); // закрываем
	         } catch (Exception e) 
	            {System.err.println("Error in file cleaning: " + e.getMessage());}
		
		textFileWorker.writeToFile(values);

		syncronize();
	}

	/** Rewrite remote DB into local */
	private void syncronize() {

		String remoteDBString = getRemoteDBString();
		if (!getDBString().equals(remoteDBString)) {
			db.setDb(remoteDBString);
		}
	}

	/** Add String to remote */
	public void addToRemoteDB(String ObjectToAdd) {

		//String currentDB = getRemoteDBString();

		String[] values = new String[] { ObjectToAdd };

		textFileWorker.writeToFile(values);

		syncronize();
	}

	/** true if db contains compareString */
	public boolean isContains(String compareString) {

		return (getDBString().contains(compareString)) ? true : false;

	}

	public void replaceStringIntoDB(String oldString, String newString){
		
		
	}
}
