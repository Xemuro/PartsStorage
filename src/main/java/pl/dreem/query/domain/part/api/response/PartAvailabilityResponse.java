package pl.dreem.query.domain.part.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pl.dreem.query.domain.part.dto.PartAvailabilityDto;

import java.util.Objects;
import java.util.UUID;

@ApiModel
public final class PartAvailabilityResponse {

    @ApiModelProperty(notes = "ID części")
    private final UUID partId;

    @ApiModelProperty(notes = "Dostpenść części")
    private final boolean available;

    @ApiModelProperty(notes = "Czas dostawy części")
    private final int numberOfDaysToShip;

    private PartAvailabilityResponse(final UUID partId, final boolean available, final int numberOfDaysToShip) {
        this.partId = Objects.requireNonNull(partId);
        this.available = available;
        this.numberOfDaysToShip = numberOfDaysToShip;
    }

    public UUID getPartId() {
        return partId;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getNumberOfDaysToShip() {
        return numberOfDaysToShip;
    }

    public static PartAvailabilityResponse from(final PartAvailabilityDto partAvailability){
        return new PartAvailabilityResponse(partAvailability.getId().getId(), partAvailability.isAvailable(), partAvailability.getNumberOfDaysToShip());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartAvailabilityResponse that = (PartAvailabilityResponse) o;
        return available == that.available &&
                numberOfDaysToShip == that.numberOfDaysToShip &&
                Objects.equals(partId, that.partId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partId, available, numberOfDaysToShip);
    }

    @Override
    public String toString() {
        return "PartAvailabilityResponse{" +
                "partId=" + partId +
                ", available=" + available +
                ", numberOfDaysToShip=" + numberOfDaysToShip +
                '}';
    }
}
