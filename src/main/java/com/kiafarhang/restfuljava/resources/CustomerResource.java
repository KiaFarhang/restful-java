package com.kiafarhang.restfuljava.resources;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import com.google.gson.JsonSyntaxException;

import com.kiafarhang.restfuljava.model.Customer;
import com.kiafarhang.restfuljava.util.JSONUtils;

@Path("/customers")
public class CustomerResource {
  private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
  private AtomicInteger idCounter = new AtomicInteger();

  @POST
  @Consumes("application/json")
  public Response createCustomer(InputStream input) {
    try {
      Customer customer = JSONUtils.parseCustomer(input);
      customer.setId(idCounter.incrementAndGet());
      customerDB.put(customer.getId(), customer);
      System.out.println("Created customer " + customer.getId());
      return Response.created(URI.create("/customers/" + customer.getId())).build();
    } catch (UnsupportedEncodingException e) {
      System.out.println(e);
      return Response.serverError().build();
    } catch( JsonSyntaxException e) {
      System.out.println(e);
      return Response.serverError().build();
    }
  }



}
