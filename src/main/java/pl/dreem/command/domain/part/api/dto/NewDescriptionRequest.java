package pl.dreem.command.domain.part.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel
public final class NewDescriptionRequest {

    @ApiModelProperty(notes = "Opis części")
    private final String description;

    @JsonCreator
    public NewDescriptionRequest(final String description) {
        this.description = Objects.requireNonNull(description);
    }

    public String getDescription() {
        return description;
    }

    public static NewDescriptionRequest from(final String description){
        return new NewDescriptionRequest(description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewDescriptionRequest that = (NewDescriptionRequest) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return "NewDescriptionRequest{" +
                "description='" + description + '\'' +
                '}';
    }
}
