package com.azusah.wishlist.infrastructure.controller;

import com.azusah.wishlist.core.domain.entity.Wishlist;
import com.azusah.wishlist.infrastructure.controller.resources.request.ProductRequest;
import com.azusah.wishlist.infrastructure.controller.resources.response.ProductResponse;
import com.azusah.wishlist.infrastructure.controller.resources.response.WishlistResponse;
import com.azusah.wishlist.infrastructure.documentation.SwaggerDocumentation;
import com.azusah.wishlist.infrastructure.mapper.DomainEntityMapper;
import com.azusah.wishlist.usecase.AddProductToWishlistUseCase;
import com.azusah.wishlist.usecase.ListAllProductsUseCase;
import com.azusah.wishlist.usecase.RemoveProductFromWishlistUseCase;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1")
public class WishlistController implements SwaggerDocumentation {

    private static final Logger log = LoggerFactory.getLogger(WishlistController.class);
    private final DomainEntityMapper mapper;
    private final AddProductToWishlistUseCase addProductUseCase;
    private final ListAllProductsUseCase listAllProductsUseCase;
    private final RemoveProductFromWishlistUseCase removeProductUseCase;

    @Autowired
    public WishlistController(DomainEntityMapper mapper, AddProductToWishlistUseCase addProductUseCase,
                              ListAllProductsUseCase listAllProductsUseCase, RemoveProductFromWishlistUseCase removeProductUseCase) {
        this.mapper = mapper;
        this.addProductUseCase = addProductUseCase;
        this.listAllProductsUseCase = listAllProductsUseCase;
        this.removeProductUseCase = removeProductUseCase;
    }

    @PostMapping("/{customerId}/products")
    public ResponseEntity<WishlistResponse> addProduct(@PathVariable String customerId,
                                                       @Valid @RequestBody ProductRequest productRequest) {
        String productId = productRequest.id();
        log.info("START: Adding product '{}' to Wishlist from customer '{}' process.", productId, customerId);
        var wishlist = addProductUseCase.execute(customerId, mapper.toProduct(productRequest));
        var addProductResponse = mapper.toResponse(wishlist);
        log.info("END: Adding product '{}' to Wishlist from customer '{}' process.", productId, customerId);
        return new ResponseEntity<>(addProductResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/products")
    public ResponseEntity<Set<ProductResponse>> listProducts(@PathVariable String customerId) {

        log.info("START: Searching products list of customer '{}' process.", customerId);
        var productsResponse = mapper.toProductResponse(listAllProductsUseCase.execute(customerId));
        log.info("END: Searching products list of customer '{}' process.", customerId);
        return new ResponseEntity<>(productsResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}/products/{productId}")
    public ResponseEntity<WishlistResponse> removeProduct(@PathVariable String customerId,
                                                           @PathVariable String productId) {
        log.info("START: Removing product '{}' to Wishlist of customer '{}' process.", productId, customerId);
        Wishlist wishlist = removeProductUseCase.execute(customerId, productId);
        log.info("END: Removing product '{}' to Wishlist of customer '{}' process.", productId, customerId);
        return new ResponseEntity<>(mapper.toResponse(wishlist), HttpStatus.NO_CONTENT);
    }
}
