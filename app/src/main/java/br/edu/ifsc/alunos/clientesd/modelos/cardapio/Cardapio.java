package br.edu.ifsc.alunos.clientesd.modelos.cardapio;

import com.google.gson.annotations.SerializedName;

public class Cardapio {

    @SerializedName(value="id")
    private Integer id;

    @SerializedName(value="dia")
    private Integer dia;

    @SerializedName(value="mes")
    private Integer mes;

    @SerializedName(value="ano")
    private Integer ano;

    @SerializedName(value="primeiroPrato")
    private String primeiroPrato;

    @SerializedName(value="segundoPrato")
    private String segundoPrato;

    @SerializedName(value="terceiroPrato")
    private String terceiroPrato;

    @SerializedName(value="quartoPrato")
    private String quartoPrato;

    @SerializedName(value="quintoPrato")
    private String quintoPrato;

    @SerializedName(value="sextoPrato")
    private String sextoPrato;

    @SerializedName(value="setimoPrato")
    private String setimoPrato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getPrimeiroPrato() {
        return primeiroPrato;
    }

    public void setPrimeiroPrato(String primeiroPrato) {
        this.primeiroPrato = primeiroPrato;
    }

    public String getSegundoPrato() {
        return segundoPrato;
    }

    public void setSegundoPrato(String segundoPrato) {
        this.segundoPrato = segundoPrato;
    }

    public String getTerceiroPrato() {
        return terceiroPrato;
    }

    public void setTerceiroPrato(String terceiroPrato) {
        this.terceiroPrato = terceiroPrato;
    }

    public String getQuartoPrato() {
        return quartoPrato;
    }

    public void setQuartoPrato(String quartoPrato) {
        this.quartoPrato = quartoPrato;
    }

    public String getQuintoPrato() {
        return quintoPrato;
    }

    public void setQuintoPrato(String quintoPrato) {
        this.quintoPrato = quintoPrato;
    }

    public String getSextoPrato() {
        return sextoPrato;
    }

    public void setSextoPrato(String sextoPrato) {
        this.sextoPrato = sextoPrato;
    }

    public String getSetimoPrato() {
        return setimoPrato;
    }

    public void setSetimoPrato(String setimoPrato) {
        this.setimoPrato = setimoPrato;
    }
}
