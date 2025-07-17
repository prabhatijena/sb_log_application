package com.example.crud_operation.controller;
import com.example.crud_operation.entity.Employee;
import com.example.crud_operation.repository.EmployeeRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/save")
public class EmployeeController {
    @Autowired
    private EmployeeRepo repository;

    @PostMapping("/post")
    public ResponseEntity<Object> create(@RequestBody Employee employee) {
        repository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee inserted Successfully...");
    }
    @GetMapping("/get")
    public ResponseEntity<Object> findallemployee(){
        return ResponseEntity.status(HttpStatus.FOUND).body(repository.findAll());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Employee employee) {
        Optional<Employee> emp = repository.findById(id);
        if (emp.isPresent()) {
            Employee existingEmp = emp.get();
            existingEmp.setName(employee.getName());
            existingEmp.setAge(employee.getAge());
            existingEmp.setSalary(employee.getSalary());
            repository.save(existingEmp);
            return ResponseEntity.status(HttpStatus.OK).body("Employee updated Successfully...");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Employee updated Successfully...");

        }
    }
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Object> deletebyId(@PathVariable Integer id){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted Successfully...: " +id);


    }
}
