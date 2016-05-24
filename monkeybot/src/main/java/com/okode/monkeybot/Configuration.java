/**
 * Monkeybot
 *
 * Copyright (C) 2016 Okode. All rights reserved.
 */

package com.okode.monkeybot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

	@Value("${xmpp.host}")
	private String xmppHost;
	
	@Value("${xmpp.port}")
	private int xmppPort;
	
	@Value("${xmpp.user}")
	private String xmppUser;
	
	@Value("${xmpp.password}")
	private String xmppPassword;
	
	@Value("${xmpp.resource}")
	private String xmppResource;
	
	public String getXmppHost() {
		return xmppHost;
	}
	
	public String getXmppUser() {
		return xmppUser;
	}
	
	public String getXmppPassword() {
		return xmppPassword;
	}

	public String getXmppResource() {
		return xmppResource;
	}
	
	public int getXmppPort() {
		return xmppPort;
	}
	
}
