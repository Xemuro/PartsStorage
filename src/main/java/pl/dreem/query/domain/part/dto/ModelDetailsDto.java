package pl.dreem.query.domain.part.dto;

import pl.dreem.global.DateRange;
import pl.dreem.global.identity.ModelId;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public final class ModelDetailsDto {

    private final ModelId id;
    private final String model;
    private final DateRange productionRange;
    private final Set<PartDto> parts;

    private ModelDetailsDto(final ModelId id, final String model, final DateRange productionRange,
                            final  Set<PartDto> parts) {
        this.id = Objects.requireNonNull(id);
        this.model = Objects.requireNonNull(model);
        this.productionRange = Objects.requireNonNull(productionRange);
        this.parts = Objects.requireNonNull(parts);
    }

    public ModelId getId() {
        return id;
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

    public static ModelDetailsDto from(ModelDto model, Set<PartDto> parts){
        return new ModelDetailsDto(model.getId(), model.getModel(), model.getProductionRange(), parts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelDetailsDto that = (ModelDetailsDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(model, that.model) &&
                Objects.equals(productionRange, that.productionRange) &&
                Objects.equals(parts, that.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, productionRange, parts);
    }

    @Override
    public String toString() {
        return "ModelDetailsDto{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", productionRange=" + productionRange +
                ", parts=" + parts +
                '}';
    }
}
