package pl.dreem.query.domain.part.api.response;

import pl.dreem.global.DateRange;
import pl.dreem.global.identity.ModelId;
import pl.dreem.query.domain.part.dto.ModelDetailsDto;
import pl.dreem.query.domain.part.dto.ModelDto;
import pl.dreem.query.domain.part.dto.PartDto;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public final class ModelDetailsResponse {

    private final String modelId;
    private final String model;
    private final DateRange productionRange;
    private final Set<PartDetailsResponse> parts;

    public ModelDetailsResponse(final ModelId modelId, final String model, final DateRange productionRange,
                                final Set<PartDetailsResponse> parts) {
        this.modelId = Objects.requireNonNull(modelId.getIdAsLiteral());
        this.model = Objects.requireNonNull(model);
        this.productionRange = Objects.requireNonNull(productionRange);
        this.parts = Objects.requireNonNull(parts);
    }

    public String getModelId() {
        return modelId;
    }

    public String getModel() {
        return model;
    }

    public DateRange getProductionRange() {
        return productionRange;
    }

    public Set<PartDetailsResponse> getParts() {
        return Collections.unmodifiableSet(parts);
    }

    public static ModelDetailsResponse from(ModelDetailsDto model) {
        final Set<PartDetailsResponse> partsResponse = model.getParts()
                                                            .stream()
                                                            .map(PartDetailsResponse::fromDto)
                                                            .collect(Collectors.toUnmodifiableSet());
        return new ModelDetailsResponse(model.getId(), model.getModel(), model.getProductionRange(), partsResponse);
    }

    public static ModelDetailsResponse from(ModelDto model, Set<PartDto> parts) {
        final Set<PartDetailsResponse> partsResponse = parts.stream()
                                                            .map(PartDetailsResponse::fromDto)
                                                            .collect(Collectors.toUnmodifiableSet());
        return new ModelDetailsResponse(model.getId(), model.getModel(), model.getProductionRange(), partsResponse);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelDetailsResponse that = (ModelDetailsResponse) o;
        return Objects.equals(modelId, that.modelId) &&
                Objects.equals(model, that.model) &&
                Objects.equals(productionRange, that.productionRange) &&
                Objects.equals(parts, that.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, model, productionRange, parts);
    }

    @Override
    public String toString() {
        return "ModelDetailsResponse{" +
                "id=" + modelId +
                ", model='" + model + '\'' +
                ", productionRange=" + productionRange +
                ", parts=" + parts +
                '}';
    }
}
