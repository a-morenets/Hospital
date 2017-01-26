package model.dao;

import model.entities.AssignationsSurgeries;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public interface AssignationsSurgeriesDao extends GenericDao<AssignationsSurgeries> {
    List<AssignationsSurgeries> findByDiagnosisHistoryId(int diagnosisHistoryId);
}
