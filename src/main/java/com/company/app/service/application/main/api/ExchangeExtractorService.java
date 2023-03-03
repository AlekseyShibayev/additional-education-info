package com.company.app.service.application.main.api;

import com.company.app.entity.Exchange;
import org.springframework.stereotype.Component;

@Component
public interface ExchangeExtractorService {

	Exchange extractCurse();
}
