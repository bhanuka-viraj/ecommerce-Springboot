package com.bhanuka.backend.reository;

import com.bhanuka.backend.entity.Category;
import com.bhanuka.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    @Query("SELECT i FROM Item i WHERE i.category =:category ")
    List<Item> findByCategory(@Param("category") Category category);
}
