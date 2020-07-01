package pl.dreem.query.domain.recall.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dreem.global.DateRange;
import pl.dreem.query.domain.recall.api.response.RecallResponse;
import pl.dreem.query.domain.recall.service.RecallQueryService;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Api("Controller do pobierania informacji o akcjach serwisowych - query")
@RestController
@RequestMapping("/query/v1/recall")
public final class RecallQueryController {

    private final RecallQueryService service;

    public RecallQueryController(RecallQueryService service) {
        this.service = service;
    }

    @ApiOperation("Pobranie akcji serwisowych po zakresie dat")
    @GetMapping
    public ResponseEntity<Set<RecallResponse>> getRecallAction(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate to) {
        final Set<RecallResponse> response = service.getRecalls(DateRange.from(from, to))
                                                    .stream()
                                                    .map(RecallResponse::from)
                                                    .collect(Collectors.toUnmodifiableSet());
        return ResponseEntity.ok(response);
    }
}