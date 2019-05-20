package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryNumber(UUID categoryNumber);
    Optional<Category> findByName(String name);
    List<Category> findAllByActiveTrue();
    List<Category> findAllByActiveFalse();
}
