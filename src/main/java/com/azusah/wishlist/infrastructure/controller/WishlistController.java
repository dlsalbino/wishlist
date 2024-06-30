package com.azusah.wishlist.infrastructure.controller;

import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.infrastructure.controller.resources.request.AddProductRequest;
import com.azusah.wishlist.infrastructure.controller.resources.request.ProductRequest;
import com.azusah.wishlist.infrastructure.controller.resources.response.WishlistResponse;
import com.azusah.wishlist.infrastructure.controller.resources.response.ProductResponse;
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
@RequestMapping("api/v1/wishlist")
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

    @PostMapping
    public ResponseEntity<WishlistResponse> addProduct(@Valid @RequestBody AddProductRequest request) {
        String userId = request.getUserId();
        String productId = request.getProduct().id();
        log.info("START: Adding product '{}' to Wishlist of client '{}' process.", productId, userId);
        var wishlist = addProductUseCase.execute(userId, mapper.toProduct(request));
        var addProductResponse = mapper.toResponse(wishlist);
        log.info("END: Adding product '{}' to Wishlist of client '{}' process.", productId, userId);
        return new ResponseEntity<>(addProductResponse, HttpStatus.CREATED);
    }

    @GetMapping("/products/{userId}")
    public ResponseEntity<Set<ProductResponse>> listProducts(@PathVariable String userId) {

        log.info("START: Searching products list of client '{}' process.", userId);
        var productsResponse = mapper.toProductResponse(listAllProductsUseCase.execute(userId));
        log.info("END: Searching products list of client '{}' process.", userId);
        return new ResponseEntity<>(productsResponse, HttpStatus.OK);
    }

    @PutMapping("/products/{userId}")
    private ResponseEntity<WishlistResponse> removeProduct(@Valid @RequestBody ProductRequest request,
                                                           @PathVariable String userId) {
        log.info("START: Removing product '{}' to Wishlist of client '{}' process.", request.id(), userId);
        Wishlist wishlist = removeProductUseCase.execute(mapper.toProduct(request), userId);
        log.info("END: Removing product '{}' to Wishlist of client '{}' process.", request.id(), userId);
        return new ResponseEntity<>(mapper.toResponse(wishlist), HttpStatus.OK);
    }

}
