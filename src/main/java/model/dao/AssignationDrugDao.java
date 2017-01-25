package model.dao;

import model.entities.AssignationDrug;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public interface AssignationDrugDao extends GenericDao<model.entities.AssignationDrug> {
    List<AssignationDrug> findByDiagnosisHistoryId(int diagnosisHistoryId);
}
