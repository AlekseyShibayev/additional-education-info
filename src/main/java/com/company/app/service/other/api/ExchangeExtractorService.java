package com.company.app.service.other.api;

import com.company.app.entity.Exchange;
import org.springframework.stereotype.Component;

@Component
public interface ExchangeExtractorService {

	public Exchange extractCurse();
}
