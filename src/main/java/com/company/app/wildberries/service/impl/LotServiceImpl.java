package com.company.app.wildberries.service.impl;

import com.company.app.wildberries.dto.LotDto;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import com.company.app.wildberries.service.api.LotService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotServiceImpl implements LotService {

	@Autowired
	LotRepository lotRepository;

	@Override
	public Lot get(Long id) {
		Optional<Lot> optional = lotRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new ObjectNotFoundException(id, Lot.class.getName());
		}
	}

	@Override
	public List<Lot> getAll() {
		return lotRepository.findAll();
	}

	@Override
	public Long create(String name, String price, String discount) {
		Lot lot = Lot.builder()
				.name(name)
				.price(price)
				.discount(discount)
				.build();
		return lotRepository.save(lot).getId();
	}

	@Override
	public Long create(LotDto lotDto) {
		Lot lot = Lot.builder()
				.name(lotDto.getName())
				.price(lotDto.getPrice())
				.discount(lotDto.getDiscount())
				.build();
		return lotRepository.save(lot).getId();
	}

	@Override
	public Boolean delete(Long id) {
		lotRepository.deleteById(id);
		return true;
	}

	@Override
	public Boolean update(Long id, LotDto lotDto) {
		Lot lot = new Lot();
		lot.setId(id);

		BeanUtils.copyProperties(lotDto, lot);
		lotRepository.save(lot);
		return true;
	}
}
