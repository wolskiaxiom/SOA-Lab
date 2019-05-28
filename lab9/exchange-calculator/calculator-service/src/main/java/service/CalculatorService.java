package service;

import currency.CurrencyService;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class CalculatorService {

    @Inject
    CurrencyService currencyService ;


    @WebMethod
    public double getExchangeRate(String symbol){
        return currencyService.getExchangeRate("chf");
    }

    @WebMethod
    public double getAmountOfPLN(int amount, String symbol){
        return currencyService.getAmountOfPLN(amount, symbol);
    }

    public static void main(String[] args) {
        CalculatorService calculatorService = new CalculatorService();
        System.out.println(calculatorService.getExchangeRate("chf"));
    }
}
