package pl.dreem.query.domain.part.api.response;

import pl.dreem.global.identity.PartId;
import pl.dreem.query.domain.part.dto.PartDto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public final class PartDetailsResponse {

    private final String partId;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final boolean available;
    private final int numberOfDaysToShip;
    private final Set<SalesArgumentResponse> salesArguments;

    public PartDetailsResponse(final PartId partId, final String name, final String description, final BigDecimal price,
                               final boolean available, final int numberOfDaysToShip,
                               final Set<SalesArgumentResponse> salesArguments) {
        this.partId = Objects.requireNonNull(partId.getIdAsLiteral());
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.price = Objects.requireNonNull(price);
        this.available = available;
        this.numberOfDaysToShip = numberOfDaysToShip;
        this.salesArguments = Objects.requireNonNull(salesArguments);
    }

    public String getPartId() {
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

    public Set<SalesArgumentResponse> getSalesArguments() {
        return salesArguments;
    }

    public static PartDetailsResponse fromDto(PartDto partDetails){
        final Set<SalesArgumentResponse> salesArguments = partDetails.getSalesArguments()
                                                                     .stream()
                                                                     .map(SalesArgumentResponse::fromDto)
                                                                     .collect(Collectors.toUnmodifiableSet());
        return new PartDetailsResponse(partDetails.getPartId(), partDetails.getName(), partDetails.getDescription(),
                                       partDetails.getPrice(), partDetails.isAvailable(),
                                       partDetails.getNumberOfDaysToShip(), salesArguments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartDetailsResponse that = (PartDetailsResponse) o;
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
        return "PartDetailsResponse{" +
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
