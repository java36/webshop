package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.model.entity.Brand;
import se.sina.webshop.model.entity.Category;
import se.sina.webshop.repository.BrandRepository;
import se.sina.webshop.repository.CategoryRepository;
import se.sina.webshop.service.exception.CategoryExceptions.CategoryNameAlreadyExists;
import se.sina.webshop.service.exception.CategoryExceptions.CategoryNameNotFound;
import se.sina.webshop.service.exception.CategoryExceptions.CategoryNumberNotFound;
import se.sina.webshop.service.exception.CategoryExceptions.CategoryUndeletable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isAllBlank;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public final class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public CategoryService(CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    public Category createCategory(Category category){
        checkDoubleNames(category.getName());
        category.setCategoryNumber(UUID.randomUUID());
        category.setActive(true);
        return categoryRepository.save(category);
    }

    public Category find(UUID categoryNumber){
        return check(categoryNumber);
    }

    public List<Category> find(String name, String active){
        List<Category> categories = new ArrayList<>();
        name = format(name);
        active = format(active);

        if (isBlank(active) || active.equals("true")) {
            if (!isBlank(name)) {
                Category category = check(name);
                if (category.getActive()) {
                    categories.add(category);
                }
                return categories;
            } else {
                return categoryRepository.findAllByActiveTrue();
            }
        } else if (active.equals("false")) {
            if (!isBlank(name)) {
                Category category = check(name);
                if (!category.getActive()) {
                    categories.add(category);
                }
                return categories;
            } else {
                return categoryRepository.findAllByActiveFalse();
            }
        } else if (isAllBlank(name, active)) {
            return categoryRepository.findAll();
        }

        return categories;
    }

    public Category update(UUID categoryNumber, Category category){
        Category existing = check(categoryNumber);
        if(category.getName() != null){
            checkDoubleNames(category.getName());
            existing.setName(category.getName());
        }
        return categoryRepository.save(existing);
    }
    public Category delete(UUID categoryNumber){
        Category category = check(categoryNumber);
        List<Brand> brands = brandRepository.findAllByActiveAndCategoryName(true, category.getName());
        if(brands.size() == 0){
            category.setActive(false);
            return categoryRepository.save(category);
        }
        throw new CategoryUndeletable("unable to delete category");
    }

    public Category check(UUID number){

        Optional<Category> category = categoryRepository.findByCategoryNumber(number);
        if(!category.isPresent()){
            throw new CategoryNumberNotFound("Category number not found");
        }
        return category.get();
    }
    public Category check(String name){
        Optional<Category> result = categoryRepository.findByName(name);
        if(!result.isPresent()){
            throw new CategoryNameNotFound("Category name not found");
        }
        return result.get();
    }
    public void checkDoubleNames(String categoryName){
        Optional<Category> result = categoryRepository.findByName(categoryName);
        if(result.isPresent()){
            throw new CategoryNameAlreadyExists("Category name already exists");
        }
    }
    public String format(String string){
        return string.trim().toLowerCase();
    }
}
