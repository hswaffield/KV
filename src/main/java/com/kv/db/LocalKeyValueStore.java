package com.kv.db;

import com.kv.utils.CommandParsing;
import com.kv.utils.HappyEaster.HappyEaster;
import com.kv.utils.LoggingHelper;
import org.apache.log4j.Logger;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * following instructions here:
 * https://my.bradfieldcs.com/distributed-systems/2019-04/introduction/exercise/
 */
public class LocalKeyValueStore {

  static Logger logger = LoggingHelper.getConfiguredLogger(LocalKeyValueStore.class);

  public static void main(String[] args) throws Exception {
    System.out.println("Welcome to the interactive KV shell. Exit by entering 'exit' or 'kill'.\n");
    System.out.println("Run queries, by entering commands like these:");
    System.out.println("put:some_key:some_value");
    System.out.println("get:some_key");
    System.out.println("delete:some_key\n");

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //need to have some sort of path where the DB lives...
    // edit this path, as needed, this is just one example
    final String pathToDb = "/tmp/db";
    //need to convert strings into db objects, for this simple little thingy

    try (final Options options = new Options().setCreateIfMissing(true)) {
      try (final RocksDB db = RocksDB.open(options, pathToDb)) {

        String query;
        while ((query = br.readLine()) != null) {
          if (query.toLowerCase().equals("exit") || query.toLowerCase().equals("kill")) {
            System.out.println("Thanks for playing. Bye-bye. " + HappyEaster.getEgg());
            break;
          }

          //System.out.println("heard: " + query);

          KeyValueQuery keyValueQuery = CommandParsing.stringToQuery(query);

          byte[] response = QueryRunner.RunKeyValueQuery(db, keyValueQuery);

          if (keyValueQuery.queryType == KeyValueQuery.QueryType.GET) {
            //System.out.println(response);
            if (response != null) {
              System.out.println("-> " + new String(response, StandardCharsets.UTF_8));
            }
          } else {
            System.out.println("done");
          }
        }
      }
    } catch (RocksDBException e) {
      // do some error handling
      logger.error("there was an error");
      throw e;
    }


  }

}
