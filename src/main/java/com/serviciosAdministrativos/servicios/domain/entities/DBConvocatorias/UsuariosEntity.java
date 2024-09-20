package com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class UsuariosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cod_emp")
    private String codEmp;
    private String correo;
    private String rol;
    private String estado;

    public UsuariosEntity() {}

    public UsuariosEntity(String codEmp, String correo, String rol, String estado) {
        this.codEmp = codEmp;
        this.correo = correo;
        this.rol = rol;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(String codEmp) {
        this.codEmp = codEmp;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
