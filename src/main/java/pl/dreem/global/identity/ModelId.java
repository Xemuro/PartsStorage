package pl.dreem.global.identity;

import springfox.documentation.annotations.ApiIgnore;

import java.util.Objects;
import java.util.UUID;

public final class ModelId {

    private UUID id;

    private ModelId(final UUID id) {
        this.id = Objects.requireNonNull(id);
    }

    public UUID getId() {
        return id;
    }

    @ApiIgnore
    public String getIdAsLiteral(){
        return id.toString();
    }

    public static ModelId from(UUID id){
        return new ModelId(id);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelId modelId = (ModelId) o;
        return Objects.equals(id, modelId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ModelId{" +
                "id=" + id +
                '}';
    }
}
