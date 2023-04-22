package com.company.app.telegram.component.impl;

import com.company.app.telegram.component.api.Binder;
import com.company.app.telegram.component.api.BinderExecutor;
import com.company.app.telegram.component.data.BinderContainer;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.entity.Subscription;
import com.company.app.telegram.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class BinderExecutorImpl implements BinderExecutor {

	@Autowired
	SubscriptionRepository subscriptionRepository;
	@Autowired
	List<Binder> binderList;
	Map<String, Binder> binders;

	@PostConstruct
	void init() {
		binders = binderList.stream().collect(Collectors.toMap(Binder::getType, Function.identity()));
		if (binderList.size() != binders.keySet().size()) {
			throw new DuplicateKeyException("ты конечно красиво написал, но дубли не проверил!");
		}

		List<Subscription> subscriptionList = binders.keySet().stream()
				.map(binderType -> Subscription.builder().type(binderType).build())
				.collect(Collectors.toList());
		subscriptionRepository.saveAll(subscriptionList);
	}

	@Override
	public void execute(Chat chat, String text) {
		String binderType = Arrays.stream(text.split(" ")).findFirst().orElseThrow();

		Binder binder = Optional.ofNullable(binders.get(binderType))
				.orElseThrow(() -> new IllegalArgumentException(String.format("Не смог вытащить тип binderType из [%s].", text)));

		BinderContainer binderContainer = BinderContainer.builder()
				.chat(chat)
				.message(text)
				.build();

		binder.bind(binderContainer);
	}
}
