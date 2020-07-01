package pl.dreem.query.domain.part.jpa.specification;

import org.springframework.data.jpa.domain.Specification;
import pl.dreem.global.entity.PartEntity;
import pl.dreem.query.domain.part.dto.PartFilterDto;

import java.util.Objects;

public final class PartSpecification {

    private final PartFilterDto filter;

    private PartSpecification(final PartFilterDto filter) {
        this.filter = Objects.requireNonNull(filter);
    }

    public static PartSpecification from(final PartFilterDto filter) {
        return new PartSpecification(filter);
    }

    public Specification<PartEntity> getSpecification() {
        if (filter.getName().isPresent() && filter.getDescription().isPresent()) {
            return Specification.where(getNameCriteria(filter.getName().get()))
                                .or(getDescriptionCriteria(filter.getDescription().get()));
        } else if (filter.getName().isPresent()) {
            return Specification.where(getNameCriteria(filter.getName().get()));
        } else if (filter.getDescription().isPresent()) {
            return Specification.where(getDescriptionCriteria(filter.getDescription().get()));
        } else {
            throw new RuntimeException("Something wrong with logic");
        }
    }

    private static Specification<PartEntity> getNameCriteria(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("name")),
                                                                      "%" + name.toUpperCase() + "%");
    }

    private static Specification<PartEntity> getDescriptionCriteria(String description) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.upper(root.get("description")), "%" + description.toUpperCase() + "%");
    }
}
