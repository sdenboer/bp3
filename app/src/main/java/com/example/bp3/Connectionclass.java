package com.example.bp3;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;

public class Connectionclass {
    static RequestQueue req;

    ArrayList<String> deVelden = new ArrayList<>();

    public void getConnection(Context context,String model, final String models, final int index) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://145.49.85.104:8080/bp3webservice/webresources/models." + model;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> buildItems(response, models , index), error -> System.out.println(error.getMessage()));
        System.out.println(stringRequest);
        queue.add(stringRequest);
    }

    public void buildItems(String response, String models, int index){
        //ArrayList<String> deVelden = new ArrayList<>();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response)));
            NodeList velden = doc.getElementsByTagName(models);
            NodeList alVelden = (velden.item(0)).getChildNodes();
            System.out.println(alVelden);

            for(int i = 0; i < alVelden.getLength(); i++){
                Node n = alVelden.item(i);
                Node name = n.getChildNodes().item(index);
                String strName = name.getTextContent();
                deVelden.add(strName);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> select(Context context,String model, final String models, final int index){
        getConnection(context,model,models,index);
        return deVelden;
    }
}
