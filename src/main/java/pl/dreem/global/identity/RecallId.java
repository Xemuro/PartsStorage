package pl.dreem.global.identity;

import java.util.Objects;
import java.util.UUID;

public final class RecallId {

    private final UUID id;

    private RecallId(final UUID id) {
        this.id = Objects.requireNonNull(id);
    }

    public UUID getId() {
        return id;
    }

    public static RecallId from(final UUID id){
        return new RecallId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecallId recallId = (RecallId) o;
        return Objects.equals(id, recallId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RecallId{" +
                "id=" + id +
                '}';
    }
}
