package com.company.app.telegram.component.data;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * todo: а пока так
 */
public class ButtonAndCommandRegistry {

	public static final List<BotCommand> LIST_OF_COMMANDS = List.of(
			new BotCommand("/tg", "TG"),
			new BotCommand("/wb", "WB"),
			new BotCommand("/ex", "EX")
	);

	private static final InlineKeyboardButton TG_BUTTON = new InlineKeyboardButton("Отключить уведомления");
	private static final InlineKeyboardButton WB_BUTTON = new InlineKeyboardButton("Добавить лоты wildberries (в разработке)");
	private static final InlineKeyboardButton ER_BUTTON = new InlineKeyboardButton("Текущий курс aliexpress");

	public static InlineKeyboardMarkup inlineMarkup() {
		TG_BUTTON.setCallbackData("TG");
		WB_BUTTON.setCallbackData("WB");
		ER_BUTTON.setCallbackData("EX");

		List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
		rowsInLine.add(List.of(TG_BUTTON));
		rowsInLine.add(List.of(WB_BUTTON));
		rowsInLine.add(List.of(ER_BUTTON));

		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		markupInline.setKeyboard(rowsInLine);

		return markupInline;
	}
}
