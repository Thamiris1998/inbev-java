package com.application.repository;

import com.application.domain.Product;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ProductRepositoryTest {

    private final static Logger log = Logger.getLogger(ProductRepositoryTest.class);

    @Autowired
    private ProductRepository repository;

    private Product product = new Product();

    @Before
    public void init() {
        product.setId("1");
        product.setName("Guaraná");
        product.setBrand("Antarctica");
        product.setDescription("2,5L");
        product.setPrice(4.00);
        repository.save(product);
    }

    // Create new Product
    @Test
    public void whenCreateNewProduct() {
        Product prod = new Product();
        prod.setId("2");
        prod.setName("Coca zero");
        prod.setBrand("Coca-cola");
        prod.setDescription("3,5L");
        prod.setPrice(6.00);

        repository.save(prod);
        List<Product> list = new ArrayList<>();
        Iterable<Product> iterable = repository.findAll();
        iterable.forEach(list::add);
        log.info(repository.findById("456def") + " : " + prod);

        assertThat(2).isEqualTo(list.size());
    }

    @After
    public void destroy() {
        repository.deleteAll();
    }
}

