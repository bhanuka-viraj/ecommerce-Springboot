package com.bhanuka.backend.controller;

import java.util.List;

import com.bhanuka.backend.dto.ItemDto;
import com.bhanuka.backend.entity.Item;
import com.bhanuka.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto){
        return ResponseEntity.status(201).body(itemService.createItem(itemDto)) ;
    }

    @GetMapping
    public ResponseEntity<List<Item>>getAllItems(){
        List<Item> items=itemService.getAllItems();

        if(items!=null){
            return ResponseEntity.status(200).body(items);
        }else{
            return ResponseEntity.status(404).body(null);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity <Item> getItemById(@PathVariable Long id){
        Item item=itemService.getItemById(id);

        if(item!=null){
            return ResponseEntity.status(200).body(item);
        }else{
            return ResponseEntity.status(404).body(null);
        }


    }

    @GetMapping("/category/{id}/items")
    public ResponseEntity<List<Item>> findByCategory(@PathVariable Long id){
        List<Item> items= itemService.findByCategory(id);
        if(items!=null){
            return ResponseEntity.status(200).body(items);
        }else{
            return ResponseEntity.status(404).body(null);
        }
    }

}
