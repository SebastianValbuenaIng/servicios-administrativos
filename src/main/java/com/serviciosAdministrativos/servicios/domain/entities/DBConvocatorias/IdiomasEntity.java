package com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias;

import jakarta.persistence.*;

@Entity
@Table(name = "idiomas")
public class IdiomasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idlen;
    private String idemp;
    private String lengua;
    private String nivlec;
    private String nivhab;
    private String nivesc;
    private String blq;

    public Integer getIdlen() {
        return idlen;
    }

    public void setIdlen(Integer idlen) {
        this.idlen = idlen;
    }

    public String getIdemp() {
        return idemp;
    }

    public void setIdemp(String idemp) {
        this.idemp = idemp;
    }

    public String getLengua() {
        return lengua;
    }

    public void setLengua(String lengua) {
        this.lengua = lengua;
    }

    public String getNivlec() {
        return nivlec;
    }

    public void setNivlec(String nivlec) {
        this.nivlec = nivlec;
    }

    public String getNivhab() {
        return nivhab;
    }

    public void setNivhab(String nivhab) {
        this.nivhab = nivhab;
    }

    public String getNivesc() {
        return nivesc;
    }

    public void setNivesc(String nivesc) {
        this.nivesc = nivesc;
    }

    public String getBlq() {
        return blq;
    }

    public void setBlq(String blq) {
        this.blq = blq;
    }
}