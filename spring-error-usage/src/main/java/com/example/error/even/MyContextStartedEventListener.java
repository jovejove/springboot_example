package com.example.error.even;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyContextStartedEventListener implements ApplicationListener<ContextStartedEvent> {
  @Override
  public void onApplicationEvent(final ContextStartedEvent event) {
    // 当一个事件拦截不了时，我们第一个要查的是拦截的事件类型对不对，执行的代码能不能抛出它
    log.info("{} received: {}", this.toString(), event);
  }
}