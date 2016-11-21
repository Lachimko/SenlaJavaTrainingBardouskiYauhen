package com.bardouski.program.dbprocessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bardouski.program.model.Mechanic;

/**
 * Container for Import/Export Operations. Works only with Mechanic collection.
 */
public final class DBCSVProcessor implements IDBCSVProcessor {

	private Path path;
	
	public DBCSVProcessor(String path) throws InvalidPathException {
		this.path = Paths.get(path);
	}

	public DBCSVProcessor(){
	}
	
	public void setPath(String path) {
		this.path = Paths.get(path);
	}

	/** Write String list to CSV */
	private void exportCollection(List<String> list) {

		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.WRITE)) {

			for (String value : list) {
				bufferedWriter.write(value + "\n");
			}

		} catch (IOException e) {
			Logger.getLogger(this.getClass().getSimpleName()).error(e.getMessage());
		}
	}

	/** return Mechanic list, which was splited from CSV Strings */
	public List<Mechanic> importCollection() {

		List<Mechanic> importedMechanics = new ArrayList<>();
		try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {

			String readed;
			while ((readed = bufferedReader.readLine()) != null) {

				String[] result = readed.split(",");
				Mechanic temp = new Mechanic(result[1]);
				temp.setId(Integer.getInteger(result[0]));

				importedMechanics.add(temp);
			}

		} catch (IOException e) {
			Logger.getLogger(this.getClass().getSimpleName()).error(e.getMessage());
		}
		return importedMechanics;
	}

	
	/**Convert Mechanic collection from parameter to List<String> and run Method exportCollection */
	public void exportCSV(List<Mechanic> list) {
	
		List<String> listToExport = new ArrayList<>();

		for (Mechanic mechanic : list) {
			String stringToCsv = new StringBuilder().append(mechanic.getId()).append(",").append(mechanic.getFullName())
					.append(",").toString();
			listToExport.add(stringToCsv);
		}
		exportCollection(listToExport);
	}

}
