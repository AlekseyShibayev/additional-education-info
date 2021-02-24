package com.company.app.services.api;

import com.company.app.mainLogic.TradingLot;
import org.springframework.stereotype.Component;

@Component
public interface HtmlParserService {

    TradingLot createTradingLot(String htmlResponse);
}
