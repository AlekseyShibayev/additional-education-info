package com.company.app.telegram.repository;

import com.company.app.telegram.entity.Chat;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends CrudRepository<Chat, Long> {

	List<Chat> findAll();

	boolean existsChatByChatId(Long chatId);

	Optional<Chat> findFirstByChatId(Long chatId);

	@EntityGraph(value = "Chat.all")
	Optional<Chat> findById(Long id);
}
