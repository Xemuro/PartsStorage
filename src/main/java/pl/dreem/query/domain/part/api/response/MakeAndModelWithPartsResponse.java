package pl.dreem.query.domain.part.api.response;

import pl.dreem.query.domain.part.dto.ModelWithPartDto;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class MakeAndModelWithPartsResponse {

    private final String make;

    private final String model;

    private final Set<PartDetailsResponse> parts;

    public MakeAndModelWithPartsResponse(final String make,
                                         final String model,
                                         final Set<PartDetailsResponse> parts) {
        this.make = Objects.requireNonNull(make);
        this.model = Objects.requireNonNull(model);
        this.parts = Objects.requireNonNull(parts);
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Set<PartDetailsResponse> getParts() {
        return parts;
    }

    public static MakeAndModelWithPartsResponse fromDto(ModelWithPartDto modelWithParts){
        final Set<PartDetailsResponse> parts = modelWithParts.getParts()
                                                           .stream()
                                                           .map(PartDetailsResponse::fromDto)
                                                           .collect(Collectors.toUnmodifiableSet());

        return new MakeAndModelWithPartsResponse(modelWithParts.getMake(), modelWithParts.getModel(), parts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MakeAndModelWithPartsResponse that = (MakeAndModelWithPartsResponse) o;
        return Objects.equals(make, that.make) &&
                Objects.equals(model, that.model) &&
                Objects.equals(parts, that.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, parts);
    }

    @Override
    public String toString() {
        return "MakeAndModelWithPartsResponse{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", parts=" + parts +
                '}';
    }
}
