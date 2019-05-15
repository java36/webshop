package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Brand;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByBrandNumber(UUID number);
    Optional<Brand> findByName(String name);
    List<Brand> findAllByActiveAndCategoryName(Boolean active, String categoryName);
    List<Brand> findAllByActiveTrue();
    List<Brand> findAllByActiveFalse();
    Optional<Brand> findByNameAndCategoryName(String brandName, String categoryName);
}
