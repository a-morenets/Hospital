package model.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class DiagnosisHistory {

    private int id;
    private Timestamp date;
    private int patientId;
    private int staffId;
    private int diagnosisId;
    private Type type;

    public enum Type {
        PRIMARY, FINAL
    }

    public static class Builder {
        private int id;
        private Timestamp date;
        private int patientId;
        private int staffId;
        private int diagnosisId;
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

        public Builder setStaffId(int staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setDiagnosisId(int diagnosisId) {
            this.diagnosisId = diagnosisId;
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
            diagnosisHistory.setStaffId(staffId);
            diagnosisHistory.setDiagnosisId(diagnosisId);
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

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
