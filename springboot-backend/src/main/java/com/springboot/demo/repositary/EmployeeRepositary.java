package com.springboot.demo.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.demo.model.Employee;

@Repository
public interface EmployeeRepositary extends JpaRepository<Employee,Long>{

	List<Employee> findAll();
	Employee findByEmaild(String emailid);

}
