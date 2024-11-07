package com.TATA.Freelancing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TATA.Freelancing.entity.Services;

public interface ServicesRepository extends JpaRepository<Services, Long>{
	
	
	@Query(nativeQuery = true,value="select * from services where category_id=:id")
	Optional<List<Services>> findbyCategoryId(@Param("id") Long catId);
	
	@Query(nativeQuery=true,value="select * from services where  tech_id=:id")
	Optional<List<Services>> findbyTechId(@Param("id") Long techId);

}
