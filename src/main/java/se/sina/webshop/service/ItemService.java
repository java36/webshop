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
        checkModelStatus(item);
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

        if(!isBlank(model) && (!isBlank(itemStatus))){
            ItemStatus status = ItemStatus.valueOf(itemStatus);
            List<Model> models = modelService.find(model, "", "");
            if(models.size() > 0){
                return itemRepository.findAllByModelAndItemStatus(models.get(0), status);
            }
            throw new ModelNameNotFound("Model name not found");
        }
        else if(!isBlank(model)){
            List<Model> models = modelService.find(model, "", "");
            if(models.size() > 0){
                return itemRepository.findAllByModelAndItemStatus(models.get(0), ItemStatus.STORED);
            }
            throw new ModelNameNotFound("Model name not found");

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

    public Item delete(UUID modelNumber){
        Item result = check(modelNumber);
        result.setItemStatus(ItemStatus.DELETED);
        Item item = itemRepository.save(result);
        checkModelStatus(item);
        return item;
    }

    public void checkModelStatus(Item item){
        Model model = item.getModel();
        List<Item> items = itemRepository.findAllByModelAndItemStatus(model, ItemStatus.STORED);
        if(items.size() == 0){
            model.setModelStatus(ModelStatus.SOLDOUT);
            modelService.update(model.getModelNumber(), model, model.getBrand());
        }
        else{
            model.setModelStatus(ModelStatus.INSTORE);
            modelService.update(model.getModelNumber(), model, model.getBrand());
        }
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
