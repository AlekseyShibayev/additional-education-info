package com.company.app.core.aop.logging.performance.component;

import com.company.app.core.aop.logging.performance.component.action.Action;
import lombok.Getter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class ActionRegistry {

	@Getter
	private final Map<ActionType, Action> actions = new EnumMap<>(ActionType.class);

	public void registerAction(Action action) {
		if (actions.containsKey(action.getActionType())) {
			throw new DuplicateKeyException(action.getActionType().toString());
		} else {
			actions.put(action.getActionType(), action);
		}
	}
}
