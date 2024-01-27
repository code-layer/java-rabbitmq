package in.datalayer.rabbitmq;

import com.rabbitmq.client.*;

public class Consumer {
	public static final String rmqServerUrl = "amqp://vkumar:Admin.1234@3.7.104.192:5673/vkumar-vhost";

	public static void main(String[] args) throws Exception {
		receiveMessages();
	}

	private static void receiveMessages()  throws Exception {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setUri(rmqServerUrl);
		Connection con = cf.newConnection();

		Channel channel = con.createChannel();
		channel.queueBind("debit-cards-q", "banking-ex", "own-card");

		String consumerTag;
		consumerTag = channel.basicConsume("debit-cards-q", false, "", false, false, null,
				new ConsumerCallback(channel));

		/*
		DeliverCallback deliverCallback = (consumerTag,delivery) -> {
            String message = new String(delivery.getBody(),"UTF-8");
				try {
					doSomeWork(message);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally{
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(),true);
			}
		};

		channel.basicConsume("debit-cards-q",false,deliverCallback, consumerTag -> {});
		*/

	}

	private static void doSomeWork(String message) throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("doSomeWork completed: " + message);
	}
}