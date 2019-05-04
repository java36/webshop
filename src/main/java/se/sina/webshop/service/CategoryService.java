package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.repository.CategoryRepository;

@Service
public final class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
