package pl.dreem.query.domain.part.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pl.dreem.query.domain.part.dto.MakeDetailsDto;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ApiModel
public final class MakeDetailsResponse {

    @ApiModelProperty("Marka")
    private final String make;

    @ApiModelProperty("Modele z detalami")
    private final Set<ModelDetailsResponse> models;

    public MakeDetailsResponse(final String make, final Set<ModelDetailsResponse> models) {
        this.make = Objects.requireNonNull(make);
        this.models = Objects.requireNonNull(models);
    }

    public String getMake() {
        return make;
    }

    public Set<ModelDetailsResponse> getModels() {
        return models;
    }

    public static MakeDetailsResponse from(MakeDetailsDto makeDetails) {
        final Set<ModelDetailsResponse> modelsDetails = makeDetails.getModels()
                                                                   .stream()
                                                                   .map(model -> ModelDetailsResponse.from(model))
                                                                   .collect(Collectors.toUnmodifiableSet());
        return new MakeDetailsResponse(makeDetails.getMake(), modelsDetails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MakeDetailsResponse that = (MakeDetailsResponse) o;
        return Objects.equals(make, that.make) &&
                Objects.equals(models, that.models);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, models);
    }

    @Override
    public String toString() {
        return "MakeDetailsResponse{" +
                "make='" + make + '\'' +
                ", models=" + models +
                '}';
    }
}
