package com.mzy.dao;

import com.mzy.pojo.Department;
import com.mzy.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer,Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(101,new Employee(101,"aaa","aaa",1,new Department(101,"教学部")));
        employees.put(102,new Employee(102,"bbb","aaa",1,new Department(102,"教学部")));
        employees.put(103,new Employee(103,"ccc","aaa",1,new Department(103,"教学部")));
        employees.put(104,new Employee(104,"ddd","aaa",1,new Department(104,"教学部")));
        employees.put(105,new Employee(105,"eee","aaa",1,new Department(105,"教学部")));
    }
    private static Integer initId = 106;
    public void save(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    public Collection<Employee>     getAll(){
        return employees.values();
    }

    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }


}

