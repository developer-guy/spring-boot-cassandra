package org.sbcsn.dao;

import org.sbcsn.entity.Employee;

import java.util.List;

/**
 * Created by bapaydin on 16.02.2017.
 */
public interface EmployeeDao extends GenericDao<Employee,Long>{
    List<Employee> getEmployeesByName(String name);
}
