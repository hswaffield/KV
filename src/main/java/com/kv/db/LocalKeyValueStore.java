package com.kv.db;

import com.kv.utils.CommandParsing;
import com.kv.utils.KeyValueQuery;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * following instructions here:
 * https://my.bradfieldcs.com/distributed-systems/2019-04/introduction/exercise/
 */
public class LocalKeyValueStore {

  //static Logger logger = Logger.getLogger(LocalKeyValueStore.class);

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //need to have some sort of path where the DB lives...
    //need to convert strings into db objects, for this simple little thingy

    String query;
    while ((query = br.readLine()) != null) {
      if (query.equals("killed")) {
        System.out.println("Thanks for playing. Bye-bye. •|龴◡龴|•");
        break;
      }

      System.out.println("heard: " + query);

      KeyValueQuery keyValueQuery = CommandParsing.stringToQuery(query);

      //TODO... make stuff happen..

    }
  }

}
