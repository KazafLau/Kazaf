package com.kazaf.dao;

import com.kazaf.domain.Bill;
import com.kazaf.domain.Gym;
import com.kazaf.domain.Gym_Anaerobic;

import java.util.List;
import java.util.Map;

/**
 * Created by Kazaf on 16/3/21.
 */
public interface CommonDao {


    public Gym getGym(int gym_id);

    public void insertGym(Gym gym);

    public Bill getBill(int bill_id);

    public void insertBill(Bill bill);

    public List<Bill> getAllBill();

    public List<Gym> getAllGym();

    public void insertGymList(List<Gym> Glist);

    public void insertBillList(List<Bill> Blist);

    public void insertGymAnaerobic(List<Gym_Anaerobic> GList);

    public Gym_Anaerobic getGymAnaerobic(int gym_id);

    public void insertGymAnaerobic(Gym_Anaerobic gym_anaerobic);

}
