package pl.dreem.query.domain.part.dto;

import org.apache.logging.log4j.util.Strings;

import java.util.Objects;
import java.util.Optional;

public final class PartFilterDto {

    private final String name;
    private final String description;

    private PartFilterDto(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNameForQuery() {
        return name == null ? Strings.EMPTY : name;
    }

    public String getDescriptionForQuery() {
        return description == null ? Strings.EMPTY : description;
    }

    public Optional<String> getNameAsOptional() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getDescriptionAsOptional() {
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
