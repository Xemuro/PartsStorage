package pl.dreem.query.domain.part.dto;

import java.util.Objects;
import java.util.Optional;

public final class PartFilterDto {

    private final String name;
    private final String description;

    private PartFilterDto(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public static PartFilterDto from(final String model, final String description){
        return new PartFilterDto(model, description);
    }

    public boolean isFilteringRequired(){
        return name != null || description != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartFilterDto that = (PartFilterDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "PartFilterDto{" +
                "partName='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
