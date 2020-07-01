package pl.dreem.query.domain.part.dto;

import pl.dreem.global.identity.ModelId;
import pl.dreem.global.DateRange;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public final class ModelWithPartDto {

    private final ModelId id;
    private final String make;
    private final String model;
    private final DateRange productionRange;
    private final Set<PartDto> parts;

    private ModelWithPartDto(final ModelId id,
                             final String make,
                             final String model,
                             final DateRange productionRange,
                             final Set<PartDto> parts) {
        this.id = Objects.requireNonNull(id);
        this.make = Objects.requireNonNull(make);
        this.model = Objects.requireNonNull(model);
        this.productionRange = Objects.requireNonNull(productionRange);
        this.parts = Objects.requireNonNull(parts);
    }

    public ModelId getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public DateRange getProductionRange() {
        return productionRange;
    }

    public Set<PartDto> getParts() {
        return Collections.unmodifiableSet(parts);
    }

    public static ModelWithPartDto from(final ModelDto modelDetails, final Set<PartDto> partsDetails) {
        return new ModelWithPartDto(modelDetails.getId(), modelDetails.getMake(), modelDetails.getModel(),
                                    modelDetails.getProductionRange(), partsDetails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelWithPartDto that = (ModelWithPartDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(make, that.make) &&
                Objects.equals(model, that.model) &&
                Objects.equals(productionRange, that.productionRange) &&
                Objects.equals(parts, that.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, productionRange, parts);
    }

    @Override
    public String toString() {
        return "ModelWithPartDto{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", productionRange=" + productionRange +
                ", parts=" + parts +
                '}';
    }
}
