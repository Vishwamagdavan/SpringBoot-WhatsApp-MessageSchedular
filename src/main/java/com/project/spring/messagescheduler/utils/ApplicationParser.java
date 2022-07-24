package com.project.spring.messagescheduler.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * ApplicationParser.java - utils
 * This java class contains all the parsing methods that has been used in the application
 *
 * Example:
 * encodeParam - Encodes the string to UTF-8
 */
@Component
public class ApplicationParser {
    public String encodeParam(String data){
        String result="";
        try {
            result= URLEncoder.encode(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public String constructUrlParameters(Map<String,Object> params){

        StringBuilder data=new StringBuilder();
        for(Map.Entry<String,Object> param: params.entrySet()){
            /* appending next attribute with &
                example: channel=whatsapp & source=917472850482
             */
            if(data.length()!=0){
                data.append('&');
            }
            data.append(encodeParam(param.getKey()));
            data.append('=');
            /*
                channel=whatsapp
             */
            data.append(encodeParam(String.valueOf(param.getValue())));
        }
        return data.toString();
    }

    public JsonObject constructObjectIntoJsonObject(HashMap<String,String> object){
        Gson gson=new Gson();
        String msgObjString=gson.toJson(object);
        return JsonParser.parseString(msgObjString).getAsJsonObject();
    }

    public String bodyParser(HashMap<String,Object> objects){
        return constructUrlParameters(objects);
    }

    public HashMap<String,Object> convertStringToObject(String data){
        JsonObject jsonObject=JsonParser.parseString(data).getAsJsonObject();
        return new Gson().fromJson(jsonObject, HashMap.class);
    }

    /**
     * Removes extra space and other symbols
     * @param phoneNumber
     * @return
     */
    public String parsePhoneNumber(String phoneNumber){
        phoneNumber=phoneNumber.replaceAll("[\\s\\-\\+]","");
        return phoneNumber;
    }
}
