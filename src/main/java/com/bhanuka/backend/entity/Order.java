package com.bhanuka.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double total;

    private Double tax;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToMany
    @JoinTable(
            name = "order_item",
            joinColumns=@JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")
    )
    private Set<Item> items=new HashSet<>();

}
