package edu.udg.mx.sacb.ejb;

import edu.udg.core.data.model.Message;
import edu.udg.mx.sacb.dao.FamiliaDAO;
import edu.udg.mx.sacb.dao.FamiliaImplDAO;
import edu.udg.mx.sacb.data.model.Familia;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class FamiliaEjb {

    public FamiliaEjb() {

    }

    public Message buscarID(Long id) {
        Message msj = new Message();

        try {

            Familia familia;
            FamiliaDAO dao = new FamiliaImplDAO();
            familia = dao.find(id);
            if (familia != null && familia.getFtvFamiliaId()!= null) {
                msj.setStatus(Boolean.TRUE);
                msj.setMessage("Valor encontrado");
                msj.setObject(familia);
            } else {
                msj.setStatus(Boolean.FALSE);
                msj.setMessage("Valor no encontrado");
                msj.setObject(null);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            msj.setStatus(Boolean.FALSE);
            msj.setMessage("Error no especificado. No se cargó correctamente la información.");
            msj.setObject(null);
        }

        return msj;
    }
    public Message buscarDescripcion(String descripcion) {
        Message msj = new Message();

        try {

            Familia familia;
            FamiliaDAO dao = new FamiliaImplDAO();
            familia = dao.findByDescripcionFamilia(descripcion);
            if (familia != null && familia.getFtvFamiliaId()!= null) {
                msj.setStatus(Boolean.TRUE);
                msj.setMessage("Valor encontrado");
                msj.setObject(familia);
            } else {
                msj.setStatus(Boolean.FALSE);
                msj.setMessage("Valor no encontrado");
                msj.setObject(null);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            msj.setStatus(Boolean.FALSE);
            msj.setMessage("Error no especificado. No se cargó correctamente la información.");
            msj.setObject(null);
        }

        return msj;
    }
    public Message buscarTodos() {
        Message msj = new Message();

        try {

            List<Familia> familias;
            FamiliaDAO dao = new FamiliaImplDAO();
            familias = dao.findAll();
            if (familias != null && !familias.isEmpty()) {
                msj.setStatus(Boolean.TRUE);
                msj.setMessage("Valor encontrado");
                msj.setObject(familias);
            } else {
                msj.setStatus(Boolean.FALSE);
                msj.setMessage("Valor no encontrado");
                msj.setObject(null);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            msj.setStatus(Boolean.FALSE);
            msj.setMessage("Error no especificado. No se cargó correctamente la información.");
            msj.setObject(null);
        }

        return msj;
    }

}
