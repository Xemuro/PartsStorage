package pl.dreem.command.domain.recall.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dreem.command.domain.recall.api.request.NewRecallRequest;
import pl.dreem.command.domain.recall.api.response.NewRecallResponse;
import pl.dreem.command.domain.recall.dto.NewRecallDto;
import pl.dreem.command.domain.recall.service.RecallCommandService;
import pl.dreem.global.identity.RecallId;

import java.util.Objects;

@Api("Controller do zmian w akcjach serwisowych - command")
@RequestMapping("/command/v1/recall")
@RestController
public class RecallCommandController {

    private final RecallCommandService service;

    public RecallCommandController(final RecallCommandService service) {
        this.service = Objects.requireNonNull(service);
    }

    @ApiOperation("Endpoint do stworzenia nowej akcji serwisowej dla części")
    @PostMapping
    public ResponseEntity<NewRecallResponse> createNewRecall(@RequestBody final NewRecallRequest request){
        final NewRecallDto newRecall = NewRecallDto.from(request);
        final RecallId recallId = service.createNewRecall(newRecall);
        return ResponseEntity.ok(NewRecallResponse.from(recallId));
    }
}