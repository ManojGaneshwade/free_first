package com.TATA.Freelancing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TATA.Freelancing.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	@Query(nativeQuery = true,value="select  category_id from category where category_name=:name")
	Long findByCatName(@Param("name") String categoryName);

}
