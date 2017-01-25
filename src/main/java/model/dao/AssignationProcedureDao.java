package model.dao;

import model.entities.AssignationProcedure;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public interface AssignationProcedureDao extends GenericDao<model.entities.AssignationProcedure> {
    List<AssignationProcedure> findByDiagnosisHistoryId(int diagnosisHistoryId);
}
