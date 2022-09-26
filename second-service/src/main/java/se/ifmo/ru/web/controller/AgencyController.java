package se.ifmo.ru.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.ifmo.ru.mapper.FlatMapper;
import se.ifmo.ru.service.api.AgencyService;
import se.ifmo.ru.web.model.CostSumResponseDto;
import se.ifmo.ru.web.model.FlatGetResponseDto;

@RestController
@RequestMapping("/agency")
public class AgencyController {
    private AgencyService agencyService;
    private FlatMapper flatMapper;

    public AgencyController(AgencyService agencyService, FlatMapper flatMapper) {
        this.agencyService = agencyService;
        this.flatMapper = flatMapper;
    }

    @GetMapping("/get-cheapest/{id1}/{id2}")
    public ResponseEntity<FlatGetResponseDto> getCheapest(@PathVariable("id1") long id1, @PathVariable("id2") long id2) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(flatMapper.toDto(agencyService.getCheapest(id1, id2)));
    }

    @GetMapping("/get-total-cost")
    public ResponseEntity<CostSumResponseDto> getTotalCost() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(CostSumResponseDto
                        .builder()
                        .sum(agencyService.getTotalCost())
                        .build()
                );
    }
}
