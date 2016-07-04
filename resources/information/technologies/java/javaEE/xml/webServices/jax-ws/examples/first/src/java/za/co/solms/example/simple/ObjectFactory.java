
package za.co.solms.example.simple;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the za.co.solms.example.simple package. 
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

    private final static QName _ClassifySongResponse_QNAME = new QName("http://solms.co.za/example/simple", "classifySongResponse");
    private final static QName _ClassifySongRequest_QNAME = new QName("http://solms.co.za/example/simple", "classifySongRequest");
    private final static QName _UnknownArtistOrSong_QNAME = new QName("http://solms.co.za/example/simple", "unknownArtistOrSong");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: za.co.solms.example.simple
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UnknownArtistOrSong }
     * 
     */
    public UnknownArtistOrSong createUnknownArtistOrSong() {
        return new UnknownArtistOrSong();
    }

    /**
     * Create an instance of {@link ClassifySongResponse }
     * 
     */
    public ClassifySongResponse createClassifySongResponse() {
        return new ClassifySongResponse();
    }

    /**
     * Create an instance of {@link ClassifySongRequest }
     * 
     */
    public ClassifySongRequest createClassifySongRequest() {
        return new ClassifySongRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassifySongResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://solms.co.za/example/simple", name = "classifySongResponse")
    public JAXBElement<ClassifySongResponse> createClassifySongResponse(ClassifySongResponse value) {
        return new JAXBElement<ClassifySongResponse>(_ClassifySongResponse_QNAME, ClassifySongResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassifySongRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://solms.co.za/example/simple", name = "classifySongRequest")
    public JAXBElement<ClassifySongRequest> createClassifySongRequest(ClassifySongRequest value) {
        return new JAXBElement<ClassifySongRequest>(_ClassifySongRequest_QNAME, ClassifySongRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownArtistOrSong }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://solms.co.za/example/simple", name = "unknownArtistOrSong")
    public JAXBElement<UnknownArtistOrSong> createUnknownArtistOrSong(UnknownArtistOrSong value) {
        return new JAXBElement<UnknownArtistOrSong>(_UnknownArtistOrSong_QNAME, UnknownArtistOrSong.class, null, value);
    }

}
