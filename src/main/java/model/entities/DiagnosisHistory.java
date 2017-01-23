package model.entities;

import java.sql.Timestamp;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class DiagnosisHistory {

    private int id;
    private Timestamp date;
    private int patientId;
    private Staff staff;
    private Diagnosis diagnosis;
    private Type type;

    public enum Type {
        PRIMARY, FINAL
    }

    public static class Builder {
        private int id;
        private Timestamp date;
        private int patientId;
        private Staff staff;
        private Diagnosis diagnosis;
        private Type type;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setDate(Timestamp date) {
            this.date = date;
            return this;
        }

        public Builder setPatientId(int patientId) {
            this.patientId = patientId;
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

        public Builder setType(Type type) {
            this.type = type;
            return this;
        }

        public DiagnosisHistory build() {
            DiagnosisHistory diagnosisHistory = new DiagnosisHistory();
            diagnosisHistory.setId(id);
            diagnosisHistory.setDate(date);
            diagnosisHistory.setPatientId(patientId);
            diagnosisHistory.setStaff(staff);
            diagnosisHistory.setDiagnosis(diagnosis);
            diagnosisHistory.setType(type);
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

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
