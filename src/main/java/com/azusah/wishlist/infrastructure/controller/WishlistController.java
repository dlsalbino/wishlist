package com.azusah.wishlist.infrastructure.controller;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.infrastructure.controller.resources.request.AddProductRequest;
import com.azusah.wishlist.infrastructure.mapper.WishlistMapper;
import com.azusah.wishlist.usecase.AddProductToWishlistUseCase;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/wishlist")
public class WishlistController {

    private static final Logger log = LoggerFactory.getLogger(WishlistController.class);
    private final WishlistMapper mapper;
    private final AddProductToWishlistUseCase useCase;

    @Autowired
    public WishlistController(WishlistMapper mapper, AddProductToWishlistUseCase useCase) {
        this.mapper = mapper;
        this.useCase = useCase;
    }

    @PostMapping
    public Wishlist addProduct(@RequestBody @Valid AddProductRequest request) {
        log.info("Adicionar produto '{}' a Wishlist do cliente '{}'",
                request.getProduct().id(), request.getUserId());
        var mappedWishlist = mapper.toWishlist(request);
        var savedWishlist = useCase.execute(mappedWishlist.getUserId(), mappedWishlist.getProducts()
                .stream()
                .findFirst()
                .orElse(Product.builder().build())
        );
        log.info("Produto '{}' adicionado a Wishlist do cliente '{}'",
                request.getProduct().id(), savedWishlist.getUserId());
        return savedWishlist;
    }
}
