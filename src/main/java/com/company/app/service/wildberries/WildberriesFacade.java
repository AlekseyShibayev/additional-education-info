package com.company.app.service.wildberries;

import com.company.app.service.wildberries.components.WildberriesServiceImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Setter
public class WildberriesFacade {

	@Autowired
	private WildberriesServiceImpl wildberriesService;

	public void doMainLogic() {
		wildberriesService.doMainLogic();
	}
}
