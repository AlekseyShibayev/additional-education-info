package com.company.app.telegram.repository;

import com.company.app.telegram.entity.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends CrudRepository<Chat, Long> {

	List<Chat> findAll();

	boolean existsChatByChatId(Long chatId);

	Optional<Chat> findFirstByChatId(Long chatId);
}
