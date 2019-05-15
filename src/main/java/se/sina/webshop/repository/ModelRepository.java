package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModelRepository extends JpaRepository<Model, Long> {
    Optional<Model> findByModelNumber(UUID number);
    Optional<Model> findByName(String name);
    List<Model> findAllByActiveAndBrandName(Boolean active, String brandName);
    List<Model> findAllByActiveTrue();
    List<Model> findAllByActiveFalse();
    Optional<Model> findByNameAndBrandName(String modelName, String brandName);
}
