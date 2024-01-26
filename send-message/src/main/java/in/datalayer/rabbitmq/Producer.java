package in.datalayer.rabbitmq;

import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.AMQImpl;

import java.util.Date;

public class Producer {
	public static final String rmqServerUrl = "amqp://vkumar:Admin.1234@3.7.104.192:5673/vkumar-vhost";

	public static void main(String[] args) throws Exception {
		sendMessage();
	}

	private static void sendMessage() throws Exception {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setUri(rmqServerUrl);
		Connection con = cf.newConnection();

		Channel channel = con.createChannel();

		/*
		AMQP.Confirm.SelectOk selOk = channel.confirmSelect();
		basic.ack
		deliveryTag
	   */

		String message = "You are credited with $100 by Datalayer. " + new Date().toString();

		AMQP.BasicProperties bp = new AMQP.BasicProperties().builder()
				.headers()
				.build();

		//MessageProperties.PERSISTENT_TEXT_PLAIN;
		//exchage: banking-ex
		//routing-key: own-card
		channel.basicPublish("banking-ex","own-card",bp,message.getBytes());
		con.close();
	}
}