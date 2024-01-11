package com.bhanuka.backend.service;

import com.bhanuka.backend.dto.ItemDto;
import com.bhanuka.backend.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    Item createItem(ItemDto itemDto);
    Item getItemById(Long id);
    List<Item> getAllItems();
    Item updateItem(Long id, Item item);
    List<Item>findByCategory(Long id);
}
