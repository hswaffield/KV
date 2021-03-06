package com.kv.db;

import com.kv.utils.LoggingHelper;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.nio.charset.StandardCharsets;

public class DatabaseSpinUpTestExample {

  private static Logger logger = LoggingHelper.getConfiguredLogger(DatabaseSpinUpTestExample.class);

  public static void main(String[] args) throws Exception {
    //TODO: seperate logging configuration into a seperate place, probably:
    PropertyConfigurator.configure("log4j.properties");

    // edit this path, as needed, this is just one example
    final String pathToDb = "/tmp/db";

    // a static method that loads the RocksDB C++ library.
    RocksDB.loadLibrary();

    // the Options class contains a set of configurable DB options
    // that determines the behaviour of the database.
    try (final Options options = new Options().setCreateIfMissing(true)) {

      // a factory method that returns a RocksDB instance
      try (final RocksDB db = RocksDB.open(options, pathToDb)) {

        byte[] hi_key = "hi_key".getBytes();
        byte[] hi_value = "hi_value".getBytes();

        byte[] hi_key_2 = "hi_key_2".getBytes(StandardCharsets.UTF_8);
        byte[] hi_value_2 = "hi_value_2".getBytes(StandardCharsets.UTF_8);

        // do something
        db.put(hi_key, hi_value);
        db.put(hi_key_2, hi_value_2);

        logger.info("printing get for hi_key and hi_key_2");
        System.out.println(new String(db.get(hi_key), StandardCharsets.UTF_8));

        System.out
            .println(new String(db.get(hi_key_2), StandardCharsets.UTF_8));
        logger.info(
            "check it out: db.get(hi_key)" + new String(db.get(hi_key), StandardCharsets.UTF_8));

        logger.info("about to delete hi_key, from db, then get results for both keys");
        db.delete(hi_key);
        System.out.println(db.get(hi_key));
        System.out.println(new String(db.get(hi_key_2), StandardCharsets.UTF_8));
      }
    } catch (RocksDBException e) {
      // do some error handling
      logger.error("there was an error");
      throw e;
    }
  }
}
