package com.se.ecofruits.service;

import java.util.List;

import com.se.ecofruits.entity.Status;

public interface StatusService {

	public List<Status> getStatuses();
	public Status getStatus(int id) ;
}
