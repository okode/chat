/**
 * Monkeybot
 *
 * Copyright (C) 2016 Okode. All rights reserved.
 */

package com.okode.monkeybot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String args[]) {
    	SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
    	try { Thread.currentThread().join(); } catch (InterruptedException e) { }
	}

}