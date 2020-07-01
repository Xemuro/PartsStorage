package pl.dreem.query.domain.part.dto;

import pl.dreem.global.entity.SalesArgumentEntity;

import java.util.Objects;
import java.util.UUID;

public final class SalesArgumentDto {

    private final UUID id;
    private final String argument;

    public SalesArgumentDto(final UUID id, final String argument) {
        this.id = Objects.requireNonNull(id);
        this.argument = Objects.requireNonNull(argument);
    }

    public UUID getId() {
        return id;
    }

    public String getArgument() {
        return argument;
    }

    public static SalesArgumentDto fromEntity(SalesArgumentEntity entity){
        return new SalesArgumentDto(UUID.fromString(entity.getId()), entity.getArgument());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesArgumentDto that = (SalesArgumentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(argument, that.argument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, argument);
    }

    @Override
    public String toString() {
        return "SalesArgumentDto{" +
                "id=" + id +
                ", argument='" + argument + '\'' +
                '}';
    }
}
