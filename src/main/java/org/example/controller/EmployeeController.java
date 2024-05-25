package org.example.controller;

import org.example.entity.employee.Employee;
import org.example.network.ResponseData;
import org.example.network.ResponseEnumMessage;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseData save(@RequestBody Employee employee) {
        Optional<Employee> optional = employeeService.save(employee);
        return optional.map(value -> new ResponseData(HttpStatus.CREATED.toString(), true, value)).
                orElseGet(() -> new ResponseData(HttpStatus.NO_CONTENT.toString(), false,
                        ResponseEnumMessage.SMTH_WRONG.getResMsg()));
    }

    @GetMapping("/employees")
    public ResponseData getAll() {
        Optional<List<Employee>> optional = employeeService.getAll();
        return optional.map(employees -> new ResponseData(HttpStatus.OK.toString(), true, employees))
                .orElseGet(() -> new ResponseData(HttpStatus.NOT_FOUND.toString(), false,
                        ResponseEnumMessage.SMTH_WRONG.getResMsg()));
    }

    @PutMapping("/employees/{id}")
    public ResponseData update(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.update(id, employee);
        if (updatedEmployee != null) {
            return new ResponseData(HttpStatus.OK.toString(), true, updatedEmployee);
        } else {
            return new ResponseData(HttpStatus.NOT_FOUND.toString(),
                    false, ResponseEnumMessage.SMTH_WRONG.getResMsg());
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseData getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return new ResponseData(HttpStatus.OK.toString(), true, employee);
        } else {
            return new ResponseData(HttpStatus.NOT_FOUND.toString(),
                    false, ResponseEnumMessage.SMTH_WRONG.getResMsg());
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseData delete(@PathVariable Long id) {
        if (employeeService.delete(id)) {
            return new ResponseData(HttpStatus.OK.toString(), true, ResponseEnumMessage.DELETED.getResMsg());
        } else {
            return new ResponseData(HttpStatus.NOT_FOUND.toString(),
                    false, ResponseEnumMessage.SMTH_WRONG.getResMsg());
        }
    }

    @GetMapping("/employees/first-name/{firstName}")
    public ResponseData getByFirstName(@PathVariable String firstName) {
        List<Employee> list = employeeService.findByFirstName(firstName);
        if (!list.isEmpty()) {
            return new ResponseData(HttpStatus.OK.toString(), true, list);
        } else {
            return new ResponseData(HttpStatus.NOT_FOUND.toString(),
                    false, ResponseEnumMessage.NO_DATA.getResMsg());
        }
    }

    @GetMapping("/employees/surname/{surname}")
    public ResponseData getBySurname(@PathVariable String surname) {
        List<Employee> list = employeeService.findBySurname(surname);
        if (!list.isEmpty()) {
            return new ResponseData(HttpStatus.OK.toString(), true, list);
        } else {
            return new ResponseData(HttpStatus.NOT_FOUND.toString(),
                    false, ResponseEnumMessage.NO_DATA.getResMsg());
        }
    }
}
