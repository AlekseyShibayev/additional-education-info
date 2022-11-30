package com.company.app.service.tools;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

@Getter
@Setter
public class JsonSearcher {

	private JSONObject result;
	private int counter;

	public void doRecursiveSearch(JSONObject jsonObject, String search) {
		Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			check(jsonObject, search, key);
			execute(jsonObject.get(key), search);
		}
	}

	private void execute(Object jsonObject, String search) {
		if (jsonObject instanceof JSONObject) {
			doRecursiveSearch((JSONObject) jsonObject, search);
		} else if (jsonObject instanceof JSONArray) {
			JSONArray jsonArray = (JSONArray) jsonObject;
			for (Object object : jsonArray) {
				doRecursiveSearch((JSONObject) object, search);
			}
		}
	}

	private void check(JSONObject jsonObject, String search, String key) {
		if (key.equals(search)) {
			this.setResult((JSONObject) jsonObject.get(key));
			this.setCounter(counter++);
		}
	}
}
