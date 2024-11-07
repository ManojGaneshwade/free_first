package com.TATA.Freelancing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TATA.Freelancing.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
