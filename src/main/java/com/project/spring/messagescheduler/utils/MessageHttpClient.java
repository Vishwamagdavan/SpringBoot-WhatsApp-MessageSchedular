package com.project.spring.messagescheduler.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.project.spring.messagescheduler.entity.Message;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageHttpClient {
    private final Logger logger= LoggerFactory.getLogger(MessageHttpClient.class);
    public MessageHttpClient() {
    }

    /*
        Fetching value from the application.properties
         */
    @Value("${gupshup.service.url}")
    private String END_POINT;
    @Value("${gupshup.service.apikey}")
    private String API_KEY;

    @Value("${gupshup.service.sourcenumber}")
    private String SOURCE_NUMBER;
    @Value("${gupshup.service.appname}")
    private String APP_NAME;

    @Autowired
    private ApplicationParser applicationParser;

    public ResponseBody httpClientPostRequest(Message message){
        try {
            Gson gson = new Gson();
            OkHttpClient client = new OkHttpClient();
            HashMap<String, Object> bodyObject = new HashMap<>();
            bodyObject.put("channel", "whatsapp");
            bodyObject.put("source", SOURCE_NUMBER);
            bodyObject.put("destination", message.getPhoneNumber());


            HashMap<String, String> messageBodyObject = new HashMap<>();
            messageBodyObject.put("type", "text");
            messageBodyObject.put("text", message.getMessageContent());
            String msgObjString = gson.toJson(messageBodyObject);
            JsonObject msgObject = JsonParser.parseString(msgObjString).getAsJsonObject();

            bodyObject.put("message", msgObject);
            bodyObject.put("src.name", APP_NAME);
            bodyObject.put("displayPreview", false);
            String bodyContent = applicationParser.bodyParser(bodyObject);

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, bodyContent);
            Request request = null;

            request = new Request.Builder()
                    .url(END_POINT)
                    .post(body)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("apikey", API_KEY)
                    .build();


            Response response = client.newCall(request).execute();
            return response.body();
        }catch (Exception e) {
            logger.warn("Unable to connect to Host:{}",e.getMessage());
        }
        return null;
    }
}
