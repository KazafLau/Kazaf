package com.kazaf.dao;

import com.kazaf.pojos.Gym;

import java.util.List;
/**
*test
*/
public interface GymDAO {

    public Gym getGym(int gym_id);
	
	public void insertGym(Gym gym);

    public List<Gym> getAllGym();


}
