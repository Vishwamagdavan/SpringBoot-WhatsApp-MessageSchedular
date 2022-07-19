package com.project.spring.messagescheduler.controller;

import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/gupshup")
public class SendMessageController {
    Logger Logger= LoggerFactory.getLogger(SendMessageController.class);
    @PostMapping("/send")
    public void sendMessage() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "channel=whatsapp&source=917472850482&destination=918748133759&message=%7B%22type%22%3A%22text%22%2C%22text%22%3A%22Hello%20user%2C%20how%20are%20you%3F%22%7D&src.name=junp10&disablePreview=false");
        Request request = new Request.Builder()
                .url("https://api.gupshup.io/sm/api/v1/msg")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
        Logger.info(response.toString());
    }
}
