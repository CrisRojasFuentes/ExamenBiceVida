package cl.examen.biceVida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;

import java.security.DomainCombiner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import cl.examen.biceVida.models.Cuenta;

//@SpringBootTest
class BiceVidaApplicationTests {
	
//	@Test
//	void contextLoads() {
//		insuranceClientsByRUT();
//	}
	
	@Test
	public void insuranceClientsByRUT() {
		Map<String, List<String>> result = Principal.insuranceClientsByRUT();
		assertNotNull(result);
		//assertEquals(2, result.size());
		
		
		List<String> ruts = new ArrayList<>( List.of("94020190", "94020190", "94020190", "86620855", "73826497", "7317855K", "7317855K"));
		assertIterableEquals(ruts, result.get("SEGURO DE VIDA"));
	}

	
	
	@Test
	public void higherClientsBalances() {
		List<Integer> result = Principal.higherClientsBalances();
		
		assertNotNull(result);
	
		for(Integer balance : result) {
			assertTrue(balance > 30000, "Se encontro balance mayor a 30.000: " + balance);
		}
		assertTrue(enOrden(result), "Lista no ordenada");
	}
	
	private boolean enOrden(List<Integer> lista) {
		for(int i = 0; i < lista.size()-1; i++) {
			if(lista.get(i) < lista.get(i+1)) {
				return false;
			}
		}
		return true;
	}
	
	@Test
	public void insuranceSortedByHighestBalance() {
		List<Integer> result = Principal.insuranceSortedByHighestBalance();
		
		assertNotNull(result);
		
		List<Integer> ids = List.of(2,1,3);
		
		assertIterableEquals(ids, result, "no ordenado");
		
		
		
	}
	

}
