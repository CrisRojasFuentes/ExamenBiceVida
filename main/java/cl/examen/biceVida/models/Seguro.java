package cl.examen.biceVida.models;

public class Seguro {
	private int id;
	private String name;

	public Seguro() {
		super();
	}

	public Seguro(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Seguro [id=" + id + ", name=" + name + "]";
	}

}