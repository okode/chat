/**
 * Monkeybot
 *
 * Copyright (C) 2016 Okode. All rights reserved.
 */

package com.okode.monkeybot;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.TcpConnectionConfiguration;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stanza.model.Message;

@Component
public class MonkeyBot {

	private static final Logger log = LoggerFactory.getLogger(MonkeyBot.class);

	@Autowired
	private Configuration configuration;

	@Autowired
	private MonkeyProcessor monkeyProcessor;

	private XmppClient xmppClient;

	@EventListener(ContextRefreshedEvent.class)
	private void start() throws XmppException, InterruptedException {

		log.info("Monkeybot init");

		TcpConnectionConfiguration tcpConfiguration = TcpConnectionConfiguration.builder()
				.hostname(configuration.getXmppHost()).port(configuration.getXmppPort()).build();

		xmppClient = new XmppClient(configuration.getXmppHost(), tcpConfiguration);
		log.info("Monkeybot connecting to {}", configuration.getXmppHost());

		xmppClient.connect();

		log.info("Monkeybot connected");

		xmppClient.addInboundMessageListener(e -> {

			Message message = e.getMessage();
			String body = message.getBody();
			if (StringUtils.isEmpty(body)) return;

			log.info("Message received: {}", body);

			try {
				List<String> responses = monkeyProcessor.analyze(body);
				for (String response : responses) {

					log.info("Sending response: {}", response);

					xmppClient.send(new Message(message.getFrom(), Message.Type.CHAT, response));
				}
			} catch (Exception ex) {
				log.error("Could not analyze text: {}", body);
			}

		});

		log.info("Monkeybot login user: {}", configuration.getXmppUser());

		xmppClient.login(configuration.getXmppUser(), configuration.getXmppPassword(), configuration.getXmppResource());

		log.info("Monkeybot logged ok. Waiting for messages...");
	}

	@EventListener(ContextClosedEvent.class)
	private void stop() throws XmppException {
		if (xmppClient != null) xmppClient.close();
	}
}
