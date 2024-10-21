package cl.examen.biceVida.models;

public class Cuenta {
	private int clientId;
	private int insuranceId;
	private int balance;

	public Cuenta() {
		super();
	}

	public Cuenta(int clientId, int insuranceId, int balance) {
		this.clientId = clientId;
		this.insuranceId = insuranceId;
		this.balance = balance;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Cuenta [clientId=" + clientId + ", insuranceId=" + insuranceId + ", balance=" + balance + "]";
	}

}