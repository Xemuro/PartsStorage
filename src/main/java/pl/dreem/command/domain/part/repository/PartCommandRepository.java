package pl.dreem.command.domain.part.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dreem.global.entity.PartEntity;

public interface PartCommandRepository extends JpaRepository<PartEntity, String> {

    @Modifying
    @Query("UPDATE part SET description = :description where id = :id")
    void changePartDescription(@Param("id") String id, @Param("description") String description);

}
