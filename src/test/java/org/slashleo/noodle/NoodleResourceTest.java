package org.slashleo.noodle;

import io.quarkus.test.junit.QuarkusTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class NoodleResourceTest {

    @Test
    public void testDeleteEndpoint() {
        final JSONObject jsonObject = new JSONObject()
                .put("id", -1)
                .put("name", "unitTest")
                .put("food", "testFood")
                .put("vegan", false);

        given()
          .given()
                .contentType("application/json").body(jsonObject.toString())
          .when()
                .delete("/delete")
          .then()
                .statusCode(204)
                .body(is(""));
    }

}