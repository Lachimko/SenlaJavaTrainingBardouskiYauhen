package com.bardouski.program.dbprocessor.serializator;

import java.io.FileNotFoundException;

public interface ISerializer {

	public void setPath(String path) throws FileNotFoundException;
	
	/**Deserialize data from file. Return collections in box IResultContainer*/
	public IResultContainer getLists();
	
	/**Serialize collections from IResultContainer, and push data to DB*/
	public void saveToFile(IResultContainer container);
}
