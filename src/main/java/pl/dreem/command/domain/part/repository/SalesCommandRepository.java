package pl.dreem.command.domain.part.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dreem.global.entity.SalesArgumentEntity;

public interface SalesCommandRepository extends JpaRepository<SalesArgumentEntity, String> {

    @Modifying
    @Query("DELETE from SalesArgument sa where sa.part.id = :partId")
    void deleteByPartId(@Param("partId") String partId);

}
