package model.entities;

/**
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public class AssignationSurgery {
    private int id;
    private int diagnosisHistoryId;
    private Surgery surgery;

    public static class Builder {
        private int id;
        private int diagnosisHistoryId;
        private Surgery surgery;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setDiagnosisHistoryId(int diagnosisHistoryId) {
            this.diagnosisHistoryId = diagnosisHistoryId;
            return this;
        }

        public Builder setSurgery(Surgery surgery) {
            this.surgery = surgery;
            return this;
        }

        public AssignationSurgery build() {
            AssignationSurgery assignationSurgery = new AssignationSurgery();
            assignationSurgery.setId(id);
            assignationSurgery.setDiagnosisHistoryId(diagnosisHistoryId);
            assignationSurgery.setSurgery(surgery);
            return assignationSurgery;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiagnosisHistoryId() {
        return diagnosisHistoryId;
    }

    public void setDiagnosisHistoryId(int diagnosisHistoryId) {
        this.diagnosisHistoryId = diagnosisHistoryId;
    }

    public Surgery getSurgery() {
        return surgery;
    }

    public void setSurgery(Surgery surgery) {
        this.surgery = surgery;
    }
}
