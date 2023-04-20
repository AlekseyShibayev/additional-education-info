package com.company.app.telegram.repository;

import com.company.app.telegram.entity.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Long> {
}
