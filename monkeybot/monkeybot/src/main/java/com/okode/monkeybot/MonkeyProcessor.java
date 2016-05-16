/**
 * Monkeybot
 *
 * Copyright (C) 2016 Okode. All rights reserved.
 */

package com.okode.monkeybot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;

@Component
public class MonkeyProcessor {

	private MonkeyLearn monkeyLearn = new MonkeyLearn("f04e2e0c9dd81993d6ec3f3b2b143ce1ec474dea");
	
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
		
		// News
		responses.add(analyze("cl_6hvxGfLu", "Noticias", text));
		
		Thread.sleep(3000);
		
		// Sentiment
		responses.add(analyze("cl_u9PRHNzf", "Sensación", text));
		
		Thread.sleep(3000);
		
		// Products
		responses.add(analyze("cl_eaa5vnQ6", "Productos", text));
		
		return responses;
	}
	
}
