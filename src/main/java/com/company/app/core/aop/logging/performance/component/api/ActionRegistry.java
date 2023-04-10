package com.company.app.core.aop.logging.performance.component.api;

import com.company.app.core.aop.logging.performance.component.ActionType;
import com.company.app.core.aop.logging.performance.component.action.Action;

public interface ActionRegistry {

	Action getAction(ActionType actionType);
}
