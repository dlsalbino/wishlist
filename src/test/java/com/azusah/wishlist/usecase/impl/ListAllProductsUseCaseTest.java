package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.infrastructure.service.RetrieveProductsGatewayImpl;
import com.azusah.wishlist.usecase.mock.ProductMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListAllProductsUseCaseTest {

    @InjectMocks
    private ListAllProductsUseCaseImpl listAllProductsUseCase;

    @Mock
    private RetrieveProductsGatewayImpl retrievedProducts;

    @Test
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
}
