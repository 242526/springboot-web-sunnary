package com.nd.springbootwebsunnary.controller;

import com.nd.springbootwebsunnary.dao.DepartmentDao;
import com.nd.springbootwebsunnary.dao.EmployeeDao;
import com.nd.springbootwebsunnary.entities.Department;
import com.nd.springbootwebsunnary.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/***
 * 员工控制层
 */
@Controller
public class EmpController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    /***
     * 跳转到员工列表页面
     * @param model
     * @return
     */
    @GetMapping(value = "/emps")
    public String empAll(Model model){
        Collection<Employee> employees = employeeDao.getAll();
         model.addAttribute("employees",employees);
        return "emp/list";
    }

    /***
     * 跳转到添加部门页面
     * @return
     */
    @GetMapping("/emp")
    public String addToEmp(Model model){
        //查询所有部门，传给前台
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        return "emp/add";
    }

    /***
     * 添加员工
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    public  String addEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /***
     * 跳转到编辑页面， 把个人的员工信息与部门信息回写给前台
     * @param id
     * @return
     */
    @GetMapping("/emp/{id}")
    public String updateToEmp(@PathVariable(name="id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        System.out.println(id);
        //查询员工列表， 回写给前台
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        return "emp/add";
    }

    /***
     * 模拟用户修改
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String updateEmp(Employee employee){

        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /****
     * 删除员工
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable(name = "id") Integer id){
        System.out.println(id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }



}
