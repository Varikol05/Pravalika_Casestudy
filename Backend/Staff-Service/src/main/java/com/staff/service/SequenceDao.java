package com.staff.service;

import com.staff.exception.SequenceException;

public interface SequenceDao {
	
	
	 long getNextSequenceId(String key) throws SequenceException;
	 }


