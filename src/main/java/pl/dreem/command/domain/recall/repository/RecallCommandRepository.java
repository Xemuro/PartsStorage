package pl.dreem.command.domain.recall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dreem.global.entity.RecallEntity;

public interface RecallCommandRepository extends JpaRepository<RecallEntity, String> {
}
