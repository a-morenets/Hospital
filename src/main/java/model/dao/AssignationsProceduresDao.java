package model.dao;

import model.entities.AssignationsProcedures;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public interface AssignationsProceduresDao extends GenericDao<AssignationsProcedures> {
    List<AssignationsProcedures> findByDiagnosisHistoryId(int diagnosisHistoryId);
}
