package com.ciessa.museum.dao;

import com.ciessa.museum.model.AuditEntry;

public class AuditEntryDAO extends GenericDAOImpl<AuditEntry> implements GenericDAO<AuditEntry> {

	public AuditEntryDAO() {
		super(AuditEntry.class);
	}

}
