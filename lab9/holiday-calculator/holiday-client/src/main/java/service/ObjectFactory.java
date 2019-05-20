
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

    private final static QName _GetNumberOfDays_QNAME = new QName("http://service/", "getNumberOfDays");
    private final static QName _GetNumberOfDaysResponse_QNAME = new QName("http://service/", "getNumberOfDaysResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetNumberOfDays }
     * 
     */
    public GetNumberOfDays createGetNumberOfDays() {
        return new GetNumberOfDays();
    }

    /**
     * Create an instance of {@link GetNumberOfDaysResponse }
     * 
     */
    public GetNumberOfDaysResponse createGetNumberOfDaysResponse() {
        return new GetNumberOfDaysResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNumberOfDays }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getNumberOfDays")
    public JAXBElement<GetNumberOfDays> createGetNumberOfDays(GetNumberOfDays value) {
        return new JAXBElement<GetNumberOfDays>(_GetNumberOfDays_QNAME, GetNumberOfDays.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNumberOfDaysResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getNumberOfDaysResponse")
    public JAXBElement<GetNumberOfDaysResponse> createGetNumberOfDaysResponse(GetNumberOfDaysResponse value) {
        return new JAXBElement<GetNumberOfDaysResponse>(_GetNumberOfDaysResponse_QNAME, GetNumberOfDaysResponse.class, null, value);
    }

}
