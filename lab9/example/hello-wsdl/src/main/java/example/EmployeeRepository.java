package example;

import java.util.LinkedList;
import java.util.List;

public class EmployeeRepository {

    List<Employee> employees = new LinkedList<>();

    public EmployeeRepository() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("Adan");
        employees.add(employee);
    }

    public Employee getEmployee(int id){
        return employees.get(id);
    }
    public int count(){
        return employees.size();
    }

}
