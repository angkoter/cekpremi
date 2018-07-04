package com.jatismobile.cekpremi.logic;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jatismobile.cekpremi.model.ProductsData;
import com.jatismobile.cekpremi.model.TransactionsData;

public interface ProductsRepo extends CrudRepository<ProductsData, Long>{
	@Query("SELECT pd FROM ProductsData pd WHERE pd.jatisProductCode = :jatisProductCode and pd.billerId =:billerId")
	ProductsData findByJatisProductCodeAndBillerId(@Param("jatisProductCode")String jatisProductCode,@Param("billerId")String billerId);
}
