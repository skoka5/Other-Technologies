package com.dxc.message;

import java.util.Properties;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AsyncReceiver implements MessageListener , ExceptionListener{
	
	static QueueConnection queueConn = null;
	
	public static void main(String[] args) {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
		env.put("queue.queueSampleQueue", "MyNewQueue");
		
		try {
			InitialContext ctx = new InitialContext(env);
			
			Queue queue =(Queue) ctx.lookup("queueSampleQueue");
			QueueConnectionFactory connFactory =(QueueConnectionFactory)ctx.lookup("QueueConnectionFactory");
			queueConn =connFactory.createQueueConnection();
			QueueSession queueSession = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver queueReceiver = queueSession.createReceiver(queue);
			
			AsyncReceiver asyncReceiver = new AsyncReceiver();
			queueReceiver.setMessageListener(asyncReceiver);
			queueConn.start();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage msg = (TextMessage) message;
		try {
			if(msg.getText().equals("exit")) {
				queueConn.close();
				System.out.println("End of the application");
			}else {
			System.out.println(msg.getText());
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onException(JMSException ex) {
		// TODO Auto-generated method stub
		System.out.println("An exception occured: "+ex);
	}

}
