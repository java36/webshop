package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.repository.ItemRepository;
import se.sina.webshop.repository.ModelRepository;

@Service
public final class ItemService {

    private final ItemRepository itemRepository;
    private final ModelRepository modelRepository;
    private final ModelService modelService;

    public ItemService(ItemRepository itemRepository, ModelRepository modelRepository, ModelService modelService) {
        this.itemRepository = itemRepository;
        this.modelRepository = modelRepository;
        this.modelService = modelService;
    }
}
