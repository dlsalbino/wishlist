package com.azusah.wishlist.infrastructure.controller;

import com.azusah.wishlist.core.domain.entity.Wishlist;
import com.azusah.wishlist.infrastructure.controller.resources.request.ProductRequest;
import com.azusah.wishlist.infrastructure.controller.resources.response.ProductResponse;
import com.azusah.wishlist.infrastructure.controller.resources.response.WishlistResponse;
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
public class WishlistController {

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

    @PostMapping("/{clientId}/products")
    public ResponseEntity<WishlistResponse> addProduct(@PathVariable String clientId,
                                                       @Valid @RequestBody ProductRequest request) {
        String productId = request.id();
        log.info("START: Adding product '{}' to Wishlist of client '{}' process.", productId, clientId);
        var wishlist = addProductUseCase.execute(clientId, mapper.toProduct(request));
        var addProductResponse = mapper.toResponse(wishlist);
        log.info("END: Adding product '{}' to Wishlist of client '{}' process.", productId, clientId);
        return new ResponseEntity<>(addProductResponse, HttpStatus.OK);
    }

    @GetMapping("/{clientId}/products")
    public ResponseEntity<Set<ProductResponse>> listProducts(@PathVariable String clientId) {

        log.info("START: Searching products list of client '{}' process.", clientId);
        var productsResponse = mapper.toProductResponse(listAllProductsUseCase.execute(clientId));
        log.info("END: Searching products list of client '{}' process.", clientId);
        return new ResponseEntity<>(productsResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{clientId}/products")
    private ResponseEntity<WishlistResponse> removeProduct(@PathVariable String clientId,
                                                           @Valid @RequestBody ProductRequest request) {
        log.info("START: Removing product '{}' to Wishlist of client '{}' process.", request.id(), clientId);
        Wishlist wishlist = removeProductUseCase.execute(mapper.toProduct(request), clientId);
        log.info("END: Removing product '{}' to Wishlist of client '{}' process.", request.id(), clientId);
        return new ResponseEntity<>(mapper.toResponse(wishlist), HttpStatus.OK);
    }
}
