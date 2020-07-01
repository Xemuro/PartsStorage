package pl.dreem.query.domain.recall.dto;

import pl.dreem.global.DateRange;
import pl.dreem.global.entity.RecallEntity;
import pl.dreem.global.identity.PartId;
import pl.dreem.global.identity.RecallId;

import java.util.Objects;

public final class RecallDto {

    private final RecallId id;
    private final String name;
    private final PartId partId;
    private final DateRange duration;

    public RecallDto(final RecallId id, final String name, PartId partId, final DateRange duration) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.partId = Objects.requireNonNull(partId);
        this.duration = Objects.requireNonNull(duration);
    }

    public RecallId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PartId getPartId() {
        return partId;
    }

    public DateRange getDuration() {
        return duration;
    }

    public static RecallDto fromEntity(RecallEntity entity) {
        return new RecallDto(entity.getRecallId(), entity.getName(), entity.getPartIdAsId(),
                             DateRange.from(entity.getRecallStart(), entity.getRecallFinish()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecallDto recallDto = (RecallDto) o;
        return Objects.equals(id, recallDto.id) &&
                Objects.equals(name, recallDto.name) &&
                Objects.equals(partId, recallDto.partId) &&
                Objects.equals(duration, recallDto.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, partId, duration);
    }

    @Override
    public String toString() {
        return "RecallDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", partId=" + partId +
                ", duration=" + duration +
                '}';
    }
}
