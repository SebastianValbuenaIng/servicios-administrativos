package com.serviciosAdministrativos.servicios.api.models.convocatorias.request;

import java.time.LocalDate;

public class RequestUpdateConvocatoria {
     public String nombre;
    public String descon;
    public String cod_emp;
    public String unidad;
    public LocalDate fini;
    public LocalDate ffin;
    public String tipo;
    public String peracad;
    public String idadm;
    public String dirunidad;
    public String correo_dadm;
    public String correo_dirunidad;

    public RequestUpdateConvocatoria(String nombre, String descon, String cod_emp, String unidad, LocalDate fini, LocalDate ffin, String tipo, String peracad, String idadm, String dirunidad, String correo_dadm, String correo_dirunidad) {
        this.nombre = nombre;
        this.descon = descon;
        this.cod_emp = cod_emp;
        this.unidad = unidad;
        this.fini = fini;
        this.ffin = ffin;
        this.tipo = tipo;
        this.peracad = peracad;
        this.idadm = idadm;
        this.dirunidad = dirunidad;
        this.correo_dadm = correo_dadm;
        this.correo_dirunidad = correo_dirunidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescon() {
        return descon;
    }

    public void setDescon(String descon) {
        this.descon = descon;
    }

    public String getCod_emp() {
        return cod_emp;
    }

    public void setCod_emp(String cod_emp) {
        this.cod_emp = cod_emp;
    }

    public LocalDate getFini() {
        return fini;
    }

    public void setFini(LocalDate fini) {
        this.fini = fini;
    }

    public LocalDate getFfin() {
        return ffin;
    }

    public void setFfin(LocalDate ffin) {
        this.ffin = ffin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPeracad() {
        return peracad;
    }

    public void setPeracad(String periodo) {
        this.peracad = periodo;
    }

    public String getIdadm() {
        return idadm;
    }

    public void setIdadm(String idadm) {
        this.idadm = idadm;
    }

    public String getDirunidad() {
        return dirunidad;
    }

    public void setDirunidad(String dirunidad) {
        this.dirunidad = dirunidad;
    }

    public String getCorreo_dadm() {
        return correo_dadm;
    }

    public void setCorreo_dadm(String correo_dadm) {
        this.correo_dadm = correo_dadm;
    }

    public String getCorreo_dirunidad() {
        return correo_dirunidad;
    }

    public void setCorreo_dirunidad(String correo_dirunidad) {
        this.correo_dirunidad = correo_dirunidad;
    }
}
