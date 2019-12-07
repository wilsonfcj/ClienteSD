package br.edu.ifsc.alunos.clientesd.modelos.rest;


import com.google.gson.annotations.SerializedName;

public class ResponseBase<E> {
	
	@SerializedName(value="Sucesso")
	private boolean sucesso;
	@SerializedName(value="Mensagem")
	private String mensagem;
	@SerializedName(value="Data")
	private E data;
	
	public ResponseBase() {
		super();
	}
	
	public ResponseBase(boolean sucesso, String mensagem, E data) {
		super();
		this.sucesso = sucesso;
		this.mensagem = mensagem;
		this.data = data;
	}
	public boolean isSucesso() {
		return sucesso;
	}
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	
	
}
