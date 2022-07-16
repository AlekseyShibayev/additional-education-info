package com.company.app.services.impl;

import com.company.app.data.Exchange;
import com.company.app.services.api.ExchangeExtractorService;
import com.company.app.tools.api.DataExtractorService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeExtractorServiceImpl implements ExchangeExtractorService {

    @Autowired
    private DataExtractorService dataExtractorService;

    @SneakyThrows
    @Override
    public Exchange extractCurse() {
        String htmlResponse = dataExtractorService.getHtmlResponse("https://aliexpress.ru/item/1005003577312703.html?sku_id=12000026945335517&gatewayAdapt=glo2rus");
        String htmlResponse2 = dataExtractorService.getHtmlResponse("https://www.aliexpress.com/item/1005003577312703.html?sku_id=12000026945335517");
        return null;
    }
}
