package org.example.service;

import org.example.entity.employee.Employee;
import org.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.Collections;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Optional<Employee> save(Employee employee) {
        return Optional.of(employeeRepository.save(employee));
    }

    public Optional<List<Employee>> getAll() {
        return Optional.of(employeeRepository.findAll());
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee update(Long id, Employee employee) {
        String firstName = employee.getFirstName();
        String surname = employee.getSurname();
        String position = employee.getPosition();
        String phone = employee.getPhone();
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employeeToUpdate = employeeOptional.get();
            if (firstName != null) {
                employeeToUpdate.setFirstName(firstName);
            }
            if (surname != null) {
                employeeToUpdate.setSurname(surname);
            }
            if (position != null) {
                employeeToUpdate.setPosition(position);
            }
            if (phone != null) {
                employeeToUpdate.setPhone(phone);
                employeeRepository.save(employeeToUpdate);
            }
        }
        return employeeRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public List<Employee> findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName).orElse(Collections.emptyList());
    }
    public List<Employee> findBySurname(String surname) {
        return employeeRepository.findBySurname(surname).orElse(Collections.emptyList());
    }
}
