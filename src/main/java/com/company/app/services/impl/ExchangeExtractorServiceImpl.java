package com.company.app.services.impl;

import com.company.app.data.AliExpressExchange;
import com.company.app.data.Exchange;
import com.company.app.data.Price;
import com.company.app.services.api.ExchangeExtractorService;
import com.company.app.tools.api.DataExtractorService;
import lombok.Setter;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Setter
public class ExchangeExtractorServiceImpl implements ExchangeExtractorService {

    private static final String URL = "https://www.aliexpress.com/item/3256803390997951.html";

    @Autowired
    private DataExtractorService dataExtractorService;

    @SneakyThrows
    @Override
    public Exchange extractCurse() {

        Price rubPrice = getPrice(dataExtractorService.getHtmlResponse(URL));
        Price usdPrice = new Price("35", "USD"); //todo
        AliExpressExchange aliExpressExchange = new AliExpressExchange(rubPrice, usdPrice);

        return Exchange.builder()
                .stockExchange("todo") //todo
                .centralBankExchange("todo") //todo
                .aliExpressExchange(aliExpressExchange)
                .build();
    }

    private Price getPrice(String htmlResponse) {
        Document document = Jsoup.parse(htmlResponse);
        Elements scripts = document.getElementsByTag("script");
        String s = scripts.get(2).childNodes().get(0).attributes().get("#data");
        JSONObject object = new JSONObject(s);
        JSONObject mainEntity = (JSONObject) object.get("mainEntity");
        JSONObject offers = (JSONObject) mainEntity.get("offers");

        String price = String.valueOf(offers.get("price"));
        String priceCurrency = String.valueOf(offers.get("priceCurrency"));

        if (price.contains(".")) {
            String[] split = price.split("\\.");
            price = split[0];
        }
        return new Price(price, priceCurrency);
    }
}
