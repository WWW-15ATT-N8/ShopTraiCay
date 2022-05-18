package com.se.ecofruits.dao;

import java.util.List;

import com.se.ecofruits.entity.Status;

public interface StatusDAO {
	public List<Status> getStatuses();
	public Status getStatus(int id) ;
}
