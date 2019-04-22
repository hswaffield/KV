package com.kv.db;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class QueryRunner {

  public static byte[] RunKeyValueQuery(RocksDB db, KeyValueQuery query) throws RocksDBException {
    if (query.queryType == KeyValueQuery.QueryType.PUT) {
      // could do something like get args... idk need to make this clean...
      db.put(query.key, query.value);
    } else if (query.queryType == KeyValueQuery.QueryType.GET) {
      return db.get(query.key);
    } else if (query.queryType == KeyValueQuery.QueryType.DELETE) {
      db.delete(query.key);
    }
    // For the put / delete / invalid queries:
    return new byte[0];
  }

}
