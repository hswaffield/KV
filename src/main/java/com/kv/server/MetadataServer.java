package com.kv.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.json.JSONObject;

public class MetadataServer {
  private final HashMap<PartitionRange, Node> rangeToNode = new HashMap<>();

  void run() throws Exception {
    org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(7541);
    ServletHandler handler = new ServletHandler();
    server.setHandler(handler);
    handler.addServletWithMapping(new ServletHolder(new InitHandler()), "/init");
    handler.addServletWithMapping(new ServletHolder(new StatusHandler()), "/status");
    server.start();
    server.join();
  }

  public static void main(String[] args) {
    try {
      MetadataServer server = new MetadataServer();
      server.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // TODO what if this is called twice :(
  public class InitHandler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      JSONObject body = new JSONObject(IOUtils.toString(req.getReader()));
      String ip = body.getString("ip");
      int port = body.getInt("port");

      rangeToNode.put(
          new PartitionRange(PartitionBoundary.zero(), PartitionBoundary.infinity()),
          new Node(ip, port)
      );

      JSONObject responseBody = new JSONObject();
      responseBody.put("status", "ok");
      resp.setContentType("application/json");
      resp.setStatus(HttpServletResponse.SC_OK);
      resp.getWriter().write(responseBody.toString(2));
    }
  }

  public class StatusHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      JSONObject leaderRanges = new JSONObject();
      for (Map.Entry<PartitionRange, Node> entry : rangeToNode.entrySet()) {
        leaderRanges.put(entry.getKey().toString(), entry.getValue().toString());
      }
      JSONObject responseBody = new JSONObject();
      responseBody.put("leaders", leaderRanges);
      resp.setContentType("application/json");
      resp.setStatus(HttpServletResponse.SC_OK);
      resp.getWriter().write(responseBody.toString(2));
    }
  }

  static abstract class PartitionBoundary {
    static Zero zero() { return new Zero(); }

    static Infinity infinity() { return new Infinity(); }

    public PartitionBoundary() {
    }
  }

  static class Zero extends PartitionBoundary {
    @Override
    public String toString() {
      return "ZERO";
    }
  }

  static class ByteArray extends PartitionBoundary {
    byte[] bytes;

    public ByteArray(byte[] bytes) {
      this.bytes = bytes;
    }

    @Override
    public String toString() {
      // cheating because we know we're accepting ASCII >_>
      return new String(bytes);
    }
  }

  static class Infinity extends PartitionBoundary {
    @Override
    public String toString() {
      return "INFINITY";
    }
  }

  // TODO implement compareTo and validate that lower is always lt upper
  static class PartitionRange {
    PartitionBoundary lowerInc;
    PartitionBoundary upper;

    public PartitionRange(PartitionBoundary lowerInc, PartitionBoundary upper) {
      this.lowerInc = lowerInc;
      this.upper = upper;
    }

    @Override
    public String toString() {
      return lowerInc.toString() + " => " + upper.toString();
    }
  }

  static class Node {
    String ip;
    Integer port;

    Node(String ip, Integer port) {
      this.ip = ip;
      this.port = port;
    }

    @Override
    public String toString() {
      return String.format("%s:%d", ip, port);
    }
  }
}
