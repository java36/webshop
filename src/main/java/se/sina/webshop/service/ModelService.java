package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.model.entity.*;
import se.sina.webshop.repository.BrandRepository;
import se.sina.webshop.repository.ItemRepository;
import se.sina.webshop.repository.ModelRepository;
import se.sina.webshop.service.exception.BrandExceptions.BrandNameNotFound;
import se.sina.webshop.service.exception.BrandExceptions.BrandNumberNotFound;
import se.sina.webshop.service.exception.ModelExceptions.ModelNameAlreadyExists;
import se.sina.webshop.service.exception.ModelExceptions.ModelNameNotFound;
import se.sina.webshop.service.exception.ModelExceptions.ModelNumberNotFound;
import se.sina.webshop.service.exception.ModelExceptions.ModelUndeletable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isAllBlank;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public final class ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final BrandService brandService;
    private final ItemRepository itemRepository;

    public ModelService(ModelRepository modelRepository, BrandRepository brandRepository, BrandService brandService, ItemRepository itemRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.brandService = brandService;
        this.itemRepository = itemRepository;
    }

    public Model createModel(Model model){
        Brand brand = brandService.check(model.getBrand().getBrandNumber());
        checkDoubleNames(model.getName(), brand.getName());
        model.setModelNumber(UUID.randomUUID());
        model.setActive(true);
        model.setBrand(brand);
        model.setModelStatus(ModelStatus.INSTORE);
        return modelRepository.save(model);
    }
    public Model find(UUID modelNumber) {
        return check(modelNumber);
    }

    public List<Model> find(String name, String brand, String active) {
        List<Model> models = new ArrayList<>();
        name = format(name);
        brand = format(brand);
        active = format(active);

        if (isBlank(active) || active.equals("true")) {
            if (!isBlank(name) && isBlank(brand)) {
                Model model = check(name);
                if (model.getActive()) {
                    models.add(model);
                }
                return models;
            } else if (!isBlank(brand) && isBlank(name)) {
                return modelRepository.findAllByActiveAndBrandName(true, brand);
            } else if (!isAllBlank(name, brand)) {
                Model model = check(name);
                if (model.getBrand().getName().equalsIgnoreCase(brand) && model.getActive()) {
                    models.add(model);
                }
                return models;
            }
            return modelRepository.findAllByActiveTrue();
        } else if (active.equals("false")) {
            if (!isBlank(name) && isBlank(brand)) {
                Model model = check(name);
                if (!model.getActive()) {
                    models.add(model);
                }
                return models;
            } else if (!isBlank(brand) && isBlank(name)) {
                return modelRepository.findAllByActiveAndBrandName(false, brand);
            } else if (!isAllBlank(name, brand)) {
                Model model = check(name);
                if (model.getBrand().getName().equalsIgnoreCase(brand) && !model.getActive()) {
                    models.add(model);
                }
                return models;
            }
            return modelRepository.findAllByActiveFalse();
        } else if (isAllBlank(name, brand, active)) {
            return modelRepository.findAll();
        }

        return models;
    }

    public Model update(UUID modelNumber, Model model, Brand brand){
        Model existing = check(modelNumber);
        Brand resultingBrand = brandService.check(existing.getBrand().getBrandNumber());
        if(model.getName() != null){
            checkDoubleNames(model.getName(), brand.getName());
            existing.setName(model.getName());
        }
        if(model.getPrice() != null){
            existing.setPrice(model.getPrice());
        }
        if(model.getModelStatus() != null){
            existing.setModelStatus(model.getModelStatus());
        }
        if(brand.getBrandNumber() != null){
            model.setBrand(brandService.check(brand.getBrandNumber()));
        }
        return modelRepository.save(existing);
    }

    public Model delete(UUID modelNumber){
        Model model = check(modelNumber);
        List<Item> items = itemRepository.findAllByModelNameAndItemStatus(model.getName(), ItemStatus.STORED);
        if(items.size() == 0){
            model.setActive(false);
            return modelRepository.save(model);
        }
        throw new ModelUndeletable("Unable to delete model");
    }

    public Model check(UUID number){

        Optional<Model> result = modelRepository.findByModelNumber(number);
        if(!result.isPresent()){
            throw new ModelNumberNotFound("Model number not found");
        }
        return result.get();
    }
    public Model check(String name){
        Optional<Model> result = modelRepository.findByName(name);
        if(!result.isPresent()){
            throw new ModelNameNotFound("Model name not found");
        }
        return result.get();
    }

    public void checkDoubleNames(String modelName, String brandName){
        Optional<Model> result = modelRepository.findByNameAndBrandName(modelName, brandName);
        if(result.isPresent()){
            throw new ModelNameAlreadyExists("Model name already exists");
        }
    }
    public String format(String string){
        return string.trim().toLowerCase();
    }
}
