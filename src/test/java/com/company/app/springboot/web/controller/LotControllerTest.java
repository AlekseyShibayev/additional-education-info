package com.company.app.springboot.web.controller;

import com.company.app.springboot.web.WebMvcSpringBootTestContext;
import com.company.app.wildberries.dto.LotDto;
import com.company.app.wildberries.entity.Lot;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class LotControllerTest extends WebMvcSpringBootTestContext {

	private Lot createTestLot() {
		return Lot.builder().id(1L).name("name").price("price").build();
	}

	private LotDto createLotDto() {
		return LotDto.builder().name("name").price("price").discount("discount").build();
	}

	@Test
	void get_mustBe() throws Exception {
		Lot lot = createTestLot();
		Mockito.when(lotService.get(Mockito.any())).thenReturn(lot);

		mockMvc.perform(MockMvcRequestBuilders.get("/wildberries/lot/1"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value("price"))
		;
	}

	@Test
	void post_mustBe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.post("/wildberries/lot/")
						.content(objectMapper.writeValueAsString(createLotDto()))
						.contentType(MediaType.APPLICATION_JSON)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
		;
	}

	@Test
	void put_mustBe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.put("/wildberries/lot/1")
						.content(objectMapper.writeValueAsString(createLotDto()))
						.contentType(MediaType.APPLICATION_JSON)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
		;
	}

	@Test
	void delete_mustBe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/wildberries/lot/1"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
		;
	}
}
