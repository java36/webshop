package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.model.entity.*;
import se.sina.webshop.repository.ItemRepository;
import se.sina.webshop.repository.ModelRepository;
import se.sina.webshop.service.exception.ItemExceptions.ItemNumberNotFound;
import se.sina.webshop.service.exception.ModelExceptions.ModelNameNotFound;
import se.sina.webshop.service.exception.ModelExceptions.ModelNumberNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isAllBlank;
import static org.apache.commons.lang3.StringUtils.isBlank;

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

    public Item createItem(Item item){
        item.setItemNumber(UUID.randomUUID());
        item.setModel(modelService.check(item.getModel().getModelNumber()));
        item.setItemStatus(ItemStatus.STORED);
        return itemRepository.save(item);
    }

    public Item find(UUID itemNumber) {
        return check(itemNumber);
    }
    public List<Item> find(String model, String itemStatus){
        List<Item> items = new ArrayList<>();
        model = format(model);
        itemStatus = format(itemStatus).toUpperCase();

//        if (isBlank(itemStatus) || itemStatus.equals("stored")) {
//            if (!isBlank(model)) {
//                return itemRepository.findAllByModelAndItemStatus(model, ItemStatus.STORED);
//            } else {
//                return itemRepository.findAllByItemStatus(ItemStatus.STORED);
//            }
//        } else if (itemStatus.equals("terminated") || itemStatus.equals("sold")) {
//            if (!isBlank(model)) {
//                items.addAll(itemRepository.findAllByModelAndItemStatus(model,))
//                if (!category.getActive()) {
//                    categories.add(category);
//                }
//                return categories;
//            } else {
//                return categoryRepository.findAllByActiveFalse();
//            }
//        } else if (isAllBlank(name, active)) {
//            return categoryRepository.findAll();
//        }
//
//        return categories;

        if(!isBlank(model) && (!isBlank(itemStatus))){
            ItemStatus status = ItemStatus.valueOf(itemStatus);
            return itemRepository.findAllByModelAndItemStatus(model, status);
        }
        else if(!isBlank(model)){
            return itemRepository.findAllByModelAndItemStatus(model, ItemStatus.STORED);
        }
        else if(!isBlank(itemStatus)){
            ItemStatus status = ItemStatus.valueOf(itemStatus);
            return itemRepository.findAllByItemStatus(status);
        }
        return itemRepository.findAllByItemStatus(ItemStatus.STORED);
    }

    public Item update(UUID itemNumber, Item item, Model model){
        Item existing = check(itemNumber);
        if(item.getItemStatus() != null){
            existing.setItemStatus(item.getItemStatus());
        }
        if(model.getModelNumber() != null){
            item.setModel(modelService.check(model.getModelNumber()));
        }
        return itemRepository.save(existing);
    }

    public void delete(UUID modelNumber){

    }

    public Item check(UUID number){

        Optional<Item> result = itemRepository.findByItemNumber(number);
        if(!result.isPresent()){
            throw new ItemNumberNotFound("Item number not found");
        }
        return result.get();
    }
    public String format(String string){
        return string.trim().toLowerCase();
    }
}
