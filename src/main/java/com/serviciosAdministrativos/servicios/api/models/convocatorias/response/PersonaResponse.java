package com.serviciosAdministrativos.servicios.api.models.convocatorias.response;

public class PersonaResponse {
    public Integer cod_emp;
    public String nombre;
    public Integer cod_cl1;
    public String area;
    private String correo;

    public Integer getCod_emp() {
        return cod_emp;
    }

    public void setCod_emp(Integer cod_emp) {
        this.cod_emp = cod_emp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCod_cl1() {
        return cod_cl1;
    }

    public void setCod_cl1(Integer cod_cl1) {
        this.cod_cl1 = cod_cl1;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
