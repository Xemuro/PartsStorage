package pl.dreem.query.domain.part.dto;

import pl.dreem.global.DateRange;
import pl.dreem.global.entity.ModelEntity;
import pl.dreem.global.identity.ModelId;

import java.util.Objects;

public final class ModelDto {

    private final ModelId id;
    private final String make;
    private final String model;
    private final DateRange productionRange;

    public ModelDto(final ModelId id, final String make, final String model, final DateRange productionRange) {
        this.id = Objects.requireNonNull(id);
        this.make = Objects.requireNonNull(make);
        this.model = Objects.requireNonNull(model);
        this.productionRange = Objects.requireNonNull(productionRange);
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

    public static ModelDto fromEntity(final ModelEntity entity){
        return new ModelDto(entity.getModelId(), entity.getMake(), entity.getModel(), entity.getProductionRange());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelDto modelDto = (ModelDto) o;
        return Objects.equals(id, modelDto.id) &&
                Objects.equals(make, modelDto.make) &&
                Objects.equals(model, modelDto.model) &&
                Objects.equals(productionRange, modelDto.productionRange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, productionRange);
    }

    @Override
    public String toString() {
        return "MakeDto{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", productionRange=" + productionRange +
                '}';
    }
}
