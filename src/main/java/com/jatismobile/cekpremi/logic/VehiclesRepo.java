package com.jatismobile.cekpremi.logic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jatismobile.cekpremi.model.TransactionsData;
import com.jatismobile.cekpremi.model.VehiclesData;

@Repository
public interface VehiclesRepo extends CrudRepository<VehiclesData, Long> {
	VehiclesData findByVehiclePlatNo(String vehiclePlatNo);
	
}
