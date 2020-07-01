package pl.dreem.command.domain.recall.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pl.dreem.global.DateRange;
import pl.dreem.global.identity.PartId;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@ApiModel
public final class NewRecallRequest {

    @ApiModelProperty(notes = "Nazwa części")
    private final String name;

    @ApiModelProperty(notes = "Start akcji serwisowej")
    private final LocalDate recallStart;

    @ApiModelProperty(notes = "Koniec akcji serwisowej")
    private final LocalDate recallFinish;

    @ApiModelProperty(notes = "ID częsci")
    private final UUID partId;

    @JsonCreator
    public NewRecallRequest(final String name, final LocalDate recallStart, final LocalDate recallFinish, final UUID partId) {
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

    public UUID getPartId() {
        return partId;
    }

    public static NewRecallRequest from(String name, DateRange duration, PartId partId){
        return new NewRecallRequest(name, duration.getFrom(), duration.getTo(), partId.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewRecallRequest that = (NewRecallRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(recallStart, that.recallStart) &&
                Objects.equals(recallFinish, that.recallFinish) &&
                Objects.equals(partId, that.partId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, recallStart, recallFinish, partId);
    }

    @Override
    public String toString() {
        return "NewRecallRequest{" +
                "name='" + name + '\'' +
                ", recallStart=" + recallStart +
                ", recallFinish=" + recallFinish +
                ", partId=" + partId +
                '}';
    }
}
