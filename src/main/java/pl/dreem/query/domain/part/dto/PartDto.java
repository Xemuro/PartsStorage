package pl.dreem.query.domain.part.dto;

import pl.dreem.global.entity.PartEntity;
import pl.dreem.global.identity.PartId;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class PartDto {

    private final PartId partId;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final boolean available;
    private final int numberOfDaysToShip;
    private final Set<SalesArgumentDto> salesArguments;

    private PartDto(final PartId partId, final String name, final String description, final BigDecimal price, final boolean available,
                    final int numberOfDaysToShip, final Set<SalesArgumentDto> salesArguments) {
        this.partId = Objects.requireNonNull(partId);
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.price = Objects.requireNonNull(price);
        this.available = available;
        this.numberOfDaysToShip = numberOfDaysToShip;
        this.salesArguments = Objects.requireNonNull(salesArguments);
    }

    public PartId getPartId() {
        return partId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getNumberOfDaysToShip() {
        return numberOfDaysToShip;
    }

    public Set<SalesArgumentDto> getSalesArguments() {
        return Collections.unmodifiableSet(salesArguments);
    }

    public static PartDto fromEntity(final PartEntity entity) {
        final Set<SalesArgumentDto> salesArguments = entity.getSalesArguments()
                                                           .stream()
                                                           .map(SalesArgumentDto::fromEntity)
                                                           .collect(Collectors.toUnmodifiableSet());

        return new PartDto(entity.getPartId(), entity.getName(), entity.getDescription(), entity.getPrice(),
                           entity.isAvailable(), entity.getNumberOfDaysToShip(), salesArguments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartDto that = (PartDto) o;
        return available == that.available &&
                numberOfDaysToShip == that.numberOfDaysToShip &&
                Objects.equals(partId, that.partId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(salesArguments, that.salesArguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partId, name, description, price, available, numberOfDaysToShip, salesArguments);
    }

    @Override
    public String toString() {
        return "PartDetailsDto{" +
                "partId=" + partId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", numberOfDaysToShip=" + numberOfDaysToShip +
                ", salesArguments=" + salesArguments +
                '}';
    }
}
