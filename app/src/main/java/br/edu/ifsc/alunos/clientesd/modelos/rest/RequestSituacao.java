package br.edu.ifsc.alunos.clientesd.modelos.rest;

import com.google.gson.annotations.SerializedName;

public class RequestSituacao {
	
	@SerializedName(value="Ano")
	private Integer ano;
	
	@SerializedName(value="Situacao")
	private Integer situacao;

	public RequestSituacao(Integer ano, Integer situacao) {
		this.ano = ano;
		this.situacao = situacao;
	}

	public RequestSituacao() {
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	} 
	
	
}
