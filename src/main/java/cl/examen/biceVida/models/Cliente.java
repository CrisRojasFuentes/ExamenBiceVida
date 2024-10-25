package cl.examen.biceVida.models;

public class Cliente {

	private int id;
	private String rut;
	private String name;

	public Cliente() {
		super();
	}

	public Cliente(int id, String rut, String name) {
		this.id = id;
		this.rut = rut;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", rut=" + rut + ", name=" + name + "]";
	}

}
