package lk.ijse.dep.payroll.Buisness.custom.impl;

import lk.ijse.dep.payroll.Buisness.custom.EmployeeBO;
import lk.ijse.dep.payroll.dao.CrudUtil;
import lk.ijse.dep.payroll.dao.DAOFactory;
import lk.ijse.dep.payroll.dao.DAOTypes;
import lk.ijse.dep.payroll.dao.custom.EmployeeDAO;
import lk.ijse.dep.payroll.dto.EmployeeDTO;
import lk.ijse.dep.payroll.entity.Employee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    private EmployeeDAO employeeDAO = DAOFactory.getInstance().getDAO(DAOTypes.EMPLOYEE);

    @Override
    public boolean saveEmployee(EmployeeDTO employee) throws Exception {
        return employeeDAO.save(
                new Employee(employee.getEmpID(),
                        employee.getDate(),
                        employee.getEmpName(),
                        employee.getPassword(),
                        employee.getDesignation(),
                        employee.getContactNo(),
                        employee.getNIC()
                ));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee) throws Exception {
        return false;
    }

    @Override
    public boolean deleteEmployee(String employeeID) throws Exception {
        return false;
    }

    @Override
    public List<EmployeeDTO> findAllEmployees() throws Exception {
        List<Employee> employeeList = employeeDAO.findAll();
        List<EmployeeDTO> dtos = new ArrayList<>();
        for (Employee em:employeeList) {
            dtos.add(
                    new EmployeeDTO(em.getEmpID(),
                            em.getDate(),
                            em.getEmpName(),
                            em.getPassword(),
                            em.getDesignation(),
                            em.getContactNo(),
                            em.getNIC()));
        }
        return dtos;
    }

    @Override
    public String getLastEmployeeID() throws Exception {
        return employeeDAO.getLastEmployeeID();
    }

    @Override
    public EmployeeDTO findEmployee(String employeeID) throws Exception {
        Employee employee = employeeDAO.findById(employeeID);
        return new EmployeeDTO(
                employee.getEmpID(),
                employee.getDate(),
                employee.getEmpName(),
                employee.getPassword(),
                employee.getDesignation(),
                employee.getContactNo(),
                employee.getNIC());
    }

    @Override
    public List<String> getAllEmployeeIDs() throws Exception {
        List<Employee> employeeList = employeeDAO.findAll();
        List<String> dtoIds = new ArrayList<>();
        for (Employee em:employeeList) {
            dtoIds.add(em.getEmpID());
        }
        return dtoIds;
    }

    @Override
    public List<EmployeeDTO> findAllEmpAtt(Date date) throws Exception {
        List<Employee> empAtt = employeeDAO.findAllAtt(date);
        List<EmployeeDTO> dtoAtts = new ArrayList<>();
        for (Employee em:empAtt) {
            dtoAtts.add(
                    new EmployeeDTO(em.getEmpID(),
                            em.getDate(),
                            em.getEmpName(),
                            em.getPassword(),
                            em.getDesignation(),
                            em.getContactNo(),
                            em.getNIC()));
        }
        return dtoAtts;
    }
}
