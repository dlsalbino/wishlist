package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.core.domain.entity.Product;
import com.azusah.wishlist.core.domain.entity.Wishlist;
import com.azusah.wishlist.core.domain.exception.WishlistLimitAchievedException;
import com.azusah.wishlist.infrastructure.service.PersistenceGatewayImpl;
import com.azusah.wishlist.usecase.mock.ProductMock;
import org.junit.jupiter.api.DisplayName;
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
    private PersistenceGatewayImpl persistenceGateway;

    @Test
    @DisplayName("Given an clientId and product should return a new Wishlist with one product.")
    public void testAddingProductToANewWishlist() {

        //given
        var clientId = "xyz";

        var product = Product.builder()
                .id("xyz")
                .name("xyz")
                .image("http://image.address.com/12345")
                .value("12345.00")
                .link("http://e-commerce/products/12345")
                .build();

        when(persistenceGateway.findWishlistByClient(anyString())).thenReturn(Optional.empty());

        var products = new HashSet<Product>();
        products.add(product);
        when(persistenceGateway.save(anyString(), any(Product.class)))
                .thenReturn(Wishlist.builder().clientId(clientId).products(products).build());

        //when
        Wishlist wishlist = addProductToWishlistUseCase.execute(clientId, product);

        //then
        assertThat(wishlist).isNotNull();
        assertThat(wishlist.getProducts())
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    @DisplayName("Given an clientId and product should return an existent wishlist containing the new product")
    public void testAddingProductToAExistingWishlist() {

        //given
        var clientId = "xyz";

        var product = Product.builder()
                .id("xyz")
                .name("xyz")
                .image("http://image.address.com/12345")
                .value("12345.00")
                .link("http://e-commerce/products/12345")
                .build();
        when(persistenceGateway.findWishlistByClient(anyString()))
                .thenReturn(Optional.of(new Wishlist(clientId, ProductMock.getProductListWith(4))));
        when(persistenceGateway.addProduct(anyString(), any(Product.class)))
                .thenReturn(new Wishlist(clientId, ProductMock.getProductListWith(5)));

        //when
        Wishlist wishlist = addProductToWishlistUseCase.execute(clientId, product);

        //then
        assertThat(wishlist).isNotNull();
        assertThat(wishlist.getProducts())
                .isNotEmpty()
                .hasSize(5);
    }

    @Test
    @DisplayName("Given an clientId and product should throw an exception" +
            "when the list size was greater than or equal to 20")
    public void testNoAddingProductToWishlistWhenWishListIsFull() {

        //given
        var clientId = "xyz";

        var product = Product.builder()
                .id("xyz")
                .name("xyz")
                .image("http://image.address.com/12345")
                .value("12345.00")
                .link("http://e-commerce/products/12345")
                .build();

        when(persistenceGateway.findWishlistByClient(anyString()))
                .thenReturn(Optional.of(new Wishlist(clientId, ProductMock.getProductListWith(20))));

        //when | then
        assertThrows(WishlistLimitAchievedException.class,
                () -> addProductToWishlistUseCase.execute(clientId, product),
                "The Wishlist has achieved the limit of 20 products.");
    }
}
