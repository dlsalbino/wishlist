package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.domain.exception.WishlistLimitAchievedException;
import com.azusah.wishlist.infrastructure.service.RetrieveWishlistGatewayImpl;
import com.azusah.wishlist.infrastructure.service.SaveWishlistGatewayImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddProductToWishlistUseCaseTest {

    @InjectMocks
    private AddProductToWishlistUseCaseImpl addProductToWishlistUseCase;

    @Mock
    private SaveWishlistGatewayImpl saveWishListGateway;

    @Mock
    private RetrieveWishlistGatewayImpl retrieveWishListGateway;

    @Test
    public void testAddingProductToANewWishlist() {

        //given
        var userId = "xyz";

        var product = Product.builder()
                .id("xyz")
                .name("xyz")
                .image("http://image.address.com/12345")
                .value("12345.00")
                .link("http://e-commerce/products/12345")
                .build();

        when(retrieveWishListGateway.findWishlistByUser(anyString())).thenReturn(Optional.empty());

        var products = new HashSet<Product>();
        products.add(product);
        when(saveWishListGateway.save(anyString(), any(Product.class)))
                .thenReturn(Wishlist.builder().userId(userId).products(products).build());

        //when
        Wishlist wishlist = addProductToWishlistUseCase.execute(userId, product);

        //then
        assertThat(wishlist).isNotNull();
        assertThat(wishlist.getProducts())
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    public void testAddingProductToAExistingWishlist() {

        //given
        var userId = "xyz";

        var product = Product.builder()
                .id("xyz")
                .name("xyz")
                .image("http://image.address.com/12345")
                .value("12345.00")
                .link("http://e-commerce/products/12345")
                .build();
        when(retrieveWishListGateway.findWishlistByUser(anyString()))
                .thenReturn(Optional.of(new Wishlist(userId, getProductListWith(4))));
        when(saveWishListGateway.addProduct(anyString(), any(Product.class)))
                .thenReturn(new Wishlist(userId, getProductListWith(5)));

        //when
        Wishlist wishlist = addProductToWishlistUseCase.execute(userId, product);

        //then
        assertThat(wishlist).isNotNull();
        assertThat(wishlist.getProducts())
                .isNotEmpty()
                .hasSize(5);
    }

    @Test
    public void testNoAddingProductToWishlistWhenWishListIsFull() {

        //given
        var userId = "xyz";

        var product = Product.builder()
                .id("xyz")
                .name("xyz")
                .image("http://image.address.com/12345")
                .value("12345.00")
                .link("http://e-commerce/products/12345")
                .build();

        when(retrieveWishListGateway.findWishlistByUser(anyString()))
                .thenReturn(Optional.of(new Wishlist(userId, getProductListWith(20))));

        //when | then
        assertThrows(WishlistLimitAchievedException.class,
                () -> addProductToWishlistUseCase.execute(userId, product),
                "The Wishlist has achieved the limit of 20 products.");
    }

    private HashSet<Product> getProductListWith(Integer products) {
        HashSet<Product> productsList = new HashSet<>();
        for (int i = 0; i < products; i++) {
            productsList.add(Product.builder()
                    .id(RandomStringUtils.randomAlphanumeric(10))
                    .name(RandomStringUtils.randomAlphanumeric(20))
                    .image(RandomStringUtils.randomAlphanumeric(30))
                    .value(RandomStringUtils.randomNumeric(3, 5))
                    .link(RandomStringUtils.randomAlphanumeric(50))
                    .build());
        }
        return productsList;
    }
}
