package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.core.domain.entity.Product;
import com.azusah.wishlist.core.domain.exception.EmptyProductListException;
import com.azusah.wishlist.infrastructure.service.PersistenceGatewayImpl;
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
    private PersistenceGatewayImpl persistenceGateway;

    @Test
    @DisplayName("Given an clientId should return product list when clientId was found.")
    public void testListAllProducts() {
        //given
        var clientId = "123456";
        var products = ProductMock.getProductListWith(5);
        when(persistenceGateway.findAllProductsByCustomer(anyString())).thenReturn(products);

        //when
        Set<Product> retrievedProducts = listAllProductsUseCase.execute(clientId);

        //then
        assertThat(retrievedProducts)
                .isNotNull()
                .hasSize(5);
    }

    @Test
    @DisplayName("Given an clientId should throw an exception when clientId was not found.")
    public void testClientIdWithoutProductsToList() {
        //given
        var clientId = "123456";
        var products = ProductMock.getProductListWith(0);
        when(persistenceGateway.findAllProductsByCustomer(anyString())).thenReturn(products);

        //when | then
        assertThrows(EmptyProductListException.class,
                () -> listAllProductsUseCase.execute(clientId),
                "There is no products for client '123456'.");
    }
}
