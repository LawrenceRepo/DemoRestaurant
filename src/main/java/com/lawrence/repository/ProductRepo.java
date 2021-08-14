package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.lawrence.model.Product;
import java.util.List;
import java.util.Set;

public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query( "select o from Product o where id in :ids" )
    Set<Product> findByProductIds(@Param("ids") List<Long> products);
}
