package com.kv.db;

import com.kv.utils.CommandParsing;
import com.kv.utils.HappyEaster.HappyEaster;
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
    System.out.println("Welcome to the interactive shell. End with 'exit' or 'kill'");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //need to have some sort of path where the DB lives...
    //need to convert strings into db objects, for this simple little thingy

    String query;
    while ((query = br.readLine()) != null) {
      if (query.toLowerCase().equals("exit") || query.toLowerCase().equals("kill")) {
        System.out.println("Thanks for playing. Bye-bye. " + HappyEaster.getEgg());
        break;
      }

      System.out.println("heard: " + query);

      KeyValueQuery keyValueQuery = CommandParsing.stringToQuery(query);

      //TODO... make stuff happen..

    }
  }

}
