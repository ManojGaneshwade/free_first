package com.TATA.Freelancing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TATA.Freelancing.entity.Request;
import com.TATA.Freelancing.entity.Services;

public interface RequestRepository extends JpaRepository<Request, Long> {
	
	
	@Query(nativeQuery=true,value="select * from request where  cust_Id=:id")
	Optional<List<Request>> findbyCustId(@Param("id") Long custId);
	
	
	@Query(nativeQuery=true,value="select * from request where  category_Id=:id")
	Optional<List<Request>> findbyCategoryId(@Param("id") Long categoryId);
	
	
	@Query(nativeQuery=true,value="select * from request where  tech_Id=:id")
	Optional<List<Request>> findbyTechId(@Param("id") Long techId);
	
	@Query(nativeQuery=true,value="select * from request where  status=:status")
	Optional<List<Request>> findbyStatus(@Param("status") String requestStatus);

}
