package br.edu.ifsc.alunos.clientesd.modelos.estacionamento;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Vaga implements Serializable {

    @SerializedName(value="Numero")
    public Integer numero;

    @SerializedName(value="Ocupada")
    public Boolean situacao;

    public Vaga(Integer numero, Boolean situacao) {
        super();
        this.numero = numero;
        this.situacao = situacao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }
}
