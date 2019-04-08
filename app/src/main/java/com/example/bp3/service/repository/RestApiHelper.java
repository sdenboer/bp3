package com.example.bp3.service.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bp3.service.models.Opdracht;
import com.example.bp3.service.models.Tag;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * @author sven
 * Deze methode voert alle httpRequests uit en is gemaakt om het contact met de webservice
 * zo makkelijk mogelijk te maken.
 * De klasse wordt geinstantieerd door een builder method die de requirements voor params url etc
 * build. Daarna kunnen er verschillende opties geselecteerd worden.
 */

@Builder(builderMethodName = "restBuilder")
@AllArgsConstructor
public class RestApiHelper {
    private Class<?> klasse;
    private String urlModel;
    private List<Object> parameters;
    private static RequestQueue requestQueue;
    private final String ipAdress = "145.49.108.94";

    //APPLICATION ONLOAD
    /**
     * Deze methode initiates de requestQueue op het hoogste niveau. Hij moet aangeroepen worden
     * zodra de applicatie wordt gestart (bijv in de MainActivity).
     * @param context is de Activity waar de initiator zich bevind.
     */
    public static void initRestApiHelper(Context context) {
        if (RestApiHelper.requestQueue == null) {
            RestApiHelper.requestQueue = Volley.newRequestQueue(context); //LOCAL EN GLOBAAL
        }
    }
    /**
     * Deze methode zorgt ervoor dat er in ieder geval een url model wordt meegegeven in de request.
     * @param urlModel is het model in de webservice en wordt aangeroepen als
     *                 http://{IP}/webservice/webresources/models.{urlModel}
     * @return de builder en mandatory param
     */
    public static RestApiHelperBuilder prepareQuery(String urlModel) {
        return restBuilder().urlModel(urlModel);
    }

    //INTERFACES
    /**
     * Deze interface wordt aangeroepen als er een jsonArray wordt teruggegeven en kan override
     * worden om zo, async, een methode uit te voeren.
     */
    public interface WebServiceCallBackArray {
        void onSuccess(JSONArray jsonArray);
    }
    /**
     * Deze interface wordt aangeroepen als er een jsonObject wordt teruggegeven en kan override
     * worden om zo, async, een methode uit te voeren.
     */
    public interface WebServiceCallBackObject {
        void onSuccess(JSONObject jsonObject);
    }
    /**
     * Deze interface wordt aangeroepen als er een String wordt teruggegeven en kan override
     * worden om zo, async, een methode uit te voeren.
     */
    public interface WebServiceCallback {
        void onSuccess(String response);
    }

    /**
     * Deze interface wordt aangeroepen als er een error heeft plaatsgevonden
     */
    public interface WebServiceCallbackFail {
        void onError(VolleyError volleyError);
    }

