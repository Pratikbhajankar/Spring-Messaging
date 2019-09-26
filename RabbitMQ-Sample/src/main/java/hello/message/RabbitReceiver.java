package hello.message;

import hello.dto.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitReceiver {

  @RabbitListener(queues="${jsa.rabbitmq.queue}")
  public void receiveMessage(Email email) {
    System.out.println("Received <" + email + ">");
  }
}
