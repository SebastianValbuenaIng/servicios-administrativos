package com.serviciosAdministrativos.servicios.api.models.votaciones.request;

import java.util.List;

public class VotoRequest {
    private Integer idcrp;
    private List<Integer> candidatos;

    public List<Integer> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Integer> candidatos) {
        this.candidatos = candidatos;
    }

    public Integer getIdcrp() {
        return idcrp;
    }

    public void setIdcrp(Integer idcrp) {
        this.idcrp = idcrp;
    }
}