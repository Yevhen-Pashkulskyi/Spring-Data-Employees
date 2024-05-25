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
    EmployeeRepository repository;

    public Optional<Employee> save(Employee employee) {
        return Optional.of(repository.save(employee));
    }

    public Optional<List<Employee>> getAll() {
        return Optional.of(repository.findAll());
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee update(Long id, Employee employee) {
        String firstName = employee.getFirstName();
        String surname = employee.getSurname();
        String position = employee.getPosition();
        String phone = employee.getPhone();
        Optional<Employee> optional = repository.findById(id);
        if (optional.isPresent()) {
            Employee empUpdate = optional.get();
            if (firstName != null) {
                empUpdate.setFirstName(firstName);
            }
            if (surname != null) {
                empUpdate.setSurname(surname);
            }
            if (position != null) {
                empUpdate.setPosition(position);
            }
            if (phone != null) {
                empUpdate.setPhone(phone);
            }
            repository.save(empUpdate);
        }
        return repository.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public List<Employee> getByFirstName(String firstName) {
        return repository.findByFirstName(firstName).orElse(Collections.emptyList());
    }
    public List<Employee> getBySurname(String surname) {
        return repository.findBySurname(surname).orElse(Collections.emptyList());
    }
}
