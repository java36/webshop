package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
