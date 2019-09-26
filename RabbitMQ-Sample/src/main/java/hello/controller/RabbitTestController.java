package hello.controller;

import hello.dto.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/message")
public class RabbitTestController {

  private final RabbitTemplate rabbitTemplate;

  @Value("${jsa.rabbitmq.exchange}")
  private String exchange;

  @Value("${jsa.rabbitmq.routingkey}")
  private String routingKey;

  public RabbitTestController(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @GetMapping
  public ResponseEntity<?> sendMessage(){

    // Send a message with a POJO - the template reuse the message converter
    log.info("Sending an email message.");
    rabbitTemplate.convertAndSend(exchange,routingKey, new Email("info@example.com", "Hello"));
    return ResponseEntity.ok("send");
  }
}
