package com.company.app.wildberries.service;

import com.company.app.wildberries.dto.LotDto;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotService {

	@Autowired
	LotRepository lotRepository;

	public Lot getLot(Long id) {
		Optional<Lot> optional = lotRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new ObjectNotFoundException(id, Lot.class.getName());
		}
	}

	public List<Lot> getAll() {
		return lotRepository.findAll();
	}

	public Long create(String name, String price, String discount) {
		Lot lot = Lot.builder()
				.name(name)
				.price(price)
				.discount(discount)
				.build();
		return lotRepository.save(lot).getId();
	}

	public Long create(LotDto lotDto) {
		Lot lot = Lot.builder()
				.name(lotDto.getName())
				.price(lotDto.getPrice())
				.discount(lotDto.getDiscount())
				.build();
		return lotRepository.save(lot).getId();
	}

	public Boolean deleteLot(Long id) {
		lotRepository.deleteById(id);
		return true;
	}
}
