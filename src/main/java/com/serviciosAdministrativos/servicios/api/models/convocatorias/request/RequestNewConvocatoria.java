package com.serviciosAdministrativos.servicios.api.models.convocatorias.request;

public class RequestNewConvocatoria {
    public String nombre;
    public String descon;
    public String cod_emp;
    public String unidad;
    public String fini;
    public String ffin;
    public String tipo;
    public String periodo;
    public String idadm;
    public String correo_dadm;
    public String dirunidad;
    public String correo_dirunidad;

    public RequestNewConvocatoria(String nombre, String descon, String cod_emp, String unidad, String fini, String ffin, String tipo, String periodo, String idadm, String correo_dadm, String dirunidad, String correo_dirunidad) {
        this.nombre = nombre;
        this.descon = descon;
        this.cod_emp = cod_emp;
        this.unidad = unidad;
        this.fini = fini;
        this.ffin = ffin;
        this.tipo = tipo;
        this.periodo = periodo;
        this.idadm = idadm;
        this.correo_dadm = correo_dadm;
        this.dirunidad = dirunidad;
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

    public String getFini() {
        return fini;
    }

    public void setFini(String fini) {
        this.fini = fini;
    }

    public String getFfin() {
        return ffin;
    }

    public void setFfin(String ffin) {
        this.ffin = ffin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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
