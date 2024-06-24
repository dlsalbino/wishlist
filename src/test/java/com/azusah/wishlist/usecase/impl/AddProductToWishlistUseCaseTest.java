package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.User;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.usecase.AddProductToWishlistUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddProductToWishlistUseCaseTest {

    private AddProductToWishlistUseCase addProductToWishlistUseCase;

    @BeforeEach
    public void init() {
        this.addProductToWishlistUseCase = new AddProductToWishlistUseCaseImpl();
    }

    @Test
    public void testAddingProductToWishlist() {

        //given
        var user = User.builder()
                .id("xyz")
                .logged(true)
                .build();

        var product = Product.builder()
                .id("xyz")
                .name("xyz")
                .image("http://image.address.com/12345")
                .value("12345.00")
                .link("http://e-commerce/products/12345")
                .build();

        //when
        Wishlist wishlist = addProductToWishlistUseCase.execute(user, product);

        //then
        assertThat(wishlist).isNotNull();

    }

}
