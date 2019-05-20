package client;

import java.net.URL;
import java.util.List;

public class EmployeeServiceClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/hello-server-1.0-SNAPSHOT/EmployeeServiceImpl?wsdl");

        EmployeeServiceImplService employeeService_Service
                = new EmployeeServiceImplService(url);
        EmpleeService empleeService = employeeService_Service.getEmployeeServiceImplPort();

        int a = empleeService.count();
        System.out.println(a);
    }
}