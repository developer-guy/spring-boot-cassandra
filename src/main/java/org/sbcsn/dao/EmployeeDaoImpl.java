package org.sbcsn.dao;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.sbcsn.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bapaydin on 16.02.2017.
 */

@Repository
public class EmployeeDaoImpl extends GenericDaoImpl<Employee, Long> implements EmployeeDao {

    @Override
    public List<Employee> getEmployeesByName(String name) {
        Select select = QueryBuilder.select().from("employee").where(QueryBuilder.eq("name", name)).orderBy(QueryBuilder.asc("id"));
        return cassandraOperations.select(select, Employee.class);
    }
}
