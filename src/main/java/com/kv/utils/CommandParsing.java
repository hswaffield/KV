package com.kv.utils;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;

public class CommandParsing {

  private static final Logger logger = LoggingHelper.getConfiguredLogger(CommandParsing.class);

  private static final String putPrefix = "put:";
  private static final String getPrefix = "get:";
  private static final String deletePrefix = "delete:";

  public static KeyValueQuery stringToQuery(String command) {
    final String lowerCaseCommand = command.toLowerCase();

    try {
      if (lowerCaseCommand.startsWith(putPrefix)) {
        String payload = command.replaceFirst(putPrefix, "");
        String[] keyValue = payload.split(":");

        return new KeyValueQuery(
            KeyValueQuery.QueryType.PUT,
            stringToBytes(keyValue[0]),
            stringToBytes(keyValue[1])
        );
      } else if (lowerCaseCommand.startsWith(getPrefix)) {
        String payload = command.replaceFirst(getPrefix, "");
        String key = payload.split(":")[0];

        return new KeyValueQuery(
            KeyValueQuery.QueryType.GET,
            stringToBytes(key),
            stringToBytes(key)
        );
      } else if (lowerCaseCommand.startsWith(deletePrefix)) {
        String payload = command.replaceFirst(deletePrefix, "");
        String key = payload.split(":")[0];

        return new KeyValueQuery(
            KeyValueQuery.QueryType.DELETE,
            stringToBytes(key),
            stringToBytes(key)
        );
      } else {
        System.out.println("invalid query");
        return new KeyValueQuery(
            KeyValueQuery.QueryType.INVALID,

            //TODO: clean this up to be more elegant and less wasteful.
            stringToBytes(""),
            stringToBytes("")
        );
      }
    } catch (Exception e) {
      logger.info("There was an error in parsing the command: " + command);
      return new KeyValueQuery(
          KeyValueQuery.QueryType.INVALID,
          stringToBytes(""),
          stringToBytes("")
      );
    }
  }

  private static byte[] stringToBytes(String string) {
    return string.getBytes(StandardCharsets.UTF_8);
  }

}
