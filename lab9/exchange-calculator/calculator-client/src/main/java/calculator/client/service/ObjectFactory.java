
package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAmountOfPLNResponse_QNAME = new QName("http://service/", "getAmountOfPLNResponse");
    private final static QName _GetExchangeRate_QNAME = new QName("http://service/", "getExchangeRate");
    private final static QName _GetAmountOfPLN_QNAME = new QName("http://service/", "getAmountOfPLN");
    private final static QName _GetExchangeRateResponse_QNAME = new QName("http://service/", "getExchangeRateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetExchangeRate }
     * 
     */
    public GetExchangeRate createGetExchangeRate() {
        return new GetExchangeRate();
    }

    /**
     * Create an instance of {@link GetAmountOfPLNResponse }
     * 
     */
    public GetAmountOfPLNResponse createGetAmountOfPLNResponse() {
        return new GetAmountOfPLNResponse();
    }

    /**
     * Create an instance of {@link GetExchangeRateResponse }
     * 
     */
    public GetExchangeRateResponse createGetExchangeRateResponse() {
        return new GetExchangeRateResponse();
    }

    /**
     * Create an instance of {@link GetAmountOfPLN }
     * 
     */
    public GetAmountOfPLN createGetAmountOfPLN() {
        return new GetAmountOfPLN();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAmountOfPLNResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAmountOfPLNResponse")
    public JAXBElement<GetAmountOfPLNResponse> createGetAmountOfPLNResponse(GetAmountOfPLNResponse value) {
        return new JAXBElement<GetAmountOfPLNResponse>(_GetAmountOfPLNResponse_QNAME, GetAmountOfPLNResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExchangeRate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getExchangeRate")
    public JAXBElement<GetExchangeRate> createGetExchangeRate(GetExchangeRate value) {
        return new JAXBElement<GetExchangeRate>(_GetExchangeRate_QNAME, GetExchangeRate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAmountOfPLN }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAmountOfPLN")
    public JAXBElement<GetAmountOfPLN> createGetAmountOfPLN(GetAmountOfPLN value) {
        return new JAXBElement<GetAmountOfPLN>(_GetAmountOfPLN_QNAME, GetAmountOfPLN.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExchangeRateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getExchangeRateResponse")
    public JAXBElement<GetExchangeRateResponse> createGetExchangeRateResponse(GetExchangeRateResponse value) {
        return new JAXBElement<GetExchangeRateResponse>(_GetExchangeRateResponse_QNAME, GetExchangeRateResponse.class, null, value);
    }

}
