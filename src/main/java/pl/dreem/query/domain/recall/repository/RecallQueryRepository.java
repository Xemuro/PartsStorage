package pl.dreem.query.domain.recall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dreem.global.entity.RecallEntity;

import java.time.LocalDate;
import java.util.Set;

public interface RecallQueryRepository extends JpaRepository<RecallEntity, String> {

    @Query(value = "select rc from RecallEntity rc where rc.recallStart between :startDate and :endDate or rc.recallFinish between :startDate and :endDate")
    Set<RecallEntity> findAllByRecallStartBetweenOrRecallFinishBetween(@Param("startDate") LocalDate from,
                                                                       @Param("endDate") LocalDate to);

}
