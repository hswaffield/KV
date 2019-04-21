import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DatabaseSpinUpTestExample {

  public static void main(String[] args) throws Exception {

    // edit this path, as needed, this is just one example
    final String pathToDb = "/Users/hswaff/code/KV/db";

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

        System.out.println(Arrays.toString(db.get(hi_key)));
        System.out.println(new String(db.get(hi_key), StandardCharsets.UTF_8));

        System.out.println(Arrays.toString(db.get(hi_key_2)));
        System.out.println(new String(db.get(hi_key_2), StandardCharsets.UTF_8));

      }
    } catch (RocksDBException e) {
      // do some error handling
      throw e;
    }
  }
}
