package cl.examen.biceVida;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BiceVidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiceVidaApplication.class, args);
		System.out.println("Examen Bice Vida");


		Principal.listClientsIds();

		Principal.insuranceClientsByRUT();// ok junit

		Principal.higherClientsBalances();// ok
		Principal.insuranceSortedByHighestBalance();// ok

		System.out.println(Principal.listClientsIdsSortedByRUT());
		System.out.println(Principal.newClientRanking());
		Principal.sortClientsTotalBalances();
		Principal.clientWithLessFunds();
	}

}






