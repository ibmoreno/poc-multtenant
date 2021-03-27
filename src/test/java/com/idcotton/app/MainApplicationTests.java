package com.idcotton.app;

import com.idcotton.app.config.security.util.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainApplicationTests {

	@Test
	void contextLoads() {

		System.out.println(PasswordUtils.gerarBCrypt("terrivel"));

	}

}
