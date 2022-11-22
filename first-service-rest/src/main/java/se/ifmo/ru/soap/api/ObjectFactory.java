
package se.ifmo.ru.soap.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the se.ifmo.ru.web.api package. 
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

    private final static QName _Coordinates_QNAME = new QName("http://api.web.ru.ifmo.se/", "Coordinates");
    private final static QName _Flat_QNAME = new QName("http://api.web.ru.ifmo.se/", "Flat");
    private final static QName _FlatPage_QNAME = new QName("http://api.web.ru.ifmo.se/", "FlatPage");
    private final static QName _Flats_QNAME = new QName("http://api.web.ru.ifmo.se/", "Flats");
    private final static QName _House_QNAME = new QName("http://api.web.ru.ifmo.se/", "House");
    private final static QName _AddFlat_QNAME = new QName("http://api.web.ru.ifmo.se/", "addFlat");
    private final static QName _AddFlatResponse_QNAME = new QName("http://api.web.ru.ifmo.se/", "addFlatResponse");
    private final static QName _CountByNew_QNAME = new QName("http://api.web.ru.ifmo.se/", "countByNew");
    private final static QName _CountByNewResponse_QNAME = new QName("http://api.web.ru.ifmo.se/", "countByNewResponse");
    private final static QName _DeleteFlat_QNAME = new QName("http://api.web.ru.ifmo.se/", "deleteFlat");
    private final static QName _GetFlat_QNAME = new QName("http://api.web.ru.ifmo.se/", "getFlat");
    private final static QName _GetFlatResponse_QNAME = new QName("http://api.web.ru.ifmo.se/", "getFlatResponse");
    private final static QName _GetFlats_QNAME = new QName("http://api.web.ru.ifmo.se/", "getFlats");
    private final static QName _GetFlatsResponse_QNAME = new QName("http://api.web.ru.ifmo.se/", "getFlatsResponse");
    private final static QName _GetWithMaxId_QNAME = new QName("http://api.web.ru.ifmo.se/", "getWithMaxId");
    private final static QName _GetWithMaxIdResponse_QNAME = new QName("http://api.web.ru.ifmo.se/", "getWithMaxIdResponse");
    private final static QName _SumHeight_QNAME = new QName("http://api.web.ru.ifmo.se/", "sumHeight");
    private final static QName _SumHeightResponse_QNAME = new QName("http://api.web.ru.ifmo.se/", "sumHeightResponse");
    private final static QName _UpdateFlat_QNAME = new QName("http://api.web.ru.ifmo.se/", "updateFlat");
    private final static QName _UpdateFlatResponse_QNAME = new QName("http://api.web.ru.ifmo.se/", "updateFlatResponse");
    private final static QName _VerificationFault_QNAME = new QName("http://api.web.ru.ifmo.se/", "VerificationFault");
    private final static QName _NotFoundFault_QNAME = new QName("http://api.web.ru.ifmo.se/", "NotFoundFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: se.ifmo.ru.web.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CoordinatesDto }
     * 
     */
    public CoordinatesDto createCoordinatesDto() {
        return new CoordinatesDto();
    }

    /**
     * Create an instance of {@link FlatsGetListRequestDto }
     * 
     */
    public FlatsGetListRequestDto createFlatsGetListRequestDto() {
        return new FlatsGetListRequestDto();
    }

    /**
     * Create an instance of {@link FlatsGetListResponseDto }
     * 
     */
    public FlatsGetListResponseDto createFlatsGetListResponseDto() {
        return new FlatsGetListResponseDto();
    }

    /**
     * Create an instance of {@link HouseDto }
     * 
     */
    public HouseDto createHouseDto() {
        return new HouseDto();
    }

    /**
     * Create an instance of {@link AddFlat }
     * 
     */
    public AddFlat createAddFlat() {
        return new AddFlat();
    }

    /**
     * Create an instance of {@link AddFlatResponse }
     * 
     */
    public AddFlatResponse createAddFlatResponse() {
        return new AddFlatResponse();
    }

    /**
     * Create an instance of {@link CountByNew }
     * 
     */
    public CountByNew createCountByNew() {
        return new CountByNew();
    }

    /**
     * Create an instance of {@link CountByNewResponse }
     * 
     */
    public CountByNewResponse createCountByNewResponse() {
        return new CountByNewResponse();
    }

    /**
     * Create an instance of {@link DeleteFlat }
     * 
     */
    public DeleteFlat createDeleteFlat() {
        return new DeleteFlat();
    }

    /**
     * Create an instance of {@link GetFlat }
     * 
     */
    public GetFlat createGetFlat() {
        return new GetFlat();
    }

    /**
     * Create an instance of {@link GetFlatResponse }
     * 
     */
    public GetFlatResponse createGetFlatResponse() {
        return new GetFlatResponse();
    }

    /**
     * Create an instance of {@link GetFlats }
     * 
     */
    public GetFlats createGetFlats() {
        return new GetFlats();
    }

    /**
     * Create an instance of {@link GetFlatsResponse }
     * 
     */
    public GetFlatsResponse createGetFlatsResponse() {
        return new GetFlatsResponse();
    }

    /**
     * Create an instance of {@link GetWithMaxId }
     * 
     */
    public GetWithMaxId createGetWithMaxId() {
        return new GetWithMaxId();
    }

    /**
     * Create an instance of {@link GetWithMaxIdResponse }
     * 
     */
    public GetWithMaxIdResponse createGetWithMaxIdResponse() {
        return new GetWithMaxIdResponse();
    }

    /**
     * Create an instance of {@link SumHeight }
     * 
     */
    public SumHeight createSumHeight() {
        return new SumHeight();
    }

    /**
     * Create an instance of {@link SumHeightResponse }
     * 
     */
    public SumHeightResponse createSumHeightResponse() {
        return new SumHeightResponse();
    }

    /**
     * Create an instance of {@link UpdateFlat }
     * 
     */
    public UpdateFlat createUpdateFlat() {
        return new UpdateFlat();
    }

    /**
     * Create an instance of {@link UpdateFlatResponse }
     * 
     */
    public UpdateFlatResponse createUpdateFlatResponse() {
        return new UpdateFlatResponse();
    }

    /**
     * Create an instance of {@link VerificationFault }
     * 
     */
    public VerificationFault createVerificationFault() {
        return new VerificationFault();
    }

    /**
     * Create an instance of {@link NotFoundFault }
     * 
     */
    public NotFoundFault createNotFoundFault() {
        return new NotFoundFault();
    }

    /**
     * Create an instance of {@link Flat }
     * 
     */
    public Flat createFlat() {
        return new Flat();
    }

    /**
     * Create an instance of {@link FlatGetResponseDto }
     * 
     */
    public FlatGetResponseDto createFlatGetResponseDto() {
        return new FlatGetResponseDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinatesDto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CoordinatesDto }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "Coordinates")
    public JAXBElement<CoordinatesDto> createCoordinates(CoordinatesDto value) {
        return new JAXBElement<CoordinatesDto>(_Coordinates_QNAME, CoordinatesDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "Flat")
    public JAXBElement<Object> createFlat(Object value) {
        return new JAXBElement<Object>(_Flat_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlatsGetListRequestDto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FlatsGetListRequestDto }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "FlatPage")
    public JAXBElement<FlatsGetListRequestDto> createFlatPage(FlatsGetListRequestDto value) {
        return new JAXBElement<FlatsGetListRequestDto>(_FlatPage_QNAME, FlatsGetListRequestDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlatsGetListResponseDto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FlatsGetListResponseDto }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "Flats")
    public JAXBElement<FlatsGetListResponseDto> createFlats(FlatsGetListResponseDto value) {
        return new JAXBElement<FlatsGetListResponseDto>(_Flats_QNAME, FlatsGetListResponseDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HouseDto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HouseDto }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "House")
    public JAXBElement<HouseDto> createHouse(HouseDto value) {
        return new JAXBElement<HouseDto>(_House_QNAME, HouseDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFlat }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddFlat }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "addFlat")
    public JAXBElement<AddFlat> createAddFlat(AddFlat value) {
        return new JAXBElement<AddFlat>(_AddFlat_QNAME, AddFlat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFlatResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddFlatResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "addFlatResponse")
    public JAXBElement<AddFlatResponse> createAddFlatResponse(AddFlatResponse value) {
        return new JAXBElement<AddFlatResponse>(_AddFlatResponse_QNAME, AddFlatResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountByNew }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CountByNew }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "countByNew")
    public JAXBElement<CountByNew> createCountByNew(CountByNew value) {
        return new JAXBElement<CountByNew>(_CountByNew_QNAME, CountByNew.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountByNewResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CountByNewResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "countByNewResponse")
    public JAXBElement<CountByNewResponse> createCountByNewResponse(CountByNewResponse value) {
        return new JAXBElement<CountByNewResponse>(_CountByNewResponse_QNAME, CountByNewResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFlat }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteFlat }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "deleteFlat")
    public JAXBElement<DeleteFlat> createDeleteFlat(DeleteFlat value) {
        return new JAXBElement<DeleteFlat>(_DeleteFlat_QNAME, DeleteFlat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlat }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFlat }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "getFlat")
    public JAXBElement<GetFlat> createGetFlat(GetFlat value) {
        return new JAXBElement<GetFlat>(_GetFlat_QNAME, GetFlat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlatResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFlatResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "getFlatResponse")
    public JAXBElement<GetFlatResponse> createGetFlatResponse(GetFlatResponse value) {
        return new JAXBElement<GetFlatResponse>(_GetFlatResponse_QNAME, GetFlatResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlats }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFlats }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "getFlats")
    public JAXBElement<GetFlats> createGetFlats(GetFlats value) {
        return new JAXBElement<GetFlats>(_GetFlats_QNAME, GetFlats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlatsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFlatsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "getFlatsResponse")
    public JAXBElement<GetFlatsResponse> createGetFlatsResponse(GetFlatsResponse value) {
        return new JAXBElement<GetFlatsResponse>(_GetFlatsResponse_QNAME, GetFlatsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWithMaxId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWithMaxId }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "getWithMaxId")
    public JAXBElement<GetWithMaxId> createGetWithMaxId(GetWithMaxId value) {
        return new JAXBElement<GetWithMaxId>(_GetWithMaxId_QNAME, GetWithMaxId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWithMaxIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWithMaxIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "getWithMaxIdResponse")
    public JAXBElement<GetWithMaxIdResponse> createGetWithMaxIdResponse(GetWithMaxIdResponse value) {
        return new JAXBElement<GetWithMaxIdResponse>(_GetWithMaxIdResponse_QNAME, GetWithMaxIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SumHeight }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SumHeight }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "sumHeight")
    public JAXBElement<SumHeight> createSumHeight(SumHeight value) {
        return new JAXBElement<SumHeight>(_SumHeight_QNAME, SumHeight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SumHeightResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SumHeightResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "sumHeightResponse")
    public JAXBElement<SumHeightResponse> createSumHeightResponse(SumHeightResponse value) {
        return new JAXBElement<SumHeightResponse>(_SumHeightResponse_QNAME, SumHeightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateFlat }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateFlat }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "updateFlat")
    public JAXBElement<UpdateFlat> createUpdateFlat(UpdateFlat value) {
        return new JAXBElement<UpdateFlat>(_UpdateFlat_QNAME, UpdateFlat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateFlatResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateFlatResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "updateFlatResponse")
    public JAXBElement<UpdateFlatResponse> createUpdateFlatResponse(UpdateFlatResponse value) {
        return new JAXBElement<UpdateFlatResponse>(_UpdateFlatResponse_QNAME, UpdateFlatResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificationFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VerificationFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "VerificationFault")
    public JAXBElement<VerificationFault> createVerificationFault(VerificationFault value) {
        return new JAXBElement<VerificationFault>(_VerificationFault_QNAME, VerificationFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotFoundFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NotFoundFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://api.web.ru.ifmo.se/", name = "NotFoundFault")
    public JAXBElement<NotFoundFault> createNotFoundFault(NotFoundFault value) {
        return new JAXBElement<NotFoundFault>(_NotFoundFault_QNAME, NotFoundFault.class, null, value);
    }

}
