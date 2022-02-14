package com.example.demo;

import com.example.demo.cliente.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	private ClienteService clienteService;

	@Autowired
	public void ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Test
	void contextLoads() {
	}

}
