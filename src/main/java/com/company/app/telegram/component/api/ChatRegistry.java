package com.company.app.telegram.component.api;

import com.company.app.telegram.entity.Chat;

import java.util.List;

public interface ChatRegistry {

	List<Chat> getAll();
}
