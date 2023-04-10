package com.company.app.core.aop.logging.performance.component.impl;

import com.company.app.core.aop.logging.performance.component.ActionType;
import com.company.app.core.aop.logging.performance.component.action.Action;
import com.company.app.core.aop.logging.performance.component.api.ActionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class ActionRegistryImpl implements ActionRegistry {

	@Autowired
	List<Action> actionList;
	private Map<ActionType, Action> actions;

	@PostConstruct
	public void init() {
		registerActions();
	}

	private void registerActions() {
		actions = new EnumMap<>(ActionType.class);
		for (Action action : actionList) {
			if (actions.containsKey(action.getType())) {
				throw new DuplicateKeyException(action.getType().toString());
			} else {
				actions.put(action.getType(), action);
			}
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
