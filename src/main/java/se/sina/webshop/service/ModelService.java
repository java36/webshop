package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.repository.BrandRepository;
import se.sina.webshop.repository.ModelRepository;

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
}
