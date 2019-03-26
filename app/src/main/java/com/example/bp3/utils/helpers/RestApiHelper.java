package com.example.bp3.utils.helpers;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * @author sven
 */

@Builder(builderMethodName = "restBuilder")
@AllArgsConstructor
public class RestApiHelper {
    private Class<?> klasse;
    private String urlModel;
    private List<String> parameters;
    private static RequestQueue requestQueue;

    public static void initRestApiHelper(Context context) {
        if (RestApiHelper.requestQueue == null) {
            RestApiHelper.requestQueue = Volley.newRequestQueue(context); //LOCAL EN GLOBAAL
        }
    }
    public static RestApiHelperBuilder prepareQuery(String urlModel) {
        return restBuilder().urlModel(urlModel);
    }

    public interface WebServiceCallBackArray {
        void onSuccess(JSONArray jsonArray);
    }

    public interface WebServiceCallBackObject {
        void onSuccess(JSONObject jsonObject);
    }

    public void returnJSONArray(final WebServiceCallBackArray callback) { //function to be run
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, buildURL(), new JSONArray(), callback::onSuccess, onError) {
            @Override
            public Map<String, String> getHeaders() {
                return jsonHeaders();
            }
        };
        RestApiHelper.requestQueue.add(request);
    }

    public void returnJSONObject(final WebServiceCallBackObject callback) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, buildURL(), new JSONObject(), callback::onSuccess, onError) {
            @Override
            public Map<String, String> getHeaders() {
                return jsonHeaders();
            }
        };
        RestApiHelper.requestQueue.add(request);
    }

    public Object toPojo(Object jsonArray) {
        return new Gson().fromJson(jsonArray.toString(), (Type) this.klasse);
    }

    public void postJSON(Object object) {
        String body = new Gson().toJson(object);
        StringRequest request = new StringRequest(Request.Method.POST, buildURL(), response -> Log.i("VOLLEY", response), onError) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() {
                return body == null ? null : body.getBytes(StandardCharsets.UTF_8);
            }
        };
        RestApiHelper.requestQueue.add(request);
    }

    private final Response.ErrorListener onError = error -> Log.e("RestError", error.toString());


    private String buildURL() {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("http://192.168.0.102:8080/bp3webservice/webresources/models.").append(this.urlModel);
        if (parameters != null) {
            parameters.forEach(value -> urlBuilder.append("/").append(value));
        }
        return urlBuilder.toString();
    }

    private Map<String, String> jsonHeaders() {
        Map<String, String> params = new HashMap<>();
        params.put("Accept", "application/json");
        return params;
    }
}


//
//    private int setMethod(String httpMethod) {
////        int method = 0;
////        switch (httpMethod.toUpperCase()) {
////            case "GET":
////                method =  Request.Method.GET;
////                break;
////            case "POST" :
////                method =  Request.Method.POST;
////                break;
////            case "DELETE" :
////                method =  Request.Method.DELETE;
////            break;
////            case "UPDATE" :
////            case "PUT" :
////                method =  Request.Method.PUT;
////            break;
////        }
////        return method;
//    }