package pl.dreem.query.domain.recall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dreem.global.entity.RecallEntity;

import java.time.LocalDate;
import java.util.Set;

public interface RecallQueryRepository extends JpaRepository<RecallEntity, String> {

    Set<RecallEntity> findAllByRecallStartIsGreaterThanEqualAndRecallFinishLessThanEqual(LocalDate from, LocalDate to);

}
