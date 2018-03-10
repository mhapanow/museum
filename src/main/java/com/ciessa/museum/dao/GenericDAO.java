package com.ciessa.museum.dao;

import java.util.List;
import java.util.Map;

import com.ciessa.museum.exception.ASException;
import com.ciessa.museum.model.ModelKey;
import com.ciessa.museum.tools.Range;

public interface GenericDAO<T extends ModelKey> {

	// Basic Gets
	T get(String identifier) throws ASException; 
	
	// List Gets
	List<T> getAll() throws ASException;
	List<T> getUsingRange(Range range) throws ASException;
	List<T> getAllAndOrder(String order) throws ASException;
	List<T> getUsingStatusAndRange(List<Integer> status, Range range, String order, Map<String, String> aattributes) throws ASException;

	// Basic CRUD Operations
	void create(T obj) throws ASException;
	void update(T obj) throws ASException;
	void delete(T obj) throws ASException;
	void delete(String identifier) throws ASException;

}
