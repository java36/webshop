package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.repository.BrandRepository;
import se.sina.webshop.repository.CategoryRepository;

@Service
public final class BrandService {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public BrandService(BrandRepository brandRepository, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }
}
