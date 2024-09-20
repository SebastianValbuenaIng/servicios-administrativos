package com.serviciosAdministrativos.servicios.api.models.fondoSolidaridad.request;

import java.util.List;

public class FondoSolidaridadRequest {
    private String cod_emp;
    private List<String> meses;
    private String mes_donacion_indefinida;
    private Integer valor;

    public FondoSolidaridadRequest(String cod_emp, List<String> meses, String mes_donacion_indefinida, Integer valor) {
        this.cod_emp = cod_emp;
        this.meses = meses;
        this.mes_donacion_indefinida = mes_donacion_indefinida;
        this.valor = valor;
    }

    public String getCod_emp() {
        return cod_emp;
    }

    public void setCod_emp(String cod_emp) {
        this.cod_emp = cod_emp;
    }

    public List<String> getMeses() {
        return meses;
    }

    public void setMeses(List<String> meses) {
        this.meses = meses;
    }

    public String getMes_donacion_indefinida() {
        return mes_donacion_indefinida;
    }

    public void setMes_donacion_indefinida(String mes_donacion_indefinida) {
        this.mes_donacion_indefinida = mes_donacion_indefinida;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
