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
		return Lot.builder().id(1L).article("article").desiredPrice("desiredPrice").build();
	}

	private LotDto createLotDto() {
		return LotDto.builder().article("article").desiredPrice("desiredPrice").discount("discount").build();
	}

	@Test
	void get_mustBe() throws Exception {
		Lot lot = createTestLot();
		Mockito.when(lotService.read(Mockito.any())).thenReturn(lot);

		mockMvc.perform(MockMvcRequestBuilders.get("/wildberries/lot/1"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$.article").value("article"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.desiredPrice").value("desiredPrice"))
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
