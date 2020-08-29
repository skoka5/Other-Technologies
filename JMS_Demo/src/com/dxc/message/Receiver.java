package com.dxc.message;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Receiver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
			env.put("queue.queueSampleQueue", "MyNewQueue");
				
			InitialContext ctxt = new InitialContext(env);
				
				Queue queue = (Queue) ctxt.lookup("queueSampleQueue");
				
				QueueConnectionFactory connFactory = (QueueConnectionFactory) ctxt.lookup("QueueConnectionFactory");
				QueueConnection queueConn = connFactory.createQueueConnection();
				QueueSession queueSession =queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
				QueueReceiver queueReceiver = queueSession.createReceiver(queue);
				
				queueConn.start();
				
				TextMessage textMessage = (TextMessage)queueReceiver.receive();
				System.out.println("Received : "+textMessage.getText());
				queueConn.close();
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	

}
