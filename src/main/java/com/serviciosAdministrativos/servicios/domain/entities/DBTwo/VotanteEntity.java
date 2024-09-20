package com.serviciosAdministrativos.servicios.domain.entities.DBTwo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "votantes")
public class VotanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificacion;
    private Integer copasst;
    private Integer ccl;
    private Integer vcd_prof;
    private Integer vca_prof;
    private LocalDate fecha_copasst;
    private LocalDate fecha_ccl;
    private LocalDate fecha_vcdprof;
    private LocalDate fecha_vcaprof;

    public LocalDate getFecha_ccl() {
        return fecha_ccl;
    }

    public void setFecha_ccl(LocalDate fecha_ccl) {
        this.fecha_ccl = fecha_ccl;
    }

    public Integer getCcl() {
        return ccl;
    }

    public void setCcl(Integer ccl) {
        this.ccl = ccl;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getCopasst() {
        return copasst;
    }

    public void setCopasst(Integer copasst) {
        this.copasst = copasst;
    }

    public LocalDate getFecha_copasst() {
        return fecha_copasst;
    }

    public void setFecha_copasst(LocalDate fecha_copasst) {
        this.fecha_copasst = fecha_copasst;
    }

    public Integer getVcd_prof() {
        return vcd_prof;
    }

    public void setVcd_prof(Integer vcd_prof) {
        this.vcd_prof = vcd_prof;
    }

    public Integer getVca_prof() {
        return vca_prof;
    }

    public void setVca_prof(Integer vca_prof) {
        this.vca_prof = vca_prof;
    }

    public LocalDate getFecha_vcdprof() {
        return fecha_vcdprof;
    }

    public void setFecha_vcdprof(LocalDate fecha_vcdprof) {
        this.fecha_vcdprof = fecha_vcdprof;
    }

    public LocalDate getFecha_vcaprof() {
        return fecha_vcaprof;
    }

    public void setFecha_vcaprof(LocalDate fecha_vcaprof) {
        this.fecha_vcaprof = fecha_vcaprof;
    }
}
