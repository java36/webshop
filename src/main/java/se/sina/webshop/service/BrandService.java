package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.model.entity.Brand;
import se.sina.webshop.model.entity.Category;
import se.sina.webshop.repository.BrandRepository;
import se.sina.webshop.repository.CategoryRepository;
import se.sina.webshop.service.exception.BrandExceptions.BrandNameNotFound;
import se.sina.webshop.service.exception.BrandExceptions.BrandNumberNotFound;
import se.sina.webshop.service.exception.CategoryExceptions.CategoryNameNotFound;
import se.sina.webshop.service.exception.CategoryExceptions.CategoryNumberNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isAllBlank;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public final class BrandService {

    private final BrandRepository brandRepository;
    private final CategoryService categoryService;

    public BrandService(BrandRepository brandRepository, CategoryService categoryService) {
        this.brandRepository = brandRepository;
        this.categoryService = categoryService;
    }

    public Brand createBrand(Brand brand){
        brand.setBrandNumber(UUID.randomUUID());
        brand.setActive(true);
        brand.setCategory(categoryService.check(brand.getCategory().getCategoryNumber()));
        return brandRepository.save(brand);
    }

    public Brand find(UUID brandNumber) {
        return check(brandNumber);
    }

    public List<Brand> find(String name, String category, String active) {
        List<Brand> brands = new ArrayList<>();
        name = format(name);
        category = format(category);
        active = format(active);

        if (isBlank(active) || active.equals("true")) {
            if (!isBlank(name) && isBlank(category)) {
                Brand brand = check(name);
                if (brand.getActive()) {
                    brands.add(brand);
                }
                return brands;
            } else if (!isBlank(category) && isBlank(name)) {
                return brandRepository.findAllByActiveAndCategoryName(true, category);
            } else if (!isAllBlank(name, category)) {
                Brand brand = check(name);
                if (brand.getCategory().getName().equalsIgnoreCase(category) && brand.getActive()) {
                    brands.add(brand);
                }
                return brands;
            }
            return brandRepository.findAllByActiveTrue();
        } else if (active.equals("false")) {
            if (!isBlank(name) && isBlank(category)) {
                Brand brand = check(name);
                if (!brand.getActive()) {
                    brands.add(brand);
                }
                return brands;
            } else if (!isBlank(category) && isBlank(name)) {
                return brandRepository.findAllByActiveAndCategoryName(false, category);
            } else if (!isAllBlank(name, category)) {
                Brand brand = check(name);
                if (brand.getCategory().getName().equalsIgnoreCase(category) && !brand.getActive()) {
                    brands.add(brand);
                }
                return brands;
            }
            return brandRepository.findAllByActiveFalse();
        } else if (isAllBlank(name, category, active)) {
            return brandRepository.findAll();
        }

        return brands;
    }

    public Brand update(UUID brandNumber, Brand brand, Category category){
        Brand existing = check(brandNumber);
        if(brand.getName() != null){
            existing.setName(brand.getName());
        }
        if(category.getCategoryNumber() != null){
            brand.setCategory(categoryService.check(category.getCategoryNumber()));
        }
        return brandRepository.save(existing);
    }

    public void delete(UUID brandNumber){

    }

    public Brand check(UUID number){

        Optional<Brand> result = brandRepository.findByBrandNumber(number);
        if(!result.isPresent()){
            throw new BrandNumberNotFound("Brand number not found");
        }
        return result.get();
    }
    public Brand check(String name){
        Optional<Brand> result = brandRepository.findByName(name);
        if(!result.isPresent()){
            throw new BrandNameNotFound("Brand name not found");
        }
        return result.get();
    }
    public String format(String string){
        return string.trim().toLowerCase();
    }


}
