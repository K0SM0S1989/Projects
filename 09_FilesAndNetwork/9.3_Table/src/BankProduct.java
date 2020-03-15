public class BankProduct {
    private String typeOfAccount;
    private String numberOfAccount;
    private String currency;
    private String dateOfOperation;
    private String reference;
    private String operationDescription;
    private String income;
    private String consumption;

    static String stringSpace = "[А-Яа-я]+\\s+[А-Яа-я]+";


    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getNumberOfAccount() {
        return numberOfAccount;
    }

    public void setNumberOfAccount(String numberOfAccount) {
        this.numberOfAccount = numberOfAccount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(String dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        if (operationDescription.matches(stringSpace)) {
            this.operationDescription = operationDescription;
        } else {
            String[] operationDescriptions = operationDescription.split("\\s{3,}");
            this.operationDescription = operationDescriptions[1];
        }

    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {

        this.income = income;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        char reg = '\"';
        if (consumption.charAt(0) == reg) {
            this.consumption = consumption.substring(1, consumption.length() - 1).replace(",", ".");
        } else this.consumption = consumption;

    }


}
