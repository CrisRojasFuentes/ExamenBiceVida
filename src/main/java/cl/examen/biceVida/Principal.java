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

import cl.examen.biceVida.models.Cliente;
import cl.examen.biceVida.models.Cuenta;
import cl.examen.biceVida.models.Seguro;

public class Principal {

	private static final List<Cliente> clients = new ArrayList<>(
			List.of(new Cliente(1, "86620855", "DANIEL BUSTOS"), new Cliente(2, "7317855K", "NICOLAS PEREZ"),
					new Cliente(3, "73826497", "ERNESTO GRANADO"), new Cliente(4, "88587715", "JORDAN MARTINEZ"),
					new Cliente(5, "94020190", "ALEJANDRO ZELADA"), new Cliente(6, "99804238", "DENIS ROJAS")));
	private static final List<Cuenta> accounts = new ArrayList<>(
			List.of(new Cuenta(6, 1, 15000), new Cuenta(1, 3, 18000), new Cuenta(5, 3, 135000), new Cuenta(2, 2, 5600),
					new Cuenta(3, 1, 23000), new Cuenta(5, 2, 15000), new Cuenta(3, 3, 45900), new Cuenta(2, 3, 19000),
					new Cuenta(4, 3, 51000), new Cuenta(5, 1, 89000), new Cuenta(1, 2, 1600), new Cuenta(5, 3, 37500),
					new Cuenta(6, 1, 19200), new Cuenta(2, 3, 10000), new Cuenta(3, 2, 5400), new Cuenta(3, 1, 9000),
					new Cuenta(4, 3, 13500), new Cuenta(2, 1, 38200), new Cuenta(5, 2, 17000), new Cuenta(1, 3, 1000),
					new Cuenta(5, 2, 600), new Cuenta(6, 1, 16200), new Cuenta(2, 2, 10000)));
	private static final List<Seguro> insurances = new ArrayList<>(List.of(new Seguro(1, "SEGURO APV"),
			new Seguro(2, "SEGURO DE VIDA"), new Seguro(3, "SEGURO COMPLEMENTARIO DE SALUD")));

