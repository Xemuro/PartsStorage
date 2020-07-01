package pl.dreem.query.domain.part.api.response;

import pl.dreem.query.domain.part.dto.SalesArgumentDto;

import java.util.Objects;
import java.util.UUID;

public final class SalesArgumentResponse {

    private final UUID argumentId;

    private final String argument;

    public SalesArgumentResponse(final UUID argumentId, final String argument) {
        this.argumentId = Objects.requireNonNull(argumentId);
        this.argument = Objects.requireNonNull(argument);
    }

    public UUID getArgumentId() {
        return argumentId;
    }

    public String getArgument() {
        return argument;
    }

    public static SalesArgumentResponse fromDto(final SalesArgumentDto salesArgument){
        return new SalesArgumentResponse(salesArgument.getId(), salesArgument.getArgument());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesArgumentResponse that = (SalesArgumentResponse) o;
        return Objects.equals(argumentId, that.argumentId) &&
                Objects.equals(argument, that.argument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(argumentId, argument);
    }

    @Override
    public String toString() {
        return "SalesArgumentResponse{" +
                "argumentId=" + argumentId +
                ", argument='" + argument + '\'' +
                '}';
    }
}
