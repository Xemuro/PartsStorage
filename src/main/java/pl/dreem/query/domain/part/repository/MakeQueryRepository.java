package pl.dreem.query.domain.part.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dreem.global.entity.ModelEntity;

import java.util.List;

public interface MakeQueryRepository extends JpaRepository<ModelEntity, String> {

    List<ModelEntity> findByMakeIgnoreCase(String make);

}