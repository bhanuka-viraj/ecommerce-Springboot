package com.bhanuka.backend.service.impl;

import com.bhanuka.backend.dto.ItemDto;
import com.bhanuka.backend.entity.Category;
import com.bhanuka.backend.entity.Item;
import com.bhanuka.backend.reository.CategoryRepository;
import com.bhanuka.backend.reository.ItemRepository;
import com.bhanuka.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public Item createItem(ItemDto itemDto) {
        Category category = categoryRepository.findById(itemDto.getCategoryId()).orElse(null);

        if (category != null) {
            Item item = new Item();
            item.setName(itemDto.getName());
            item.setPrice(itemDto.getPrice());
            item.setQty(itemDto.getQty());
            item.setCategory(category);

            return itemRepository.save(item);
        }else{
            return null;
        }
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item updateItem(Long id, Item item) {
        Item existingItem = itemRepository.findById(id).orElse(null);

        if (existingItem != null) {
            existingItem.setName(item.getName());
            existingItem.setPrice(item.getPrice());
            existingItem.setQty(item.getQty());
            existingItem.setCategory(item.getCategory());

            return itemRepository.save(existingItem);
        } else {
            return null;
        }
    }

    @Override
    public List<Item> findByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category != null) {
            return itemRepository.findByCategory(category);
        } else {
            return null;
        }
    }
}
