package pl.dreem.query.domain.part.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dreem.global.identity.PartId;
import pl.dreem.query.domain.part.api.response.PartAvailabilityResponse;
import pl.dreem.query.domain.part.service.PartQueryService;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Api("Controller do pobrania częsci - query")
@RestController
@RequestMapping("/query/v1/part")
final class PartQueryController {

    private final PartQueryService service;

    public PartQueryController(final PartQueryService service) {
        this.service = Objects.requireNonNull(service);
    }

    @ApiOperation("Endpoint do pobrania dostępności części")
    @GetMapping("/{partId}/availability")
    public ResponseEntity<PartAvailabilityResponse> getPartAvailability(@PathVariable("partId") final UUID partIdRequest){
        final PartId partId = PartId.from(partIdRequest);
        final Optional<PartAvailabilityResponse> response = service.getPartAvailability(partId)
                                                                   .map(PartAvailabilityResponse::from);
        return ResponseEntity.of(response);
    }
}
