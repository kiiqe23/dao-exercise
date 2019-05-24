package edu.udg.mx.sacb.dao;

import edu.udg.mx.sacb.data.model.Familia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FamiliaImplDAO extends DAOFactory<Familia> implements FamiliaDAO {

    private static final String SQL_INSERT
            = "";
    private static final String SQL_UPDATE
            = "";
    private static final String SQL_DELETE
            = "";
    private static final String SQL_ALL
            = " SELECT ftv_familia_id,ftv_familia_familia,ftv_familia_descripcion,ftv_familia_mov_usuario,ftv_familia_mov_fecha,ftv_familia_mov_ip "
            + " FROM coresiia.ftv_familia order by ftv_familia_id";
    private static final String SQL_FIND_BY_DESCRIPCION
            = " SELECT ftv_familia_id,ftv_familia_familia,ftv_familia_descripcion,ftv_familia_mov_usuario,ftv_familia_mov_fecha,ftv_familia_mov_ip "
            + " FROM coresiia.ftv_familia WHERE ftv_familia_descripcion = :ftv_familia_descripcion";
    private static final String SQL_FIND_BY_ID
            = " SELECT ftv_familia_id,ftv_familia_familia,ftv_familia_descripcion,ftv_familia_mov_usuario,ftv_familia_mov_fecha,ftv_familia_mov_ip "
            + " FROM coresiia.ftv_familia WHERE ftv_familia_id = :ftv_familia_id";

    public FamiliaImplDAO() {
        super(SQL_ALL, SQL_INSERT, SQL_UPDATE, SQL_DELETE);
    }

    @Override
    Map<String, Object> convertObjToParam(Familia object) {
        Map<String, Object> params = new HashMap<>();
        params.put("ftv_familia_id", object.getFtvFamiliaId());
        params.put("ftv_familia_familia", object.getFtvFamiliaFamilia());
        params.put("ftv_familia_descripcion", object.getFtvFamiliaDescripcion());
        return params;
    }

    @Override
    Familia convertDbToOjb(ResultSet resultSet) throws SQLException {
        Familia unity = new Familia();
        unity.setFtvFamiliaId(resultSet.getLong("ftv_familia_id"));
        unity.setFtvFamiliaFamilia(resultSet.getString("ftv_familia_familia"));
        unity.setFtvFamiliaFamilia(resultSet.getString("ftv_familia_descripcion"));
        return unity;
    }

    @Override
    public void create(Familia familia) {
        insert(familia);
    }

    @Override
    public void edit(Familia familia) {
        update(familia);
    }

    @Override
    public void remove(Familia familia) {
        delete(familia);
    }

    @Override
    public Familia find(Object id) {
        return find(SQL_FIND_BY_ID, id);
    }

    @Override
    public Familia findByDescripcionFamilia(String descripcionFamilia) {
        return find(SQL_FIND_BY_DESCRIPCION, descripcionFamilia);
    }
}
