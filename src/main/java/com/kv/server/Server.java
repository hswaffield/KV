package com.kv.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.eclipse.jetty.servlet.ServletHandler;
import org.json.JSONObject;

public class Server {
  public static void main(String[] args) {
    try {
      org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(8080);
      ServletHandler handler = new ServletHandler();
      server.setHandler(handler);
      handler.addServletWithMapping(KvHandler.class, "/kv");
      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static class KvHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      JSONObject body = new JSONObject();
      body.put("status", "ok");
      resp.setContentType("application/json");
      resp.setStatus(HttpServletResponse.SC_OK);
      resp.getWriter().write(body.toString(2));
    }
  }
}
