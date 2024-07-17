package com.azusah.wishlist.usecase.mock;

import com.azusah.wishlist.core.domain.entity.Product;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.HashSet;

public class ProductMock {

    public static HashSet<Product> getProductListWith(Integer products) {
        HashSet<Product> productsList = new HashSet<>();
        for (int i = 0; i < products; i++) {
            productsList.add(Product.builder()
                    .id(RandomStringUtils.randomAlphanumeric(10))
                    .name(RandomStringUtils.randomAlphanumeric(20))
                    .image(RandomStringUtils.randomAlphanumeric(30))
                    .value(new BigDecimal(RandomStringUtils.randomNumeric(3, 5)))
                    .link(RandomStringUtils.randomAlphanumeric(50))
                    .build());
        }
        return productsList;
    }
}
