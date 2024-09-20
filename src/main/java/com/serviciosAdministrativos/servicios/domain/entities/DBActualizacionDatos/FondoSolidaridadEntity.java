package com.serviciosAdministrativos.servicios.domain.entities.DBActualizacionDatos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "usr_eci_fondo_solidaridad")
public class FondoSolidaridadEntity {
    @Id
    private String cod_emp;
    private Boolean enero;
    private Boolean febrero;
    private Boolean marzo;
    private Boolean abril;
    private Boolean mayo;
    private Boolean junio;
    private Boolean julio;
    private Boolean agosto;
    private Boolean septiembre;
    private Boolean octubre;
    private Boolean noviembre;
    private Boolean diciembre;
    private Boolean donacion_indefinida;
    private Integer valor;
    private LocalDateTime fec_graba;

    public FondoSolidaridadEntity() {
    }

    public String getCod_emp() {
        return cod_emp;
    }

    public void setCod_emp(String cod_emp) {
        this.cod_emp = cod_emp;
    }

    public Boolean getEnero() {
        return enero;
    }

    public void setEnero(Boolean enero) {
        this.enero = enero;
    }

    public Boolean getFebrero() {
        return febrero;
    }

    public void setFebrero(Boolean febrero) {
        this.febrero = febrero;
    }

    public Boolean getMarzo() {
        return marzo;
    }

    public void setMarzo(Boolean marzo) {
        this.marzo = marzo;
    }

    public Boolean getAbril() {
        return abril;
    }

    public void setAbril(Boolean abril) {
        this.abril = abril;
    }

    public Boolean getMayo() {
        return mayo;
    }

    public void setMayo(Boolean mayo) {
        this.mayo = mayo;
    }

    public Boolean getJunio() {
        return junio;
    }

    public void setJunio(Boolean junio) {
        this.junio = junio;
    }

    public Boolean getJulio() {
        return julio;
    }

    public void setJulio(Boolean julio) {
        this.julio = julio;
    }

    public Boolean getAgosto() {
        return agosto;
    }

    public void setAgosto(Boolean agosto) {
        this.agosto = agosto;
    }

    public Boolean getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(Boolean septiembre) {
        this.septiembre = septiembre;
    }

    public Boolean getOctubre() {
        return octubre;
    }

    public void setOctubre(Boolean octubre) {
        this.octubre = octubre;
    }

    public Boolean getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(Boolean noviembre) {
        this.noviembre = noviembre;
    }

    public Boolean getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(Boolean diciembre) {
        this.diciembre = diciembre;
    }

    public Boolean getDonacion_indefinida() {
        return donacion_indefinida;
    }

    public void setDonacion_indefinida(Boolean donacion_indefinida) {
        this.donacion_indefinida = donacion_indefinida;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public LocalDateTime getFec_graba() {
        return fec_graba;
    }

    public void setFec_graba(LocalDateTime fec_graba) {
        this.fec_graba = fec_graba;
    }
}
