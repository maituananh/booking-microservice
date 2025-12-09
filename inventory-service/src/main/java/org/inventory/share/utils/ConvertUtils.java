package org.inventory.share.utils;

import java.nio.charset.StandardCharsets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConvertUtils {

  public static String toString(Object object) {
    if (object instanceof byte[] header) {
      return new String(header, StandardCharsets.UTF_8);
    } else {
      return object.toString();
    }
  }
}