	// Método para listar los IDs de clientes
	public static List<Integer> listClientsIds() {
		// CODE HERE
		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < clients.size(); i++) {
			ids.add(clients.get(i).getId());
		}
		System.out.println(ids);
		return ids;
	}

	// 2 -Método para listar los IDs de clientes ordenados por RUT
	public static List<Integer> listClientsIdsSortedByRUT() {
		// CODE HERE
		return clients.stream().sorted((o1, o2) -> o1.getRut().compareTo(o2.getRut())).map(Cliente::getId)
				.collect(Collectors.toList());
	}

	// Método para listar los nombres de clientes
	// ordenados de mayor a menor por la suma TOTAL de los saldos de cada cliente
	// en los seguros que participa
	public static List<String> sortClientsTotalBalances() {
		// CODE HERE
		List<Cuenta> listaCuenta;
		List<String> lista = new ArrayList<>();

		listaCuenta = ordenaClienteId(accounts);

		Map<Integer, Double> total = new HashMap<>();

		// sumo balances
		for (Cuenta cuenta : listaCuenta) {
			total.put(cuenta.getClientId(), total.getOrDefault(cuenta.getClientId(), 0.0) + cuenta.getBalance());
		}
		// ordeno de mayor a menor
		List<Map.Entry<Integer, Double>> sortBalance = total.entrySet().stream()
				.sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue())).collect(Collectors.toList());

		// muestro nombres
		for (Entry<Integer, Double> balanceMayor : sortBalance) {
			for (int i = 0; i < clients.size(); i++) {
				if (balanceMayor.getKey() == clients.get(i).getId()) {
					lista.add(clients.get(i).getName());
				}
			}
		}

		System.out.println(lista);

		return lista;
	}

	private static List<Cuenta> ordenaClienteId(List<Cuenta> accounts) {
		List<Cuenta> listaOrdenada = new ArrayList<>();
		try {
			listaOrdenada = accounts.stream().sorted((o1, o2) -> Integer.compare(o1.getClientId(), o2.getClientId()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaOrdenada;

	}

	// Método para generar un objeto en que las claves sean los nombres de los
	// seguros y
	// los valores un arreglo con los RUTs de sus clientes
	// ordenados alfabéticamente por nombre
	public static Map<String, List<String>> insuranceClientsByRUT() {
		// CODE HERE
		Map<String, List<String>> lista = new HashMap<>();

		// recorro los seguros
		for (Seguro seguro : insurances) {
			List<String> ruts = accounts.stream().filter(cuenta -> cuenta.getInsuranceId() == seguro.getId())
					.map(cuenta -> clients.stream().filter(cliente -> cliente.getId() == cuenta.getClientId())
							.map(Cliente::getRut).findFirst().orElse(null))
					.filter(Objects::nonNull).collect(Collectors.toList());

			// ordeno alfabeticamente
//			List<String> rutsOrdenados = ruts.stream().sorted(Comparator.comparing(rut -> {
//				Cliente cliente = clients.stream().filter(c -> (c.getRut().equals(rut))).findFirst().orElse(null);
//				return cliente != null ? cliente.getName() : "";
//			})).collect(Collectors.toList());
			List<String> rutsOrdenados = ruts.stream()
					.map(rut -> clients.stream()
							.filter(cliente -> cliente.getRut().equals(rut))
							.findFirst()
							.orElse(null))
					.filter(Objects::nonNull)
					.sorted(Comparator.comparing(Cliente::getName))
					.map(Cliente::getRut)
					.collect(Collectors.toList());

			lista.put(seguro.getName(), rutsOrdenados);
		}

		for (Map.Entry<String, List<String>> x : lista.entrySet()) {
			System.out.println(x);
		}
		return lista;
	}

	// Método para generar un arreglo ordenado decrecientemente con los saldos
	// de clientes que tengan más de 30.000 en el"Seguro APV"
	public static List<Integer> higherClientsBalances() {
		// CODE HERE

		List<Integer> balances = accounts.stream().filter(cuenta -> cuenta.getInsuranceId() == 1)
				.filter(cuenta -> cuenta.getBalance() > 30000).map(Cuenta::getBalance).sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		System.out.println(balances);
		return balances;
	}

	// Método para generar un arreglo con IDs de los seguros ordenados //
	// crecientemente por la cantidad TOTAL de dinero que administran
	public static List<Integer> insuranceSortedByHighestBalance() {
		// CODE HERE

		// Calcular Total por seguro
		Map<Integer, Integer> totalPorSeguro = accounts.stream()
				.collect(Collectors.groupingBy(Cuenta::getInsuranceId, Collectors.summingInt(Cuenta::getBalance)));

		// ordenar por la cantidad de dinero
		List<Map.Entry<Integer, Integer>> ordenSeguro = new ArrayList<>(totalPorSeguro.entrySet());
		ordenSeguro.sort(Map.Entry.comparingByValue());

		// obtengo los id de los seguros
		Integer[] seguroIdArray = ordenSeguro.stream().map(Map.Entry::getKey).toArray(Integer[]::new);

		return Arrays.asList(seguroIdArray);
	}

	// Método para generar un objeto en que las claves sean los nombres de los //
	// Seguros y los valores el número de clientes que solo tengan cuentas en ese
	// seguro
	public static Map<String, Long> uniqueInsurance() {
		// CODE HERE
		Map<String, Long> clienteMap = new HashMap<>();

		// recorro seguro
		for (Seguro seguro : insurances) {
			int contador = (int) clients.stream().filter(cliente -> {
				// obtengo cuentas por cliente
				List<Cuenta> cuentaCliente = accounts.stream().filter(cuenta -> cuenta.getClientId() == cliente.getId())
						.collect(Collectors.toList());
				// filtro las cuentas para saber si pertenecen al mismo seguro
				return cuentaCliente.stream().allMatch(cuenta -> cuenta.getInsuranceId() == seguro.getId());
			}).count();
			clienteMap.put(seguro.getName(), Long.parseLong(String.valueOf(contador)));
		}

		return clienteMap;
	}

	// Método para generar un objeto en que las claves sean los nombres de los //
	// Seguros y los valores el ID de su cliente con menos fondos
	public static Map<String, Integer> clientWithLessFunds() {
		// CODE HERE

		Map<Integer, Double> segurosPorClientes = new HashMap<>();

		for (Cuenta cuenta : accounts) {
			segurosPorClientes.put(cuenta.getClientId(),
					segurosPorClientes.getOrDefault(cuenta.getClientId(), 0.0) + cuenta.getBalance());

		}

		Map<String, Integer> clienteMenosFondos = new HashMap<>();

		for (Seguro seguro : insurances) {
			Integer clienteIdMin = null;
			Double minFondo = Double.MAX_VALUE;

			// busco cliente con menos fondoss
			for (Map.Entry<Integer, Double> data : segurosPorClientes.entrySet()) {
				if (data.getValue() < minFondo) {
					minFondo = data.getValue();
					clienteIdMin = data.getKey();
				}
			}
			// agrego cliente al seguro corriespondiente
			if (clienteIdMin != null) {
				clienteMenosFondos.put(seguro.getName(), clienteIdMin);
			}
		}

		System.out.println(clienteMenosFondos);

		return clienteMenosFondos;
	}

	// Método para agregar un nuevo cliente con datos ficticios y una cuenta en
	// el // "SEGURO COMPLEMENTARIO DE SALUD" con un saldo de 15000 para este
	// nuevo cliente, luego devolver el // lugar que ocupa este cliente en el
	// ranking de la pregunta 2
	public static int newClientRanking() {
		// CODE HERE }

		String rut = "123";
		String nombre = "nombre";
		Cliente nuevoCli = new Cliente(clients.size() + 1, rut, nombre);
		clients.add(nuevoCli);

		int seguroId = 3;// id de: SEGURO COMPLEMENTARIO DE SALUD
		Cuenta nuevacuenta = new Cuenta(nuevoCli.getId(), seguroId, 15000);
		accounts.add(nuevacuenta);

		// pbtengo rut ordenados
		List<Integer> idsOrdenados = listClientsIdsSortedByRUT();

		int x = idsOrdenados.indexOf(nuevoCli.getId());
		return x;
	}

}
