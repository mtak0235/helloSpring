package mtak.dev.helloSpring.web.converter;

import lombok.Getter;

@Getter
public enum MessageCode {
  SUCCESS(1, "success"),
  SRC_DELETED(2, "source deleted"),
  SRC_CREATED(3, "source created");

  private final int code;
  private final String codeDescription;

  MessageCode(int code, String codeDescription) {
    this.code = code;
    this.codeDescription = codeDescription;
  }
}
