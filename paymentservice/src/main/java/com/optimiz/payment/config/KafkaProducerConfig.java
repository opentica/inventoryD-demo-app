package com.optimiz.payment.config;

import java.util.HashMap;
import java.util.Map;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.optimiz.payment.model.PaymentOrder;

@Configuration
public class KafkaProducerConfig {
	@Value("${kafka.bootstrap.servers}")
	private String bootstrapServers;

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return props;
	}

	@Bean
	public ProducerFactory<String, PaymentOrder> producerFactory() {
		return new DefaultKafkaProducerFactory(producerConfigs());
	}

	@Bean
	public KafkaTemplate<String, PaymentOrder> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
	@Bean
	@ServiceActivator(inputChannel = "paymentOutputChannel")
	public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
		return new PubSubMessageHandler(pubsubTemplate, "payment-topic");
	}

	@MessagingGateway(defaultRequestChannel = "paymentOutputChannel")
	public interface PubsubOutboundGateway {
		void sendToPubsub(String text);
	}
	
	//GCP PUB sub specific code
	
	@Bean
	public PubSubInboundChannelAdapter messageChannelAdapter(
	       @Qualifier("paymentInputChannel") MessageChannel inputChannel,
	       PubSubTemplate pubSubTemplate) {
	    PubSubInboundChannelAdapter adapter =
	          new PubSubInboundChannelAdapter(pubSubTemplate, "payment-subscription");
	    adapter.setOutputChannel(inputChannel);
	    return adapter;
	}
	@Bean
	public MessageChannel paymentInputChannel() {
		return new DirectChannel();
	}

	@ServiceActivator(inputChannel = "paymentInputChannel")
	public void messageReceiver(String payload) {
	System.out.println("Message Payload is : " + payload);
	}
}
