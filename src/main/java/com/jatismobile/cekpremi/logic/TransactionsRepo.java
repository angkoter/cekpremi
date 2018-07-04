package com.jatismobile.cekpremi.logic;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jatismobile.cekpremi.model.TransactionsData;
import com.jatismobile.cekpremi.model.VehiclesData;

@Repository
public interface TransactionsRepo extends CrudRepository<TransactionsData,Long> {
	TransactionsData findByTrxId(String trxid);
	
	@Query("SELECT td FROM TransactionsData td WHERE td.type = 'Register' and td.jatisProductCode =:jtsProduct and td.vehiclesData.vehiclePlatNo=:platNo")
	List<TransactionsData> findByTypeAndVehiclePlatNo(@Param("jtsProduct") String jtsProdct,@Param("platNo")String platNo);
	
}
