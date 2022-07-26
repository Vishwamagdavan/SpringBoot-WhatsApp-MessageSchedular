package com.project.spring.messagescheduler.utils;

import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class UserOptUtil {
    @Value("${gupshup.service.url}")
    private String END_POINT;
    @Value("${gupshup.service.apikey}")
    private String API_KEY;

    @Value("${gupshup.service.sourcenumber}")
    private String SOURCE_NUMBER;
    @Value("${gupshup.service.appname}")
    private String APP_NAME;

    /**
     * Accepts the input as phone number and makes the user OPT
     * @param phoneNumber parsed phone number ( after removing + and extra space)
     * @return if the phoneNumber is successfully made opted
     */
    public boolean makeUserOpt(String phoneNumber){

        try {
            OkHttpClient client = new OkHttpClient();
            String bodyContent="user="+phoneNumber;
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, bodyContent);
            Request request = new Request.Builder()
                    .url("https://api.gupshup.io/sm/api/v1/app/opt/in/"+APP_NAME)
                    .post(body)
                    .addHeader("apikey", API_KEY)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            Response response = client.newCall(request).execute();

            if(response.code()==202 && response.body().toString().equalsIgnoreCase("Accepted")){
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
