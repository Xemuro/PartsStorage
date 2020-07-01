package pl.dreem.command.domain.recall.api.response;

import pl.dreem.global.identity.RecallId;

import java.util.Objects;
import java.util.UUID;

public class NewRecallResponse {

    private final UUID recallId;

    private NewRecallResponse(final UUID recallId) {
        this.recallId = Objects.requireNonNull(recallId);
    }

    public UUID getRecallId() {
        return recallId;
    }

    public static NewRecallResponse from(final RecallId recallId){
        return new NewRecallResponse(recallId.getId());
    }
}
