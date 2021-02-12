package com.ipiecole.films;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Handler implements RequestHandler<Object, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(Object o, Context context) {
        try {
            //Appel ma méthode métier
            List<FilmsData> films = new Films().getFilmsRss();
            //Serialize en JSON l'objet de retour
            Genson genson = new GensonBuilder().useRuntimeType(true).create();
            String body = genson.serialize(films);
            //Gère les headers
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");
            int statusCode = 200;
            //Renvoie de l'objet à API Gateway
            return new GatewayResponse(body, headers, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
