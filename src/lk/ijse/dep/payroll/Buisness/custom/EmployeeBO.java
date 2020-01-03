package lk.ijse.dep.payroll.Buisness.custom;

import lk.ijse.dep.payroll.Buisness.SuperBO;
import lk.ijse.dep.payroll.dto.EmployeeDTO;

import java.sql.Date;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    boolean saveEmployee(EmployeeDTO employee) throws Exception;

    boolean updateEmployee(EmployeeDTO employee) throws Exception;

    boolean deleteEmployee(String employeeID) throws Exception;

    List<EmployeeDTO> findAllEmployees() throws Exception;

    String getLastEmployeeID()throws Exception;

    EmployeeDTO findEmployee(String EmployeeID) throws Exception;

    List<String> getAllEmployeeIDs() throws Exception;

    List<EmployeeDTO> findAllEmpAtt(Date date) throws Exception;
}
