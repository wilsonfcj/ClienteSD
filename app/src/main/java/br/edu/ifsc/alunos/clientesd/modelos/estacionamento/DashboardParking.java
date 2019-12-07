package br.edu.ifsc.alunos.clientesd.modelos.estacionamento;

import com.google.gson.annotations.SerializedName;

public class DashboardParking {
	
	@SerializedName(value="TotalVagas")
	int totalParkingSpaces;
	
	@SerializedName(value="VagasDisponiveis")
	int freeParkingSpace;

	public DashboardParking(int totalParkingSpaces, int freeParkingSpace) {
		this.totalParkingSpaces = totalParkingSpaces;
		this.freeParkingSpace = freeParkingSpace;
	}

	public DashboardParking() {

	}

	public int getTotalParkingSpaces() {
		return totalParkingSpaces;
	}

	public void setTotalParkingSpaces(int totalParkingSpaces) {
		this.totalParkingSpaces = totalParkingSpaces;
	}

	public int getFreeParkingSpace() {
		return freeParkingSpace;
	}

	public void setFreeParkingSpace(int freeParkingSpace) {
		this.freeParkingSpace = freeParkingSpace;
	}
	
}
