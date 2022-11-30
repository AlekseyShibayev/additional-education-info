package com.company.app.service.tools;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

@Getter
@Setter
public class JsonSearcher {

	JSONObject result;
	int counter;

	public void doRecursive(JSONObject jsonObject, String search) {
		Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			if (key.equals(search)) {
				this.setResult((JSONObject) jsonObject.get(key));
				this.setCounter(counter++);
			}

			Object o = jsonObject.get(key);
			if (o instanceof JSONObject) {
				JSONObject o1 = (JSONObject) o;
				doRecursive(o1, search);
			} else if (o instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) o;
				for (Object o1 : jsonArray) {
					JSONObject o11 = (JSONObject) o1;
					doRecursive(o11, search);
				}
			}
		}
	}
}
