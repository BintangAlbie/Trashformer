package com.example.trashformer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.trashformer.repository.TipeSampahRepository;
import com.example.trashformer.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class TrashformerApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TipeSampahRepository tipeSampahRepository;

	@Test
	void repositoriesLoad() {
		assertNotNull(userRepository);
		assertNotNull(tipeSampahRepository);
	}

}
