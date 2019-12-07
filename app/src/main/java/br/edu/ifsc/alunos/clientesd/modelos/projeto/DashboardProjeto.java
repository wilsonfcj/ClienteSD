package br.edu.ifsc.alunos.clientesd.modelos.projeto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DashboardProjeto {
	
	@SerializedName(value="TotalProjetos")
	private int totalProjetos;
	@SerializedName(value="QuantidadePorSituacao")
	private List<ProjetosPorSituacao> projetosPorSituacaos;
	
	public int getTotalProjetos() {
		return totalProjetos;
	}
	
	public void setTotalProjetos(int totalProjetos) {
		this.totalProjetos = totalProjetos;
	}
	
	public List<ProjetosPorSituacao> getProjetosPorSituacaos() {
		return projetosPorSituacaos;
	}
	
	public void setProjetosPorSituacaos(List<ProjetosPorSituacao> projetosPorSituacaos) {
		this.projetosPorSituacaos = projetosPorSituacaos;
	}
	
}
