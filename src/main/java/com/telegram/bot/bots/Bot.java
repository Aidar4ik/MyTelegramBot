package com.telegram.bot.bots;


import com.telegram.bot.menu.MainMenu;
import com.telegram.bot.menu.babies.BabiesMenu;
import com.telegram.bot.menu.mens.MensMenu;
import com.telegram.bot.menu.optoms.OptomMenu;
import com.telegram.bot.menu.womens.WomensMenu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botUserName;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText().toString();
        switch (message) {
            case "/start":
                MainMenu mainMenu = new MainMenu();
                try {
                    execute(mainMenu.getMainMenu(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Женская одежда":
                WomensMenu womensMenu = new WomensMenu();
                try {
                    execute(womensMenu.getWomensMenu(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Мужская одежда":
                MensMenu mensMenu = new MensMenu();
                try {
                    execute(mensMenu.getMensMenu(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Детская одежда":
                BabiesMenu babiesMenu = new BabiesMenu();
                try {
                    execute(babiesMenu.getBabiesMenu(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Товары Оптом":
                OptomMenu optomMenu = new OptomMenu();
                try {
                    execute(optomMenu.getMensMenu(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
