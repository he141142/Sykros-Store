package com.example.sykrosstore.constants;

import com.example.sykrosstore.constants.EntityValidators.EntityValidator;
import org.springframework.stereotype.Component;

@Component
public class MessageList {
  public static EntityValidator entityValidator;

  public static EntityValidator getEntityValidator() {
    return entityValidator;
  }

  public static void setEntityValidator(
      EntityValidator entityValidator) {
    MessageList.entityValidator = entityValidator;
  }
}
