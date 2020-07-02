package pl.dreem.query.domain.part.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dreem.global.entity.PartEntity;
import pl.dreem.query.domain.part.dto.PartAvailabilityDto;

import java.util.Optional;
import java.util.Set;

public interface PartQueryRepository extends JpaRepository<PartEntity, String>, JpaSpecificationExecutor<PartEntity> {

    @Query("SELECT DISTINCT p FROM part p LEFT JOIN FETCH p.salesArguments a LEFT JOIN p.modelsDetails " +
            "WHERE (:name is null OR upper(p.name) LIKE upper(concat('%', :name,'%'))) " +
            "AND (:description is null OR upper(p.description) LIKE upper(concat('%', :description,'%')))")
    Set<PartEntity> findAllByFilters(@Param("name") String name, @Param("description") final String description);

    @Query("SELECT DISTINCT p FROM part p LEFT JOIN FETCH p.salesArguments JOIN p.modelsDetails mp WHERE mp.id = :modelId")
    Set<PartEntity> findAllByModelsDetails(@Param("modelId") String modelId);

    @Query("SELECT new pl.dreem.query.domain.part.dto.PartAvailabilityDto(p.id, p.available, p.numberOfDaysToShip) FROM part p WHERE p.id = :partId")
    Optional<PartAvailabilityDto> findByPartId(@Param("partId") String partId);

}
