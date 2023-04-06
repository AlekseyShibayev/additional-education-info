package com.company.app.core.aop.logging.performance.component.impl;

import com.company.app.core.aop.logging.performance.component.ActionType;
import com.company.app.core.aop.logging.performance.component.action.Action;
import com.company.app.core.aop.logging.performance.component.api.ActionRegistry;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class ActionRegistryImpl implements ActionRegistry {

	private final Map<ActionType, Action> actions = new EnumMap<>(ActionType.class);

	@Override
	public void registerAction(Action action) {
		if (actions.containsKey(action.getActionType())) {
			throw new DuplicateKeyException(action.getActionType().toString());
		} else {
			actions.put(action.getActionType(), action);
		}
	}

	@Override
	public Action getAction(ActionType actionType) {
		Action action = actions.get(actionType);
		if (action == null) {
			throw new UnsupportedOperationException(actionType.toString());
		} else {
			return action;
		}
	}
}
