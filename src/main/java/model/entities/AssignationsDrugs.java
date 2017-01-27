package model.entities;

/**
 * AssignationsDrugs
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public class AssignationsDrugs {
    private int id;
    private int diagnosisHistoryId;
    private Drug drug;
    private int numUnits;
    private int numTimes;
    private int numDays;

    public static class Builder {
        private int id;
        private int diagnosisHistoryId;
        private Drug drug;
        private int numUnits;
        private int numTimes;
        private int numDays;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setDiagnosisHistoryId(int diagnosisHistoryId) {
            this.diagnosisHistoryId = diagnosisHistoryId;
            return this;
        }

        public Builder setDrug(Drug drug) {
            this.drug = drug;
            return this;
        }

        public Builder setNumUnits(int numUnits) {
            this.numUnits = numUnits;
            return this;
        }

        public Builder setNumTimes(int numTimes) {
            this.numTimes = numTimes;
            return this;
        }

        public Builder setNumDays(int numDays) {
            this.numDays = numDays;
            return this;
        }

        public AssignationsDrugs build() {
            AssignationsDrugs assignationsDrugs = new AssignationsDrugs();
            assignationsDrugs.setId(id);
            assignationsDrugs.setDiagnosisHistoryId(diagnosisHistoryId);
            assignationsDrugs.setDrug(drug);
            assignationsDrugs.setNumUnits(numUnits);
            assignationsDrugs.setNumTimes(numTimes);
            assignationsDrugs.setNumDays(numDays);
            return assignationsDrugs;
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

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public int getNumUnits() {
        return numUnits;
    }

    public void setNumUnits(int numUnits) {
        this.numUnits = numUnits;
    }

    public int getNumTimes() {
        return numTimes;
    }

    public void setNumTimes(int numTimes) {
        this.numTimes = numTimes;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    @Override
    public String toString() {
        return "AssignationsDrugs{" +
                "id=" + id +
                ", diagnosisHistoryId=" + diagnosisHistoryId +
                ", drug=" + drug +
                ", numUnits=" + numUnits +
                ", numTimes=" + numTimes +
                ", numDays=" + numDays +
                '}';
    }

}
