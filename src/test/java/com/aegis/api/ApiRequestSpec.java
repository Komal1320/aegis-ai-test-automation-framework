package com.aegis.api;

import com.aegis.config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/** Shared Rest Assured setup; API endpoints require a configured nopCommerce API plugin. */
public final class ApiRequestSpec {
    private ApiRequestSpec() {}
    public static RequestSpecification base() {
        return new RequestSpecBuilder()
            .setBaseUri(ConfigReader.get("api.base.url"))
            .setContentType("application/json")
            .build();
    }
}
