package com.bhanuka.backend.service.impl;

import com.bhanuka.backend.dto.OrderDto;
import com.bhanuka.backend.entity.Item;
import com.bhanuka.backend.entity.Order;
import com.bhanuka.backend.reository.ItemRepository;
import com.bhanuka.backend.reository.OrderRepository;
import com.bhanuka.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Order createOrder(OrderDto orderDto) {
        List<Long> itemIds = orderDto.getItems();

        Set <Item> items= new HashSet<>();

        Double total=0.0;

        for(Long itemId:itemIds){ //looping through product ids
            Item item=itemRepository.findById(itemId).orElse(null);

            if(item!=null && item.getQty()!=0){
                //add this to item set
                items.add(item);

                //find the total snd set
                total= total+item.getPrice();

                item.setQty(item.getQty()-1);
                itemRepository.save(item);
            }
        }

        Double tax=(total/100)*15;
        LocalDateTime dateTime=LocalDateTime.now();

        Order order = new Order();
        order.setDateTime(dateTime);
        order.setItems(items);
        order.setTotal(total);
        order.setTax(tax);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
