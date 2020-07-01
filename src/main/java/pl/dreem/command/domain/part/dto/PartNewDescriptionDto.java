package pl.dreem.command.domain.part.dto;

import pl.dreem.global.identity.PartId;
import pl.dreem.command.domain.part.api.dto.NewDescriptionRequest;

import java.util.Objects;
import java.util.UUID;

public final class PartNewDescriptionDto {

    private final UUID id;
    private final String description;

    private PartNewDescriptionDto(final UUID id, final String description) {
        this.id = Objects.requireNonNull(id);
        this.description = Objects.requireNonNull(description);
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static PartNewDescriptionDto from(final PartId partId, final NewDescriptionRequest request){
        return new PartNewDescriptionDto(partId.getId(), request.getDescription());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartNewDescriptionDto that = (PartNewDescriptionDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "PartNewDescriptionDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
