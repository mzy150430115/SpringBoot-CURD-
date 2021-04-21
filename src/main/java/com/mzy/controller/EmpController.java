package com.mzy.controller;

import com.mzy.dao.DepartmentDao;
import com.mzy.dao.EmployeeDao;
import com.mzy.pojo.Department;
import com.mzy.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmpController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("save=>"+employee);
        //添加的操作
        employeeDao.save(employee); //保存员工信息
        return "redirect:/emps";
    }

    //修改
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id,Model model){
        //查出原来的数据
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return  "redirect:/emps";
    }
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
