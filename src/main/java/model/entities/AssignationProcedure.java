package model.entities;

/**
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public class AssignationProcedure {
    private int id;
    private int diagnosisHistoryId;
    private Procedure procedure;
    private int numDays;

    public static class Builder {
        private int id;
        private int diagnosisHistoryId;
        private Procedure procedure;
        private int numDays;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setDiagnosisHistoryId(int diagnosisHistoryId) {
            this.diagnosisHistoryId = diagnosisHistoryId;
            return this;
        }

        public Builder setProcedure(Procedure procedure) {
            this.procedure = procedure;
            return this;
        }

        public Builder setNumDays(int numDays) {
            this.numDays = numDays;
            return this;
        }

        public AssignationProcedure build() {
            AssignationProcedure assignationProcedure = new AssignationProcedure();
            assignationProcedure.setId(id);
            assignationProcedure.setProcedure(procedure);
            assignationProcedure.setDiagnosisHistoryId(diagnosisHistoryId);
            assignationProcedure.setNumDays(numDays);
            return assignationProcedure;
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

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }
}
