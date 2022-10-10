package se.ifmo.ru.web.controller;

import org.apache.commons.lang3.StringUtils;
import se.ifmo.ru.mapper.FlatMapper;
import se.ifmo.ru.service.api.FlatService;
import se.ifmo.ru.service.model.Flat;
import se.ifmo.ru.storage.model.Page;
import se.ifmo.ru.util.ResponseUtils;
import se.ifmo.ru.web.model.FlatAddOrUpdateRequestDto;
import se.ifmo.ru.web.model.FlatsListGetResponseDto;
import se.ifmo.ru.web.model.NewCountResponseDto;
import se.ifmo.ru.web.model.SumHeightResponseDto;

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
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/catalog")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class CatalogController {
    @Inject
    FlatService flatService;

    @Inject
    ResponseUtils responseUtils;

    @Inject
    FlatMapper flatMapper;

    @GET
    @Path("/flats")
    public Response getFlats(@Context HttpServletRequest request) {
        String[] sortParameters = request.getParameterValues("sort");
        String[] filterParameters = request.getParameterValues("filter");

        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");
        Integer page = null, pageSize = null;

        try {
            if (StringUtils.isNotEmpty(pageParam)) {
                page = Integer.parseInt(pageParam);
                if (page <= 0) {
                    throw new NumberFormatException();
                }
            }
            if (StringUtils.isNotEmpty(pageSizeParam)) {
                pageSize = Integer.parseInt(pageSizeParam);
                if (pageSize <= 0) {
                    throw new NumberFormatException();
                }
            }
        } catch (NumberFormatException numberFormatException) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "Invalid query param value");
        }

        List<String> sort = sortParameters == null
                ? new ArrayList<>()
                : Stream.of(sortParameters).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        List<String> filter = filterParameters == null
                ? new ArrayList<>()
                : Stream.of(filterParameters).filter(StringUtils::isNotEmpty).collect(Collectors.toList());

        Page<Flat> resultPage = flatService.getFlats(
                sort,
                filter,
                page,
                pageSize
        );

        return Response
                .ok()
                .entity(new FlatsListGetResponseDto(
                                flatMapper.toGetResponseDtoList(resultPage.getObjects()),
                                resultPage.getPage(),
                                resultPage.getPageSize(),
                                resultPage.getTotalPages(),
                                resultPage.getTotalCount()
                        )
                )
                .build();
    }

    @GET
    @Path("/flats/{id}")
    public Response getFlat(@PathParam("id") long id) {
        Flat flat = flatService.getFlat(id);

        if (flat == null) {
            return responseUtils.buildResponseWithMessage(Response.Status.NOT_FOUND, "Flat with id " + id + " not found");
        }
        return Response
                .ok()
                .entity(flatMapper.toDto(flat))
                .build();
    }

    @POST
    @Path("/flats")
    public Response addFlat(FlatAddOrUpdateRequestDto requestDto) {
        Response validationResult = validateFlatAddOrUpdateRequestDto(requestDto);

        if (validationResult != null) {
            return validationResult;
        }

        Flat flat = flatService.addFlat(requestDto);

        return Response
                .ok()
                .entity(flatMapper.toDto(flat))
                .build();
    }

    @PUT
    @Path("/flats/{id}")
    public Response updateFlat(@PathParam("id") long id, FlatAddOrUpdateRequestDto requestDto) {
        Response validationResult = validateFlatAddOrUpdateRequestDto(requestDto);

        if (validationResult != null) {
            return validationResult;
        }

        Flat flat = flatService.updateFlat(id, requestDto);

        if (flat == null) {
            return responseUtils.buildResponseWithMessage(Response.Status.NOT_FOUND, "Flat with id " + id + " not found");
        }
        return Response
                .ok()
                .entity(flatMapper.toDto(flat))
                .build();
    }

    @DELETE
    @Path("/flats/{id}")
    public Response deleteFlat(@PathParam("id") long id) {
        boolean deleted = flatService.deleteFlat(id);

        if (!deleted) {
            return responseUtils.buildResponseWithMessage(Response.Status.NOT_FOUND, "Flat with id " + id + " not found");
        }

        return Response.noContent().build();
    }

    @GET
    @Path("/flats/height-sum")
    public Response sumHeight() {
        return Response
                .ok()
                .entity(SumHeightResponseDto.builder().sum(flatService.sumHeight()).build())
                .build();
    }

    @GET
    @Path("/flats/max-id")
    public Response getWithMaxId() {
        return Response
                .ok()
                .entity(flatMapper.toDto(flatService.getWithMaxId()))
                .build();
    }

    @GET
    @Path("/flats/count-by-new")
    public Response countByNew(@QueryParam("value") Boolean value) {
        return Response
                .ok()
                .entity(NewCountResponseDto.builder().count(flatService.countByNew(value)).build())
                .build();
    }

    private Response validateFlatAddOrUpdateRequestDto(FlatAddOrUpdateRequestDto requestDto) {
        if (StringUtils.isEmpty(requestDto.getName())) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "Name can not be empty");
        }
        if (requestDto.getCoordinates() == null) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "Coordinates can not be null");
        }
        if (requestDto.getArea() == null || requestDto.getArea() <= 0) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "Area must be grater than 0");
        }
        if (requestDto.getNumberOfRooms() == null || requestDto.getNumberOfRooms() <= 0) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "Number of rooms must be greater than 0");
        }
        if (requestDto.getHeight() != null && requestDto.getHeight() <= 0) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "Height must be greater than 0");
        }
        if (requestDto.getNewField() == null) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "New can not be null");
        }
        if (StringUtils.isEmpty(requestDto.getTransport())) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "Transport can not be empty");
        }
        if (requestDto.getHouse() == null) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "House can not be null");
        }
        if (requestDto.getCoordinates() == null) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "Coordinates can not be null");
        }
        if (requestDto.getCoordinates().getX() == null || requestDto.getCoordinates().getX() <= -292) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "Coordinates X must be greater than -292");
        }
        if (requestDto.getCoordinates().getY() == null || requestDto.getCoordinates().getY() <= -747) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "Coordinates Y must be greater than -747");
        }
        if (requestDto.getHouse().getName() == null) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "House name can not be null");
        }
        if (requestDto.getHouse().getYear() != null && requestDto.getHouse().getYear() <= 0) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "House year m,ust be greater than 0");
        }
        if (requestDto.getHouse().getNumberOfFloors() == null || requestDto.getHouse().getNumberOfFloors() <= 0) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "house number of floors must be greater than 0");
        }
        if (requestDto.getHouse().getNumberOfLifts() != null && requestDto.getHouse().getNumberOfLifts() != null && requestDto.getHouse().getNumberOfLifts() <= 0) {
            return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, "House number of lifts must be greater than 0");
        }

        return null;
    }
}
