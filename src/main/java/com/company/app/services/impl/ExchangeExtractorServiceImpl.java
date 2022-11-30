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

    private static final String URL = "https://aliexpress.ru/item/1005004176116159.html?sku_id=12000028287776246";

    @Autowired
    private DataExtractorService dataExtractorService;

    @SneakyThrows
    @Override
    public Exchange extractCurse() {
        String price = getExchange(dataExtractorService.getHtmlResponse(URL));

        AliExpressExchange aliExpressExchange = AliExpressExchange.builder()
                .exchange(price)
                .build();

        return Exchange.builder()
                .stockExchange("todo") //todo
                .centralBankExchange("todo") //todo
                .aliExpressExchange(aliExpressExchange)
                .build();
    }

    public String getExchange(String htmlResponse) {
        Document document = Jsoup.parse(htmlResponse);
        Elements scripts = document.getElementsByTag("script");
        String s = scripts.get(3).childNodes().get(0).attributes().get("#data");
        JSONObject jsonObject = new JSONObject(s);

        JSONObject activityAmount = dataExtractorService.getJsonObject(jsonObject, "activityAmount");
        return String.valueOf(activityAmount.getDouble("value"));
    }
}
