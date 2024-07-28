package com.azusah.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class TokenResponse {

    @Schema(example = "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InVzZXJuYW1lIiwiZXhwIjoxNzIyMDgwMTY0" +
            "LCJpYXQiOjE3MjIwNzk5ODQsInNjb3BlIjoiUkVBRCJ9.K0iiQTFP8crLrenl-KSau2tnDRkmv4FmKkLuY4na48agrCkLPACw21" +
            "MbeajP4_nbFuSy2aeoiyti6uWf_Q8_BOnX1hCNYCVo__xjjrLvgw1Oedu6FZE0o2lwHIFVo_YXRoiA7i3k92T9ISF6IxET9Smt" +
            "tBVxzKpOFQUEFgQMShuR0dazrCuLpYL4P8FuWWqBxEMmwEQdM8HSK7N-GZy2DQ4YwRN8j9LJr2xenoYpXfPD21KOizREImENWqys" +
            "PDBBLoPocRIn1YjZ_Qd9UVfOphUuVXWgCWFkzZUoFeKbDnq9aUvta-Pxr63V4R1djOWQfJKh7_RfdA_GxGlw-lrVjw",
            description = "A token that allows do operations on Wishlist App.")
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
