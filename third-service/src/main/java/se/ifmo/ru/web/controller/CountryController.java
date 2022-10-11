package se.ifmo.ru.web.controller;

import se.ifmo.ru.mapper.CountryMapper;
import se.ifmo.ru.mapper.HouseMapper;
import se.ifmo.ru.service.api.CountryService;
import se.ifmo.ru.service.api.HouseService;
import se.ifmo.ru.web.model.CountryListResponseDto;
import se.ifmo.ru.web.model.HouseListResponseDto;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/secret/countries")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class CountryController {

    @Inject
    CountryService countryService;

    @Inject
    CountryMapper countryMapper;

    @Inject
    HouseMapper houseMapper;

    @Inject
    HouseService houseService;

    @GET
    public Response getCountries() {
        return Response
                .ok()
                .entity(CountryListResponseDto
                        .builder()
                        .countryResponseDtos(
                                countryMapper.toDtoList(countryService.getCountries())
                        )
                        .build()
                )
                .build();
    }

    @Path("/{countryId}/houses")
    @GET
    public Response getHousesInCountry(@PathParam("countryId") Long countryId) {
        return Response
                .ok()
                .entity(HouseListResponseDto
                        .builder()
                        .houseResponseDtos(
                                houseMapper.toDtoList(houseService.getHousesInCountry(countryId))
                        )
                        .build()
                )
                .build();
    }
}
