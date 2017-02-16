package org.sbcsn.rest;

import org.sbcsn.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sbcsn.service.EmployeeService;

import java.util.List;

/**
 * Created by bapaydin on 16.02.2017.
 */

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    Employee create(@RequestBody Employee employee){
        return employeeService.create(employee);
    }

    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    List<Employee> findAlll(){
        return employeeService.findAll();
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    Employee findById(@PathVariable("id") long id) {
        return employeeService.findById(id);
    }
}
