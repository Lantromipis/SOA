package se.ifmo.ru.web.controller;

import org.apache.commons.lang3.StringUtils;
import se.ifmo.ru.ejb.model.Flat;
import se.ifmo.ru.ejb.model.Page;
import se.ifmo.ru.mapper.FlatMapper;
import se.ifmo.ru.soap.api.FlatGetResponseDto;
import se.ifmo.ru.soap.api.FlatsGetListRequestDto;
import se.ifmo.ru.soap.api.FlatsGetListResponseDto;
import se.ifmo.ru.soap.impl.Flats;
import se.ifmo.ru.soap.impl.NotFoundException;
import se.ifmo.ru.soap.impl.VerificationException;
import se.ifmo.ru.util.ResponseUtils;
import se.ifmo.ru.web.model.FlatAddOrUpdateRequestDto;
import se.ifmo.ru.web.model.FlatsListGetResponseDto;
import se.ifmo.ru.web.model.NewCountResponseDto;
import se.ifmo.ru.web.model.SumHeightResponseDto;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/catalog")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class CatalogController {

    @Inject
    ResponseUtils responseUtils;

    @Inject
    FlatMapper flatMapper;

    private Flats flatWebService;

    @PostConstruct
    public void init() {
        flatWebService = new Flats();
    }

    @GET
    @Path("/flats")
    public Response getFlats(@Context HttpServletRequest request) {
        String[] sortParameters = request.getParameterValues("sort");
        String[] filterParameters = request.getParameterValues("filter");

        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        List<String> sort = sortParameters == null
                ? new ArrayList<>()
                : Stream.of(sortParameters).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        List<String> filter = filterParameters == null
                ? new ArrayList<>()
                : Stream.of(filterParameters).filter(StringUtils::isNotEmpty).collect(Collectors.toList());

        FlatsGetListRequestDto requestDto = new FlatsGetListRequestDto();
        requestDto.setPage(pageParam);
        requestDto.setSize(pageSizeParam);
        requestDto.getFilter().addAll(filter);
        requestDto.getSort().addAll(sort);

        FlatsGetListResponseDto response = flatWebService.getFlatsWebServiceImplPort().getFlats(requestDto);

        return Response
                .ok()
                .entity(new FlatsListGetResponseDto(
                                flatMapper.toGetResponseDtoList(response.getFlat()),
                                response.getPage(),
                                response.getPageSize(),
                                response.getTotalPages(),
                                response.getTotalCount()
                        )
                )
                .build();
    }

    @GET
    @Path("/flats/{id}")
    public Response getFlat(@PathParam("id") long id) {
        try {
            FlatGetResponseDto response = flatWebService.getFlatsWebServiceImplPort().getFlat(id);
            return Response
                    .ok()
                    .entity(flatMapper.toDto(response))
                    .build();
        } catch (NotFoundException e) {
            return responseUtils.buildResponseWithMessage(Response.Status.NOT_FOUND, "Flat with id " + id + " not found");
        }
    }

    @POST
    @Path("/flats")
    public Response addFlat(FlatAddOrUpdateRequestDto requestDto) {
        try {
            FlatGetResponseDto response = flatWebService.getFlatsWebServiceImplPort().addFlat(flatMapper.fromDto(requestDto));

            return Response
                    .ok()
                    .entity(flatMapper.toDto(response))
                    .build();

        } catch (VerificationException e) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }

    @PUT
    @Path("/flats/{id}")
    public Response updateFlat(@PathParam("id") long id, FlatAddOrUpdateRequestDto requestDto) {
        try {
            FlatGetResponseDto response = flatWebService.getFlatsWebServiceImplPort().updateFlat(id, flatMapper.fromDto(requestDto));

            return Response
                    .ok()
                    .entity(flatMapper.toDto(response))
                    .build();

        } catch (VerificationException e) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }

    @DELETE
    @Path("/flats/{id}")
    public Response deleteFlat(@PathParam("id") long id) {
        flatWebService.getFlatsWebServiceImplPort().deleteFlat(id);

        return Response.noContent().build();
    }

    @GET
    @Path("/flats/height-sum")
    public Response sumHeight() {
        return Response
                .ok()
                .entity(
                        SumHeightResponseDto
                                .builder()
                                .sum(flatWebService.getFlatsWebServiceImplPort().sumHeight())
                                .build()
                )
                .build();
    }

    @GET
    @Path("/flats/max-id")
    public Response getWithMaxId() throws NotFoundException {
        FlatGetResponseDto response = flatWebService.getFlatsWebServiceImplPort().getWithMaxId();

        return Response
                .ok()
                .entity(flatMapper.toDto(response))
                .build();
    }

    @GET
    @Path("/flats/count-by-new")
    public Response countByNew(@QueryParam("value") Boolean value) {
        return Response
                .ok()
                .entity(
                        NewCountResponseDto
                                .builder()
                                .count(flatWebService.getFlatsWebServiceImplPort().countByNew(value))
                                .build()
                )
                .build();
    }
}
