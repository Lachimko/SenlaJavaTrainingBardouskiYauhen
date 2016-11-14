package com.bardouski.program.serializator;

public interface ISerializer {

	/**Deserialize data from file. Return collections in box IResultContainer*/
	public IResultContainer getLists();
	
	
	/**Serialize collections from IResultContainer, and push data to DB*/
	public void saveToFile(IResultContainer container);
}
