package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.exception.EmptyProductListException;
import com.azusah.wishlist.infrastructure.service.RetrieveProductsGatewayImpl;
import com.azusah.wishlist.usecase.mock.ProductMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListAllProductsUseCaseTest {

    @InjectMocks
    private ListAllProductsUseCaseImpl listAllProductsUseCase;

    @Mock
    private RetrieveProductsGatewayImpl retrievedProducts;

    @Test
    @DisplayName("Given an userId should return product list when userId was found.")
    public void testListAllProducts() {
        //given
        var userId = "123456";
        var products = ProductMock.getProductListWith(5);
        when(retrievedProducts.findAllByUser(anyString())).thenReturn(products);

        //when
        Set<Product> retrievedProducts = listAllProductsUseCase.execute(userId);

        //then
        assertThat(retrievedProducts)
                .isNotNull()
                .hasSize(5);
    }

    @Test
    @DisplayName("Given an userId should throw an exception when userId was not found.")
    public void testUserIdWithoutProductsToList() {
        //given
        var userId = "123456";
        var products = ProductMock.getProductListWith(0);
        when(retrievedProducts.findAllByUser(anyString())).thenReturn(products);

        //when | then
        assertThrows(EmptyProductListException.class,
                () -> listAllProductsUseCase.execute(userId),
                "There is no products for client '123456'.");
    }
}
