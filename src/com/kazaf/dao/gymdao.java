package com.kazaf.dao;

import com.kazaf.domain.Gym;

import java.util.List;

public interface gymdao {

    public Gym getGym(int gym_id);
	
	public void insertGym(Gym gym);

    public List<Gym> getAllGym();


}
