package com.azusah.controller;

import com.azusah.controller.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

@Tag(name = "Auth Operations", description = "Allows users authenticate on application.")
public interface AuthenticationControllerSwaggerDoc {

    @Operation(summary = "Authentication", description = "Authenticates an user.")
    @Parameters({@Parameter(name = "authentication", description = "Refers to credentials of an user (username and password).")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class))})
    })
    ResponseEntity<TokenResponse> login(Authentication authentication);
}
