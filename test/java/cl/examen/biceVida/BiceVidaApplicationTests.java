package cl.examen.biceVida;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.DomainCombiner;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BiceVidaApplicationTests {
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public static Map<String, List<String>> insuranceClientsByRUT() {
		Map<String, List<String>> result = BiceVidaApplicationTests.insuranceClientsByRUT();
		assertNotNull(result);
		assertEquals(2, result.size());
		
		
		List<String> rut = List.of("86620855","7317855K");
		assertEquals(2, result);
		
		result.put("SEGURO DE VIDA", rut);
		return result;
	}
	
	@Test
	public static List<Integer> higherClientsBalances() {
		return null;
	}
	
	@Test
	public static List<Integer> insuranceSortedByHighestBalance() {
		return null;
	}
	

}
