package com.company.app.core.tool;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

@Getter
@Setter
public class JsonSearcher {

	private JSONObject result;
	private JSONArray resultArray;
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
				if (object instanceof JSONObject) {
					doRecursiveSearch((JSONObject) object, search);
				}
			}
		}
	}

	private void check(JSONObject jsonObject, String search, String key) {
		if (key.equals(search)) {
			Object object = jsonObject.get(key);
			if (object instanceof JSONObject) {
				this.setResult((JSONObject) jsonObject.get(key));
			} else if (object instanceof JSONArray) {
				this.setResultArray((JSONArray) jsonObject.get(key));
			}
			this.setCounter(counter++);
		}
	}
}
