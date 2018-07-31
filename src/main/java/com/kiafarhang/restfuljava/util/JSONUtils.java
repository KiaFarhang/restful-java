package com.kiafarhang.restfuljava.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.google.gson.*;

import com.kiafarhang.restfuljava.model.Customer;

public class JSONUtils {
  public static Customer parseCustomer(InputStream stream) throws JsonSyntaxException, UnsupportedEncodingException {
    InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
    Gson gson = new Gson();
    return gson.fromJson(reader, Customer.class);
  }
}
