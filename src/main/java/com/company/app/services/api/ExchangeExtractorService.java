package com.company.app.services.api;

import com.company.app.data.Exchange;
import org.springframework.stereotype.Component;

@Component
public interface ExchangeExtractorService {

	public Exchange extractCurse();
}
