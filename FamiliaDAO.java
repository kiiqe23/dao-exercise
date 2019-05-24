package edu.udg.mx.sacb.dao;

import edu.udg.mx.sacb.data.model.Familia;
import java.util.List;

public interface FamiliaDAO {

    void create(Familia familia);

    void edit(Familia familia);

    void remove(Familia familia);

    Familia find(Object id);

    List<Familia> findAll();

    Familia findByDescripcionFamilia(String descripcionFamilia);
}
