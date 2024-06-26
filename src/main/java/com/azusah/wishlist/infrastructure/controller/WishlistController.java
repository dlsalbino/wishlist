package com.azusah.wishlist.infrastructure.controller;

import com.azusah.wishlist.infrastructure.controller.resources.request.AddProductRequest;
import com.azusah.wishlist.infrastructure.controller.resources.response.AddProductResponse;
import com.azusah.wishlist.infrastructure.mapper.DomainEntityMapper;
import com.azusah.wishlist.usecase.AddProductToWishlistUseCase;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/wishlist")
public class WishlistController {

    private static final Logger log = LoggerFactory.getLogger(WishlistController.class);
    private final DomainEntityMapper mapper;
    private final AddProductToWishlistUseCase useCase;

    @Autowired
    public WishlistController(DomainEntityMapper mapper, AddProductToWishlistUseCase useCase) {
        this.mapper = mapper;
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<AddProductResponse> addProduct(@Valid @RequestBody AddProductRequest request) {
        String userId = request.getUserId();
        log.info("Adding product '{}' to Wishlist of client '{}'", request.getProduct().id(), userId);

        var wishlist = useCase.execute(userId, mapper.toProduct(request));
        var addProductResponse = mapper.toResponse(wishlist);

        log.info("Product '{}' added to Wishlist of client '{}'",
                request.getProduct().id(), wishlist.getUserId());
        return new ResponseEntity<>(addProductResponse, HttpStatus.CREATED);
    }

}
