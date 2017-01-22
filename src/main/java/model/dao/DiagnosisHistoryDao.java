package model.dao;

import model.entities.DiagnosisHistory;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public interface DiagnosisHistoryDao extends GenericDao<DiagnosisHistory> {
    List<DiagnosisHistory> getDiagnosisHistoryByPatientIdList(int patientId);
}
