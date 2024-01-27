package in.datalayer.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;

public class ConsumerCallback extends DefaultConsumer {
	public ConsumerCallback(Channel channel) throws IOException {
		super(channel);
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, 
			byte[] body) throws IOException {
		System.out.println("Client received message: " + new String(body));
		long deliveryTag = envelope.getDeliveryTag();
		envelope.get
		super.getChannel().basicAck(deliveryTag, false);
	}
}