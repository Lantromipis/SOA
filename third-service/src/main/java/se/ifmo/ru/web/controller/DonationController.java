package se.ifmo.ru.web.controller;

import se.ifmo.ru.service.api.DonationService;
import se.ifmo.ru.web.model.DonationRequestDto;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/secret/donate")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class DonationController {
    @Inject
    DonationService donationService;

    @POST
    @Path("/{houseId}/{sponsorId}")
    public Response donate(@PathParam("houseId") long houseId,
                           @PathParam("sponsorId") long sponsorId,
                           DonationRequestDto donationRequestDto
    ) {
        if (donationRequestDto == null || donationRequestDto.getAmount() < 1000000) {
            throw new IllegalArgumentException("Too small donation!");
        }
        donationService.donate(houseId, sponsorId, donationRequestDto.getAmount());
        return Response.noContent().build();
    }
}