    //HTTPREQUESTS
    /**
     * Deze methode bouwt de async functie om een JsonArray terug te geven.
     * @param callback is de interface onSucess
     * @param error is de interface voor onError
     * @see WebServiceCallBackArray
     */
    public void getArray(final WebServiceCallBackArray callback, final WebServiceCallbackFail error) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, buildURL(), new JSONArray(), callback::onSuccess, error::onError) {
            @Override
            public Map<String, String> getHeaders() {
                return jsonHeaders();
            }
        };
        RestApiHelper.requestQueue.add(request);
    }
    /**
     * Deze methode bouwt de async functie om een jsonObject terug te geven.
     * @param callback is de interface onSucess
     * @param error is de interface voor onError
     * @see WebServiceCallBackObject
     */
    public void getObject(final WebServiceCallBackObject callback, final WebServiceCallbackFail error) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, buildURL(), new JSONObject(), callback::onSuccess, error::onError) {
            @Override
            public Map<String, String> getHeaders() {
                return jsonHeaders();
            }
        };
        RestApiHelper.requestQueue.add(request);
    }
    /**
     * Deze methode maakt een POST request naar de webservice
     * @param object is het object dat aan de webservice wordt meegegeven(zoals een Student)
     * @param callback is wat er gebeurd nadat de functie is uitgevoerd.
     * @param error is de interface voor onError
     */
    public void post(Object object, final WebServiceCallback callback, final WebServiceCallbackFail error) {
        String body = new Gson().toJson(object);
        Log.d("Post", body);
        StringRequest request = new StringRequest(Request.Method.POST, buildURL(), callback::onSuccess, error::onError) {
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
    /**
     * Deze methode maakt een PUT request naar de webservice
     * @param object object is het object dat aan de webservice wordt meegegeven(zoals een Student)
     * @param callback is wat er gebeurd nadat de functie is uitgevoerd.
     * @param error is de interface voor onError
     */
    public void update(Object object, final WebServiceCallback callback, final WebServiceCallbackFail error){
        String body = new Gson().toJson(object);
        Log.d("EF", body);
        StringRequest request = new StringRequest(Request.Method.PUT, buildURL(), callback::onSuccess, error::onError) {
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
    /**
     * Deze methode maakt een DELETE request naar de webservice
     * @param callback is wat er gebeurd nadat de functie is uitgevoerd
     * @param error is de interface voor onError
     */
    public void delete(final  WebServiceCallback callback, final WebServiceCallbackFail error) {
        StringRequest request = new StringRequest(Request.Method.DELETE, buildURL(), callback::onSuccess, error::onError);
        RestApiHelper.requestQueue.add(request);
    }

    //JSON NAAR PLAIN OLD JAVA OBJECT (POJO)
    /**
     * Deze methode gebruikt Google GSON om een jsonArray om te zetten in een object.
     * @param jsonObject is een JSONObject of een JSONArray
     * @return een array of object van de klasse die verwacht wordt
     */
    public Object toPOJO(Object jsonObject) {
        return new Gson().fromJson(jsonObject.toString(), (Type) this.klasse);
    }

    //HELPER FUNCTIES
    /**
     * Deze methode build de URL en plaatst eventuele parameters voor langere en custom queries
     * @return een String met de volledige URL die nodig is voor de REQUEST
     */
    private String buildURL() {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("http://").append(ipAdress).append(":8080/bp3webservice/webresources/models.").append(this.urlModel);
        if (parameters != null) {
            parameters.forEach(value -> urlBuilder.append("/").append(value));
        }
        Log.d("URL", urlBuilder.toString());
        return urlBuilder.toString().replaceAll(" ", "%20");
    }
    /**
     * Deze functie maakt het makkelijker om de http Request content type naar JSON te zetten
     * @return de HEADERS Accept - application/json
     */
    private Map<String, String> jsonHeaders() {
        Map<String, String> params = new HashMap<>();
        params.put("Accept", "application/json");
        return params;
    }
    /**
     * Maakt het makkelijker om simpel te debuggen zonder herhalende code
     * verander fail::onerror door onerror en verwijder de CallbackFail
     */
    private final Response.ErrorListener onError = error -> Log.e("Webservice Error", error.toString());



    ///////////////////HIERONDER VOLGT EEN GUIDE VOOR HET GEBRUIK VAN DE HELPER/////////////////////



    protected class helperGuide {

        protected helperGuide() {
            //GET request voor een LIST van meerdere objecten (bijvoorbeeld een findAll)
            //Kijk in de webservice naar de modelFACADERest om te zien wat er verwacht wordt.
            RestApiHelper tagJSON = RestApiHelper
                    .prepareQuery("tag")
                    .klasse(Tag[].class)
                    .build();
            tagJSON.getArray(ja -> {
                List<Tag> tags = Arrays.asList((Tag[]) tagJSON.toPOJO(ja));
                //      In deze array zitten alle tag objecten.
                //      voeg hieronder code toe om iets met de objecten te doen. Bijvoorbeeld:
                //
                //      tags.forEach(tag -> Log.d("Array with tags", tag.getTag()));
            }, error -> Log.e("Kutzooi", error.toString())); //Deze functie wordt uitgevoerd als er iets fout is.

            //GET request voor een enkel object (1 resultaat)
            RestApiHelper opdrachtJSON = RestApiHelper
                    .prepareQuery("opdrachtvraag")
                    .klasse(Opdracht.class)
                    .parameters(Arrays.asList("primary key"))
                    .build();
            opdrachtJSON.getObject(jo -> {
                Opdracht opdracht = (Opdracht) opdrachtJSON.toPOJO(jo);
                //      In dit object zit een Student object.
                //      voeg hieronder code toe om iets met het object te doen. Bijvoorbeeld:
                //
                //      Log.d("Opdracht", opdracht.getDocent().getNaam());
            }, error -> Log.e("Webservice Error", error.toString()));

            Tag tag = new Tag("test"); //<- Dit is zinloos natuurlijk, maar maakt de uitleg van de PUT request makkelijker
            tag.setTag("de nieuwe tag");
            //PUT request voor een object
            RestApiHelper.prepareQuery("tag")
                    .parameters(Arrays.asList("mbo")) //<- In dit geval is "mbo" de primary key van het object dat gewijzigd moet worden
                    .build()
                    .update(tag, callback -> { //<-In dit geval is 'tag' het geupdate object.
                        //De code hieronder wordt uitgevoerd nadat de request is uitgevoerd. Bijvoorbeeld
                        //
                        //      Log.d("UPDATE", "Het object is geupdate!");
                    }, error -> Log.e("Webservice Error", error.toString()));

            //DELETE request voor een object
            RestApiHelper.prepareQuery("tag")
                    .parameters(Arrays.asList("primary key"))
                    .build()
                    .delete(callback -> {
                        //De code hieronder wordt uitgevoerd nadat de request is uitgevoerd. Bijvoorbeeld
                        //
                        //      Log.d("DELETE", "Het object is verwijderd!");

                    }, error -> Log.e("Webservice Error", error.toString()));

//        POST request voor een object
            Tag newTag = new Tag("java");
            RestApiHelper.prepareQuery("tag")
                    .build()
                    .post(newTag, response -> {
                        //De code hieronder wordt uitgevoerd nadat de request is uitgevoerd. Bijvoorbeeld
                        //
                        //      Log.d("POST", "Het object zit in de database!");
                    }, error -> Log.e("Webservice Error", error.toString()));
        }

    }

    //ADD AND REMOVE A TEAM MEMBER
//    RestApiHelper team = RestApiHelper
//            .prepareQuery("team")
//            .klasse(Team.class)
//            .parameters(Arrays.asList("1beroepsproduct3 wit"))
//            .build();
//        team.getObject(ja -> {
//        Team t = (Team)team.toPOJO(ja);
//        RestApiHelper tagJSON = RestApiHelper
//                .prepareQuery("student")
//                .klasse(Student.class)
//                .parameters(Arrays.asList("sven@avans.nl"))
//                .build();
//        tagJSON.getObject(jo -> {
//            Student currentSession = (Student)tagJSON.toPOJO(jo);
//            t.addTeamMember(currentSession);
//            RestApiHelper.prepareQuery("team").klasse(Team.class).parameters(Arrays.asList("1beroepsproduct3%20wit")).build().update(t, response -> {
//                Log.d("HELLO", "IM POSTED");
//            });
//        });
//    });
}




