package com.kv.utils;

public class KeyValueQuery {

  public final byte[] key;
  public final byte[] value;
  public final QueryType queryType;

  //TODO: should probably seperate out these types, but for now the intuition is that if it's
  // not type put, it will have the value ignored...

  public static enum QueryType {
    PUT,
    GET,
    DELETE,
    INVALID
  }

  public KeyValueQuery(QueryType queryType, byte[] key, byte[] value) {
    this.queryType = queryType;
    this.key = key;
    this.value = value;
  }
}
