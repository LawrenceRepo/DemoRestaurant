package com.lawrence.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.lawrence.model.Category;
import com.lawrence.model.Product;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepoTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CategoryRepo repository;
    @Test
    void findByCategoryIds() {
        Category category = new Category();
        Product product1 = new Product();
        Product product2 = new Product();
        Set<Product> productSet = new HashSet<>();
        productSet.add(product1);
        product1.setName("prod1");
        productSet.add(product2);
        category.setProducts(productSet);

        category = entityManager.persist(category);
         List<Long> categoryList = new ArrayList();
         categoryList.add(category.getId());
        Category category1 = null;
        Set<Category> categorySet = repository.findByCategoryIds(categoryList);
        for (Category categorytemp:categorySet
             ) {
            category1 =categorytemp;
        }
        assertEquals(category.getId(),category1.getId());

    }
}