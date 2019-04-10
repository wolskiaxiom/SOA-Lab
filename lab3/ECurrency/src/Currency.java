import netscape.javascript.JSObject;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStreamReader;

@ManagedBean(name="currency", eager = true)
@ViewScoped
public class Currency {
    public String symbol;
    public float rateToPLN;

    public Currency() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getRateToPLN() {
        return rateToPLN;
    }

    public void setRateToPLN(float rateToPLN) {
        this.rateToPLN = rateToPLN;
    }

    public Currency(String symbol) {
        this.symbol = symbol;
    }

//    public Double getRateFromNBP(String symbol) throws Exception {
//
////        Float rate = 0.0f;
//        Double rate = null;
//        JsonReader reader = null;
//        CloseableHttpClient client = HttpClientBuilder.create().build();
//        HttpHost target = new HttpHost("api.nbp.pl", 80, "http");
//        HttpGet request = new HttpGet("/api/exchangerates/rates/a/chf");
//        request.addHeader("accept", "application/json");
//        CloseableHttpResponse response = null;
//
//        try{
//            response = client.execute(target, request);
//            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
//                throw new Exception("Failes : HTTP error code : "
//                        + response.getStatusLine().getStatusCode());
//            }
//
//            reader = Json.createReader(new InputStreamReader(response.getEntity().getContent()));
//
//            JsonObject object = reader.readObject();
//            object = object.getJsonObject("Rates");
//            object = object.getJsonObject("Rate");
//
//            rate = object.getJsonNumber("Mid").doubleValue();
//            System.out.println(object.getJsonNumber("Mid").doubleValue());
//
//        }finally {
//            if (reader !=null) reader.close();
//            if(client != null) client.close();
//            if (response != null) response.close();
//        }
//
//        return rate;
//    }
    public Double getRateFromNBP(String symbol){
        return 2.0;
    }

}
