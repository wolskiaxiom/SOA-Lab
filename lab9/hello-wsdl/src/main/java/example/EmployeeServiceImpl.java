package example;

import javax.inject.Inject;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "example.EmpleeService")
public class EmployeeServiceImpl implements EmpleeService {

//    @Inject
//    private EmployeeRepository employeeRepositoryImpl;

    @WebMethod
    public Employee getEmployee(int id) {
        return new Employee();
    }

    @WebMethod
    public int count(){
        return 1;
    }
}