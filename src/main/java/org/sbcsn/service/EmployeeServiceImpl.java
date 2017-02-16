package org.sbcsn.service;

import org.sbcsn.dao.EmployeeDao;
import org.sbcsn.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bapaydin on 16.02.2017.
 */

@Repository
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, Long> implements EmployeeService {

    @Autowired
    @Qualifier("employeeDaoImpl")
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getEmployeesByName(String name) {
       return employeeDao.getEmployeesByName(name);
    }
}
