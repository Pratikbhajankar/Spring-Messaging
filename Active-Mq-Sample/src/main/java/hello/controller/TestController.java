package hello.controller;

import hello.dto.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/message")
public class TestController {

  private final JmsTemplate jmsTemplate;

  public TestController(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @GetMapping
  public ResponseEntity<?> sendMessage(){

    // Send a message with a POJO - the template reuse the message converter
    log.info("Sending an email message.");
    jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));
    return ResponseEntity.ok("send");
  }
}
