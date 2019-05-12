package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.model.entity.Brand;
import se.sina.webshop.model.entity.Category;
import se.sina.webshop.model.entity.Model;
import se.sina.webshop.model.entity.ModelStatus;
import se.sina.webshop.repository.BrandRepository;
import se.sina.webshop.repository.ModelRepository;
import se.sina.webshop.service.exception.BrandExceptions.BrandNameNotFound;
import se.sina.webshop.service.exception.BrandExceptions.BrandNumberNotFound;
import se.sina.webshop.service.exception.ModelExceptions.ModelNameNotFound;
import se.sina.webshop.service.exception.ModelExceptions.ModelNumberNotFound;

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

    public ModelService(ModelRepository modelRepository, BrandRepository brandRepository, BrandService brandService) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.brandService = brandService;
    }

    public Model createModel(Model model){
        model.setModelNumber(UUID.randomUUID());
        model.setActive(true);
        model.setBrand(brandService.check(model.getBrand().getBrandNumber()));
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
        if(model.getName() != null){
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

    public void delete(UUID modelNumber){

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
    public String format(String string){
        return string.trim().toLowerCase();
    }
}
