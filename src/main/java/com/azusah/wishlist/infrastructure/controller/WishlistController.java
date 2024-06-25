package com.azusah.wishlist.infrastructure.controller;

import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.infrastructure.controller.resources.request.AddProductRequest;
import com.azusah.wishlist.infrastructure.mapper.WishlistMapper;
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

    @Autowired
    public WishlistController(WishlistMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping
    public Wishlist create(@RequestBody AddProductRequest request) {

        Wishlist wishlist = mapper.toWishlist(request);
        log.info("Wishlist created: {}", wishlist);
        return wishlist;
    }
}
