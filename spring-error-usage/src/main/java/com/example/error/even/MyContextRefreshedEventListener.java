package com.example.error.even;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
    log.info("{} received: {}", this.toString(), event);
  }
}