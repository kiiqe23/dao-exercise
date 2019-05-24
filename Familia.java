package edu.udg.mx.sacb.data.model;

import java.io.Serializable;
import java.util.Date;

public class Familia implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long ftvFamiliaId;
    private String ftvFamiliaFamilia;
    private String ftvFamiliaDescripcion;
    private String ftvFamiliaMovUsuario;
    private Date ftvFamiliaMovFecha;
    private String ftvFamiliaMovIp;

    public Familia() {
    }

    public Familia(Long ftvFamiliaId) {
        this.ftvFamiliaId = ftvFamiliaId;
    }

    public Familia(Long ftvFamiliaId, String ftvFamiliaFamilia, String ftvFamiliaDescripcion) {
        this.ftvFamiliaId = ftvFamiliaId;
        this.ftvFamiliaFamilia = ftvFamiliaFamilia;
        this.ftvFamiliaDescripcion = ftvFamiliaDescripcion;
    }

    public Long getFtvFamiliaId() {
        return ftvFamiliaId;
    }

    public void setFtvFamiliaId(Long ftvFamiliaId) {
        this.ftvFamiliaId = ftvFamiliaId;
    }

    public String getFtvFamiliaFamilia() {
        return ftvFamiliaFamilia;
    }

    public void setFtvFamiliaFamilia(String ftvFamiliaFamilia) {
        this.ftvFamiliaFamilia = ftvFamiliaFamilia;
    }

    public String getFtvFamiliaDescripcion() {
        return ftvFamiliaDescripcion;
    }

    public void setFtvFamiliaDescripcion(String ftvFamiliaDescripcion) {
        this.ftvFamiliaDescripcion = ftvFamiliaDescripcion;
    }

    public String getFtvFamiliaMovUsuario() {
        return ftvFamiliaMovUsuario;
    }

    public void setFtvFamiliaMovUsuario(String ftvFamiliaMovUsuario) {
        this.ftvFamiliaMovUsuario = ftvFamiliaMovUsuario;
    }

    public Date getFtvFamiliaMovFecha() {
        return ftvFamiliaMovFecha;
    }

    public void setFtvFamiliaMovFecha(Date ftvFamiliaMovFecha) {
        this.ftvFamiliaMovFecha = ftvFamiliaMovFecha;
    }

    public String getFtvFamiliaMovIp() {
        return ftvFamiliaMovIp;
    }

    public void setFtvFamiliaMovIp(String ftvFamiliaMovIp) {
        this.ftvFamiliaMovIp = ftvFamiliaMovIp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ftvFamiliaId != null ? ftvFamiliaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Familia)) {
            return false;
        }
        Familia other = (Familia) object;
        if ((this.ftvFamiliaId == null && other.ftvFamiliaId != null) || (this.ftvFamiliaId != null && !this.ftvFamiliaId.equals(other.ftvFamiliaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.udg.cgti.ln.modelo.Familia[ftvFamiliaId=" + ftvFamiliaId + "]";
    }

}
