package org.sbcsn.service;

import org.sbcsn.entity.Employee;

import java.util.List;

/**
 * Created by bapaydin on 16.02.2017.
 */
public interface EmployeeService extends GenericService<Employee,Long> {
    List<Employee> getEmployeesByName(String name);
}
