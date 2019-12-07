package br.edu.ifsc.alunos.clientesd.modelos.projeto;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Projeto {
	
	@SerializedName(value="Codigo")
	private String codigo;
	@SerializedName(value="Titulo")
	private String titulo;
	@SerializedName(value="Tipo")
	private String tipo;
	@SerializedName(value="Categoria")
	private String categoria;
	@SerializedName(value="Situacao")
	private int situacao;
	@SerializedName(value="Area")
	private String area;
	@SerializedName(value="EmailCoordenador")
	private String emailCoordenador;
	@SerializedName(value="NomeCoordenador")
	private String coordenador;
	@SerializedName(value="DataCadastro")
	private Date dataCadastro;
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public int getSituacao() {
		return situacao;
	}
	
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getEmailCoordenador() {
		return emailCoordenador;
	}
	
	public void setEmailCoordenador(String emailCoordenador) {
		this.emailCoordenador = emailCoordenador;
	}
	
	public String getCoordenador() {
		return coordenador;
	}
	
	public void setCoordenador(String coordenador) {
		this.coordenador = coordenador;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dateCadastro) {
		this.dataCadastro = dateCadastro;
	}
	
	
}
