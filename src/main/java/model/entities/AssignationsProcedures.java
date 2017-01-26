package model.entities;

/**
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public class AssignationsProcedures {
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

        public AssignationsProcedures build() {
            AssignationsProcedures assignationsProcedures = new AssignationsProcedures();
            assignationsProcedures.setId(id);
            assignationsProcedures.setProcedure(procedure);
            assignationsProcedures.setDiagnosisHistoryId(diagnosisHistoryId);
            assignationsProcedures.setNumDays(numDays);
            return assignationsProcedures;
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
