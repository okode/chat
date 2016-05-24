/**
 * Monkeybot
 *
 * Copyright (C) 2016 Okode. All rights reserved.
 */

package com.okode.monkeybot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;

@Component
public class MonkeyProcessor {

	@Autowired
	private Configuration configuration;
	
	private MonkeyLearn monkeyLearn;
	
	@PostConstruct
	void init() {
		monkeyLearn = new MonkeyLearn(configuration.getMonkeylearnToken());
	}
	
	private String analyze(String moduleId, String type, String text) throws MonkeyLearnException {
		MonkeyLearnResponse res = monkeyLearn.classifiers.classify(moduleId, (String[]) Arrays.asList(text).toArray(), true);

		JSONArray results = (JSONArray) res.arrayResult.get(0);

		String response = type + ": ";
		int size = results.size();
		for (int index = 0; index < size; index++) {
			JSONObject element = (JSONObject) results.get(index);
			String label = (String) element.get("label");
			response += label + " ";
		}

		return response;
	}

	public List<String> analyze(String text) throws MonkeyLearnException, InterruptedException {
		List<String> responses = new ArrayList<>();
		responses.add(analyze("cl_u9PRHNzf", "Sensación", text));
		return responses;
	}
	
}
