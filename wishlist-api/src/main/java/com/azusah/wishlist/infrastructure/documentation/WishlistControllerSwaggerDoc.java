package com.azusah.wishlist.infrastructure.documentation;

import com.azusah.wishlist.infrastructure.controller.resources.request.ProductRequest;
import com.azusah.wishlist.infrastructure.controller.resources.response.ProductResponse;
import com.azusah.wishlist.infrastructure.controller.resources.response.WishlistResponse;
import com.azusah.wishlist.infrastructure.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Set;

@Tag(name = "Wishlist Operations", description = "Performs operations to a wishlist.")
public interface WishlistControllerSwaggerDoc {

    @Operation(summary = "Add product", description = "Creates an wishlist if not exists, and add a product.")
    @Parameters({
            @Parameter(name = "customerId", description = "The id of an existent customer.", required = true, example = "becfef1f-296c-4e6a-8609-200c9aa7f5cd")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WishlistResponse.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    ResponseEntity<WishlistResponse> addProduct(String customerId, ProductRequest productRequest);

    @Operation(summary = "List products", description = "Lists all products (maximum of 20) existent into a wishlist.")
    @Parameters({
            @Parameter(name = "customerId", description = "The id of an existent customer.", required = true, example = "becfef1f-296c-4e6a-8609-200c9aa7f5cd")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class)))}),
            @ApiResponse(responseCode = "404", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    ResponseEntity<Set<ProductResponse>> listProducts(String customerId);

    @Operation(summary = "Remove product", description = "Verifies if the given product exists on wishlist and remove it.")
    @Parameters({
            @Parameter(name = "customerId", description = "The id of an existent customer.", required = true, example = "becfef1f-296c-4e6a-8609-200c9aa7f5cd"),
            @Parameter(name = "productId", description = "The id of an existent product.", required = true, example = "6c3af85d-53ca-4cfe-8f91-ab6e1e727450")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WishlistResponse.class))}),
            @ApiResponse(responseCode = "404", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    ResponseEntity<WishlistResponse> removeProduct(String customerId, String productId);
}
