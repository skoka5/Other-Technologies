package com.dxc.publisher;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SimplePublisher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String topicName = "";
		if (args.length == 0) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter topic name");
			topicName = sc.next();
		}
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		for (int i = 0; i < 10; i++)
			producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i), Integer.toString(i)));

		producer.send(new ProducerRecord<String, String>(topicName, "Welcome"), new Callback() {

			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				System.out.println("Finished");
			}
		});
		System.out.println("Message sent successfully");
		producer.close();
	}

}
