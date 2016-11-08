package bardouski.senla.training.serializator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.log4j.Logger;


public class Serializator {
	
	private Logger logger = Logger.getLogger(Serializator.class.getSimpleName());
	
	private static final String IO_ERROR = "IO Error";
	private static final String NO_DB_FILE = "DB File did not find";

	private String dbPath;
	
	public Serializator(String dbPath) throws FileNotFoundException {
		this.dbPath = dbPath;
		
		if (!(new File(dbPath)).exists()) {
			logger.fatal(NO_DB_FILE);
			throw new FileNotFoundException();
		} 
	}

	/**Deserialise data from file. Returns List<List<Object>>*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<List> getLists() {
		
		try(FileInputStream fileInputStream = new FileInputStream(dbPath);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);){
			
			return  (List<List>) objectInputStream.readObject();
			
		} catch (IOException e) {
			logger.error("IO Exception throwed in getLists");
		} catch (ClassNotFoundException e) {
			logger.fatal("Serialization fail");
		}
		return null; 
	}

	/**Serialize and write to DB lists from params*/
	@SuppressWarnings("rawtypes")
	public void writeToDB(List<List> lists){
		
		try(FileOutputStream fileOutputStream = new FileOutputStream(dbPath);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);){
			objectOutputStream.writeObject(lists);
		} catch (FileNotFoundException e) {
			logger.fatal(NO_DB_FILE);
		} catch (IOException e) {
			logger.error(IO_ERROR);
		}
	}

}
