/**
 * Monkeybot
 *
 * Copyright (C) 2016 Okode. All rights reserved.
 */

package com.okode.monkeybot;

import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;

@Component
public class MonkeyProcessor {

	private MonkeyLearn monkeyLearn = new MonkeyLearn("f04e2e0c9dd81993d6ec3f3b2b143ce1ec474dea");
		
	public String analyze(String text) throws MonkeyLearnException {
		// https://app.monkeylearn.com/categorizer/projects/cl_6hvxGfLu/tab/live-tab
		String moduleId = "cl_6hvxGfLu";
		
		MonkeyLearnResponse res = monkeyLearn.classifiers.classify(moduleId, (String[]) Arrays.asList(text).toArray(), true);
		
		JSONArray results = (JSONArray) res.arrayResult.get(0);
		
		String response = "Noticias: ";
		int size = results.size();
		for (int index = 0; index < size; index++) {
			JSONObject element = (JSONObject) results.get(index);
			String label = (String) element.get("label");
			response += label + " ";
		}

		return response;
	}
	
}
