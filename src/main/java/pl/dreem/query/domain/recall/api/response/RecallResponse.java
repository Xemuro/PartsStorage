package pl.dreem.query.domain.recall.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pl.dreem.global.DateRange;
import pl.dreem.query.domain.recall.dto.RecallDto;

import java.util.Objects;
import java.util.UUID;

@ApiModel
public final class RecallResponse {

    @ApiModelProperty("ID akcji serwisowej")
    private UUID recallId;

    @ApiModelProperty("Nazwa akcji serwisowej")
    private String name;

    @ApiModelProperty("ID części w ramach akcji serwisowej")
    private UUID partId;

    @ApiModelProperty("Czas trwania akcji serwisowej")
    private DateRange recallDuration;

    private RecallResponse(UUID recallId, String name, UUID partId, DateRange recallDuration) {
        this.recallId = Objects.requireNonNull(recallId);
        this.name = Objects.requireNonNull(name);
        this.partId = Objects.requireNonNull(partId);
        this.recallDuration = Objects.requireNonNull(recallDuration);
    }

    public UUID getRecallId() {
        return recallId;
    }

    public String getName() {
        return name;
    }

    public UUID getPartId() {
        return partId;
    }

    public DateRange getRecallDuration() {
        return recallDuration;
    }

    public static RecallResponse from(RecallDto recall){
        return new RecallResponse(recall.getId().getId(), recall.getName(), recall.getPartId().getId(), recall.getDuration());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecallResponse that = (RecallResponse) o;
        return Objects.equals(recallId, that.recallId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(partId, that.partId) &&
                Objects.equals(recallDuration, that.recallDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recallId, name, partId, recallDuration);
    }

    @Override
    public String toString() {
        return "RecallResponse{" +
                "recallId=" + recallId +
                ", name='" + name + '\'' +
                ", partId=" + partId +
                ", recallDuration=" + recallDuration +
                '}';
    }
}
