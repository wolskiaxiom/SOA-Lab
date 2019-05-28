package currency;

import model.CurrencyEntity;
import model.Rate;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.net.URI;

@ApplicationScoped
public class CurrencyService implements Serializable {
    private Client client;

    public double getExchangeRate(String symbol){
        client = ClientBuilder.newClient();
        URI uri = UriBuilder.fromUri("http://api.nbp.pl/api/exchangerates/rates/a/"+ symbol+"/").build();
        Response response = client.target(uri).request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        if(response.getStatus()!=200){
            throw new RuntimeException("Error");
        }

        CurrencyEntity currencyEntity = response.readEntity(CurrencyEntity.class);
        Rate rate = currencyEntity.getRates().get(0);

        return Double.parseDouble(rate.getMid());
    }

    public double getAmountOfPLN(int amount, String symbol){
        return amount * getExchangeRate(symbol);
    }

}
