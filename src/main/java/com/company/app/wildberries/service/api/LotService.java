package com.company.app.wildberries.service.api;

import com.company.app.wildberries.dto.LotDto;
import com.company.app.wildberries.entity.Lot;

import java.util.List;

public interface LotService {

	Lot getLot(Long id);

	List<Lot> getAll();

	Long create(String name, String price, String discount);

	Long create(LotDto lotDto);

	Boolean deleteLot(Long id);
}
