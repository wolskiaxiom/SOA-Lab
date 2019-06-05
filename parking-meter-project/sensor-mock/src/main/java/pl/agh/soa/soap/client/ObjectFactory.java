
package pl.agh.soa.soap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.agh.soa.soap.client package. 
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

    private final static QName _OccupyPlaceResponse_QNAME = new QName("http://soap.soa.agh.pl/", "occupyPlaceResponse");
    private final static QName _OccupyPlace_QNAME = new QName("http://soap.soa.agh.pl/", "occupyPlace");
    private final static QName _ReleasePlace_QNAME = new QName("http://soap.soa.agh.pl/", "releasePlace");
    private final static QName _ReleasePlaceResponse_QNAME = new QName("http://soap.soa.agh.pl/", "releasePlaceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.agh.soa.soap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReleasePlaceResponse }
     * 
     */
    public ReleasePlaceResponse createReleasePlaceResponse() {
        return new ReleasePlaceResponse();
    }

    /**
     * Create an instance of {@link ReleasePlace }
     * 
     */
    public ReleasePlace createReleasePlace() {
        return new ReleasePlace();
    }

    /**
     * Create an instance of {@link OccupyPlaceResponse }
     * 
     */
    public OccupyPlaceResponse createOccupyPlaceResponse() {
        return new OccupyPlaceResponse();
    }

    /**
     * Create an instance of {@link OccupyPlace }
     * 
     */
    public OccupyPlace createOccupyPlace() {
        return new OccupyPlace();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OccupyPlaceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.soa.agh.pl/", name = "occupyPlaceResponse")
    public JAXBElement<OccupyPlaceResponse> createOccupyPlaceResponse(OccupyPlaceResponse value) {
        return new JAXBElement<OccupyPlaceResponse>(_OccupyPlaceResponse_QNAME, OccupyPlaceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OccupyPlace }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.soa.agh.pl/", name = "occupyPlace")
    public JAXBElement<OccupyPlace> createOccupyPlace(OccupyPlace value) {
        return new JAXBElement<OccupyPlace>(_OccupyPlace_QNAME, OccupyPlace.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReleasePlace }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.soa.agh.pl/", name = "releasePlace")
    public JAXBElement<ReleasePlace> createReleasePlace(ReleasePlace value) {
        return new JAXBElement<ReleasePlace>(_ReleasePlace_QNAME, ReleasePlace.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReleasePlaceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.soa.agh.pl/", name = "releasePlaceResponse")
    public JAXBElement<ReleasePlaceResponse> createReleasePlaceResponse(ReleasePlaceResponse value) {
        return new JAXBElement<ReleasePlaceResponse>(_ReleasePlaceResponse_QNAME, ReleasePlaceResponse.class, null, value);
    }

}
