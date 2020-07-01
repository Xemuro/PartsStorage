package pl.dreem.query.domain.part.dto;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public final class MakeDetailsDto {

    private final String make;
    private final Set<ModelDetailsDto> models;

    private MakeDetailsDto(final String make, final Set<ModelDetailsDto> models) {
        this.make = Objects.requireNonNull(make);
        this.models = Objects.requireNonNull(models);
    }

    public String getMake() {
        return make;
    }

    public Set<ModelDetailsDto> getModels() {
        return Collections.unmodifiableSet(models);
    }

    public static MakeDetailsDto from(String make, Set<ModelDetailsDto> models){
        return new MakeDetailsDto(make, models);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MakeDetailsDto that = (MakeDetailsDto) o;
        return Objects.equals(make, that.make) &&
                Objects.equals(models, that.models);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, models);
    }

    @Override
    public String toString() {
        return "MakeDetailsDto{" +
                "make='" + make + '\'' +
                ", models=" + models +
                '}';
    }
}
