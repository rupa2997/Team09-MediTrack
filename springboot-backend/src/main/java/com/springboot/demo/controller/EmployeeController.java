package com.springboot.demo.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.exception.ResourceNotFound;
import com.springboot.demo.model.Employee;
import com.springboot.demo.repositary.EmployeeRepositary;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepositary employeeRepository;
	
	//get all employees

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
		
	}
	
	//create employeee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
		
	}
	
	//get employee by rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		//Long id=Long.parseLong(id1);
		Employee employee=employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Employee not exist with id: "+ id));
		return ResponseEntity.ok(employee);
	}
	
	//update employee by rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
		//Long id=Long.parseLong(id1);
//		Employee employee=employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Employee not exist with id: "+ id));
//		
//		employee.setFirstname(employeeDetails.getFirstname());
//		employee.setLastname(employeeDetails.getLastname());
//		employee.setEmaild(employeeDetails.getEmaild());
		employeeDetails.setId(id);
//		Employee updatedEmployee=employeeRepository.save(employee);
		Employee updatedEmployee=employeeRepository.save(employeeDetails);
		return ResponseEntity.ok(updatedEmployee);
		
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Employee e,HttpSession Session)
	{
		Employee dt=employeeRepository.findByEmaild(e.getEmaild());
		if(Objects.nonNull(dt)) 
		{
//			Session.setAttribute("id",dt.getId());
//			String s=(String)Session.getAttribute("id");
//			return s;
			
			return "success";
		}
		else 
		{
			return "login is fail";
		}
	}
	
	
	
	
}
