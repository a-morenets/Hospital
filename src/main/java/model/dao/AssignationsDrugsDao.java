package model.dao;

import model.entities.AssignationsDrugs;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public interface AssignationsDrugsDao extends GenericDao<AssignationsDrugs> {
    List<AssignationsDrugs> findByDiagnosisHistoryId(int diagnosisHistoryId);
}
