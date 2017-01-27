package model.entities;

import java.sql.Timestamp;

/**
 * DiagnosisHistory
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class DiagnosisHistory {

    private int id;
    private Timestamp date;
    private Patient patient;
    private Staff staff;
    private Diagnosis diagnosis;
    private DiagnosisType diagnosisType;

    public static class Builder {
        private int id;
        private Timestamp date;
        private Patient patient;
        private Staff staff;
        private Diagnosis diagnosis;
        private DiagnosisType diagnosisType;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setDate(Timestamp date) {
            this.date = date;
            return this;
        }

        public Builder setPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public Builder setStaff(Staff staff) {
            this.staff = staff;
            return this;
        }

        public Builder setDiagnosis(Diagnosis diagnosis) {
            this.diagnosis = diagnosis;
            return this;
        }

        public Builder setDiagnosisType(DiagnosisType diagnosisType) {
            this.diagnosisType = diagnosisType;
            return this;
        }

        public DiagnosisHistory build() {
            DiagnosisHistory diagnosisHistory = new DiagnosisHistory();
            diagnosisHistory.setId(id);
            diagnosisHistory.setDate(date);
            diagnosisHistory.setPatient(patient);
            diagnosisHistory.setStaff(staff);
            diagnosisHistory.setDiagnosis(diagnosis);
            diagnosisHistory.setDiagnosisType(diagnosisType);
            return diagnosisHistory;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public DiagnosisType getDiagnosisType() {
        return diagnosisType;
    }

    public void setDiagnosisType(DiagnosisType diagnosisType) {
        this.diagnosisType = diagnosisType;
    }

}
