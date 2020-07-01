package pl.dreem.global.identity;

import com.fasterxml.jackson.annotation.JsonCreator;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Objects;
import java.util.UUID;

public final class PartId {

    private final UUID id;

    @JsonCreator
    public PartId(final UUID id) {
        this.id = Objects.requireNonNull(id);
    }

    public UUID getId() {
        return id;
    }

    @ApiIgnore
    public String getIdAsLiteral(){
        return id.toString();
    }

    public static PartId from(final UUID id){
        return new PartId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartId partId = (PartId) o;
        return Objects.equals(id, partId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PartId{" +
                "id=" + id +
                '}';
    }
}
