package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecs {

    public static final ResponseSpecification OK_RESPONSE =
            new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .build();
}