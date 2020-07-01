package pl.dreem.query.domain.part.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dreem.query.domain.part.api.response.MakeAndModelWithPartsResponse;
import pl.dreem.query.domain.part.api.response.MakeDetailsResponse;
import pl.dreem.query.domain.part.dto.MakeDetailsDto;
import pl.dreem.query.domain.part.dto.ModelWithPartDto;
import pl.dreem.query.domain.part.dto.PartFilterDto;
import pl.dreem.query.domain.part.service.MakeQueryService;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Api("Controller służacy do czytania marek samochodów. - query")
@RestController
@RequestMapping("/query/v1/make")
public class MakeQueryController {

    private final MakeQueryService service;

    public MakeQueryController(final MakeQueryService service) {
        this.service = Objects.requireNonNull(service);
    }

    @ApiOperation("Endpoint do pobrania modeli marki z częściami")
    @GetMapping
    public ResponseEntity<MakeDetailsResponse> getModelsByMake(@RequestParam("make") final String make){
        final MakeDetailsDto makeDetails = service.getMakeDetails(make);
        final MakeDetailsResponse response = MakeDetailsResponse.from(makeDetails);
        return ResponseEntity.ok(response);
    }

    @ApiOperation("Endpoint do pobrania części z modelem i marką")
    @GetMapping("/parts")
    public ResponseEntity<Set<MakeAndModelWithPartsResponse>> getPartsForMake(@RequestParam(value = "part", required = false) final String partName,
                                                                              @RequestParam(value = "description", required = false) final String description) {

        final PartFilterDto filter = PartFilterDto.from(partName, description);
        final Set<ModelWithPartDto> makeWithParts = service.getMakeAndModelWithParts(filter);
        final Set<MakeAndModelWithPartsResponse> response = makeWithParts.stream()
                                                                         .map(MakeAndModelWithPartsResponse::fromDto)
                                                                         .collect(Collectors.toUnmodifiableSet());
        return ResponseEntity.ok(response);
    }
}