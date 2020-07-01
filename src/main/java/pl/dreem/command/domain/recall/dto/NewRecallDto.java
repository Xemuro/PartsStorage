package pl.dreem.command.domain.recall.dto;

import pl.dreem.command.domain.recall.api.request.NewRecallRequest;
import pl.dreem.global.identity.PartId;

import java.time.LocalDate;
import java.util.Objects;

public final class NewRecallDto {

    private final String name;
    private final LocalDate recallStart;
    private final LocalDate recallFinish;
    private final PartId partId;

    private NewRecallDto(final String name, final LocalDate recallStart, final LocalDate recallFinish, final PartId partId) {
        this.name = Objects.requireNonNull(name);
        this.recallStart = Objects.requireNonNull(recallStart);
        this.recallFinish = Objects.requireNonNull(recallFinish);
        this.partId = Objects.requireNonNull(partId);
    }

    public String getName() {
        return name;
    }

    public LocalDate getRecallStart() {
        return recallStart;
    }

    public LocalDate getRecallFinish() {
        return recallFinish;
    }

    public PartId getPartId() {
        return partId;
    }

    public static NewRecallDto from(NewRecallRequest request){
        return new NewRecallDto(request.getName(), request.getRecallStart(), request.getRecallFinish(), PartId.from(request.getPartId()));
    }
}
