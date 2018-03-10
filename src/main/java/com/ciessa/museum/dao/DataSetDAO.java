package com.ciessa.museum.dao;

import com.ciessa.museum.model.DataSet;

public class DataSetDAO extends GenericDAOImpl<DataSet> implements GenericDAO<DataSet> {

	public DataSetDAO() {
		super(DataSet.class);
	}
	
}
