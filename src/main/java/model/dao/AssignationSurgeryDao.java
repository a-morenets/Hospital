package model.dao;

import model.entities.AssignationSurgery;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public interface AssignationSurgeryDao extends GenericDao<model.entities.AssignationSurgery> {
    List<AssignationSurgery> findByDiagnosisHistoryId(int diagnosisHistoryId);
}
