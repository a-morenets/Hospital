package model.entities;

/**
 * AssignationsSurgeries
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public class AssignationsSurgeries {
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

        public AssignationsSurgeries build() {
            AssignationsSurgeries assignationsSurgeries = new AssignationsSurgeries();
            assignationsSurgeries.setId(id);
            assignationsSurgeries.setDiagnosisHistoryId(diagnosisHistoryId);
            assignationsSurgeries.setSurgery(surgery);
            return assignationsSurgeries;
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
