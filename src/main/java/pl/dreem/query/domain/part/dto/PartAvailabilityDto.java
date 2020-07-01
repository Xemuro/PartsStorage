package pl.dreem.query.domain.part.dto;

import pl.dreem.global.identity.PartId;

import java.util.Objects;
import java.util.UUID;

public final class PartAvailabilityDto {

    private final PartId id;
    private final boolean available;
    private final int numberOfDaysToShip;

    public PartAvailabilityDto(final String id, final boolean available, final int numberOfDaysToShip) {
        this.id = Objects.requireNonNull(PartId.from(UUID.fromString(id)));
        this.available = available;
        this.numberOfDaysToShip = numberOfDaysToShip;
    }

    public PartId getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getNumberOfDaysToShip() {
        return numberOfDaysToShip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartAvailabilityDto that = (PartAvailabilityDto) o;
        return available == that.available &&
                numberOfDaysToShip == that.numberOfDaysToShip &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, available, numberOfDaysToShip);
    }

    @Override
    public String toString() {
        return "PartAvailabilityDto{" +
                "id=" + id +
                ", available=" + available +
                ", numberOfDaysToShip=" + numberOfDaysToShip +
                '}';
    }
}
