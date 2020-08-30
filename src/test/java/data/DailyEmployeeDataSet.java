package data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vimalselvam.graphql.GraphqlTemplate;
import okhttp3.*;
import java.io.*;
import java.util.*;


public class DailyEmployeeDataSet {
    private static final String filePath = "src/test/java/data/getDailyEmployees.graphql";
    private static final OkHttpClient client = new OkHttpClient();
    private static final String graphqlUri = "https://api.t4.pc.ekinoffy.com/graphql";
    private static final String authorizationName = "Authorization";
    private static final String authorizationValue = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJqb2UiLCJleHAiOjE2MDEyMTQyMDMsInVzZXJJbmZvIjoie1wiZW1haWxcIjpcImJpY2gtdGh1eS5uZ3V5ZW4tdGhpQGVraW5vLmNvbVwifSJ9.xA9L_nZAAocdHK4T-lpS4CvyXInFFoRz0GSDGO1Glkg";

    public static void main(String[] args) throws IOException {
       String a = testGraphqlWithVariable("2020-08-04", "", "",1);
       Map<?,?> b = convertToDataTest(a);
    }

    private static Response prepareResponse(String graphqlPayload) throws IOException {
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), graphqlPayload);
        Request request = new Request.Builder().url(graphqlUri).header(authorizationName, authorizationValue).post(body).build();
        return client.newCall(request).execute();
    }

    public static String getGraphqlWithNoVariables() throws IOException {
        // Read a graphql file
        File file = new File(filePath);

        // Now parse the graphql file to a request payload string
        String graphqlPayload = GraphqlTemplate.parseGraphql(file, null);

        // Build and trigger the request
        Response response = prepareResponse(graphqlPayload);
        System.out.println("Response status:" + response.toString());

        String jsonData = response.body().string();
        System.out.println(jsonData);
//        JsonNode jsonNode = new ObjectMapper().readTree(jsonData);

        return jsonData;
    }
    public static String testGraphqlWithVariable(String date,String name, String status,  Integer page) throws IOException {
        // Read a graphql file
        File file = new File(filePath);

        // Create a variables to pass to the graphql query
        ObjectNode variables = new ObjectMapper().createObjectNode();
        variables.put("date", date);
        variables.put("name", name);
        variables.put("status", status);
        variables.put("page", page);

        System.out.println("variables: " +variables);

        // Now parse the graphql file to a request payload string
        String graphqlPayload = GraphqlTemplate.parseGraphql(file, variables);

        // Build and trigger the request
        Response response = prepareResponse(graphqlPayload);

        String jsonData = response.body().string();

        return jsonData;

    }

    public static Map<?, ?> convertToDataTest(String jsonData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = (ArrayNode) mapper.readTree(jsonData).get("data").get("getDailyEmployees").get("data");
        Map<Object, Object> result = new LinkedHashMap<Object, Object>();
        if (arrayNode.isArray()) {

            for (JsonNode jsonNode : arrayNode) {

                String status = jsonNode.get("statusAM").toString() + jsonNode.get("statusPM").toString();
                String fullName = jsonNode.get("employee").get("fullName").toString();

                status = status.replace("\"", "");
                fullName = fullName.replace("\"", "");

                result.put(fullName, status);

            }
        }
        System.out.println("Convert: " + result);
        return result;
    }

}