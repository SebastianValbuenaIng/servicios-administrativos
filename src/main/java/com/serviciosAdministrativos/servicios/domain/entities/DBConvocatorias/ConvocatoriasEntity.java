package com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "convoca")
public class ConvocatoriasEntity {
    @Id
    private Integer idcon;
    private String nombre;
    private String descon;
    private String unidad;
    private LocalDateTime fini;
    private LocalDateTime ffin;
    private String tipo;
    private Integer asig;
    private String peracad;
    private String estado;
    private Integer idadm;
    private Integer dirunidad;

    public Integer getIdcon() {
        return idcon;
    }

    public void setIdcon(Integer idcon) {
        this.idcon = idcon;
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

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public LocalDateTime getFini() {
        return fini;
    }

    public void setFini(LocalDateTime fini) {
        this.fini = fini;
    }

    public LocalDateTime getFfin() {
        return ffin;
    }

    public void setFfin(LocalDateTime ffin) {
        this.ffin = ffin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getAsig() {
        return asig;
    }

    public void setAsig(Integer asig) {
        this.asig = asig;
    }

    public String getPeracad() {
        return peracad;
    }

    public void setPeracad(String peracad) {
        this.peracad = peracad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdadm() {
        return idadm;
    }

    public void setIdadm(Integer idadm) {
        this.idadm = idadm;
    }

    public Integer getDirunidad() {
        return dirunidad;
    }

    public void setDirunidad(Integer dirunidad) {
        this.dirunidad = dirunidad;
    }
}