package calculator.client.service;

import service.CalculatorService;
import service.CalculatorServiceService;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/calculator_service_war_exploded/exchange?wsdl");

        CalculatorServiceService calculatorServiceService = new CalculatorServiceService(url);

        CalculatorService calculatorService = calculatorServiceService.getCalculatorServicePort();

        System.out.println(calculatorService.getExchangeRate("chf"));

    }
}
