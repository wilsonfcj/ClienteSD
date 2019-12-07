package br.edu.ifsc.alunos.clientesd.modelos.projeto;

import com.google.gson.annotations.SerializedName;

public class ProjetosPorSituacao {
	
	@SerializedName(value="Situacao")
	private int situacao;
	@SerializedName(value="Quantidade")
	private int qtd;
	
	public ProjetosPorSituacao(int situacao) {
		super();
		this.situacao = situacao;
		this.qtd = 0;
	}
	
	public int getSituacao() {
		return situacao;
	}
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	public void addQtd() {
		this.qtd += 1;
	}

}
