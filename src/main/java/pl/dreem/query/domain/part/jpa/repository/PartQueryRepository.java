package pl.dreem.query.domain.part.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dreem.global.entity.PartEntity;
import pl.dreem.query.domain.part.dto.PartAvailabilityDto;

import java.util.Optional;
import java.util.Set;

public interface PartQueryRepository extends JpaRepository<PartEntity, String>, JpaSpecificationExecutor<PartEntity> {

    @Query("SELECT p FROM part p JOIN p.modelsDetails mp WHERE mp.id = :modelId")
    Set<PartEntity> findAlLByModelsDetails(@Param("modelId") String modelId);

    @Query("SELECT new pl.dreem.query.domain.part.dto.PartAvailabilityDto(p.id, p.available, p.numberOfDaysToShip) FROM part p WHERE p.id = :partId")
    Optional<PartAvailabilityDto> findByPartId(@Param("partId") String partId);

}
