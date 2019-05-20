package example;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface EmpleeService {

    @WebMethod
    Employee getEmployee(int id);

    @WebMethod
    int count();
}
