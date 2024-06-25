package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.User;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.infrastructure.service.RetrieveWishListGatewayImpl;
import com.azusah.wishlist.infrastructure.service.SaveWishListGatewayImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AddProductToWishlistUseCaseTest {

    @InjectMocks
    private AddProductToWishlistUseCaseImpl addProductToWishlistUseCase;

    @Mock
    private SaveWishListGatewayImpl saveWishListGateway;

    @Mock
    private RetrieveWishListGatewayImpl retrieveWishListGateway;

    @Test
    public void testAddingProductToANewWishlist() {

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

        Mockito.when(retrieveWishListGateway.findWishlistByUser(Mockito.anyString())).thenReturn(Optional.empty());

        HashSet<Product> products = new HashSet<>();
        products.add(product);
        Mockito.when(saveWishListGateway.save(Mockito.any(Wishlist.class)))
                .thenReturn(new Wishlist(user, products));

        //when
        Wishlist wishlist = addProductToWishlistUseCase.execute(user, product);

        //then
        assertThat(wishlist).isNotNull();
        assertThat(wishlist.getProducts())
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    public void testAddingProductToAExistingWishlist() {

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
        Mockito.when(retrieveWishListGateway.findWishlistByUser(Mockito.anyString()))
                .thenReturn(Optional.of(new Wishlist(user, getProductListWith(4))));

        //when
        Wishlist wishlist = addProductToWishlistUseCase.execute(user, product);

        //then
        assertThat(wishlist).isNotNull();
        assertThat(wishlist.getProducts())
                .isNotEmpty()
                .hasSize(5);
    }

    @Test
    public void testNoAddingProductToWishlistWhenUserIsNotLogged() {

        //given
        var user = User.builder()
                .id("xyz")
                .logged(false)
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
        assertThat(wishlist).isNull();
    }

    @Test
    public void testNoAddingProductToWishlistWhenWishListIsFull() {

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

        Mockito.when(retrieveWishListGateway.findWishlistByUser(Mockito.anyString()))
                .thenReturn(Optional.of(new Wishlist(user, getProductListWith(20))));


        //when | then
        assertThrows(RuntimeException.class,
                () -> addProductToWishlistUseCase.execute(user, product),
                "A lista atingiu o limite de 20 produtos.");

    }

    private HashSet<Product> getProductListWith(Integer products) {
        HashSet<Product> productsList = new HashSet<>();
        for (int i = 0; i < products; i++) {
            productsList.add(Product.builder()
                    .id(RandomStringUtils.randomAlphanumeric(10))
                    .name(RandomStringUtils.randomAlphanumeric(20))
                    .image(RandomStringUtils.randomAlphanumeric(30))
                    .value(RandomStringUtils.randomNumeric(1, 10000))
                    .link(RandomStringUtils.randomAlphanumeric(50))
                    .build());
        }
        return productsList;
    }

}
