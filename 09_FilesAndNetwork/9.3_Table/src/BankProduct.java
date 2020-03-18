public class BankProduct {
    private String typeOfAccount;
    private String numberOfAccount;
    private String currency;
    private String dateOfOperation;
    private String reference;
    private String operationDescription;

    public BankProduct setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
        return this;
    }

    private String income;
    private String consumption;

    public BankProduct setNumberOfAccount(String numberOfAccount) {
        this.numberOfAccount = numberOfAccount;
        return this;
    }

    public BankProduct setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public BankProduct setDateOfOperation(String dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
        return this;
    }

    public BankProduct setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public BankProduct setOperationDescription(String operationDescription) {
        if (operationDescription.matches(stringSpace)) {
            this.operationDescription = operationDescription;
        } else {
            String[] operationDescriptions = operationDescription.split("\\s{3,}");
            this.operationDescription = operationDescriptions[1];
        }
        return this;
    }

    public BankProduct setIncome(String income) {
        this.income = income;
        return this;
    }

    public BankProduct setConsumption(String consumption) {
        char reg = '\"';
        if (consumption.charAt(0) == reg) {
            this.consumption = consumption.substring(1, consumption.length() - 1).replace(",", ".");
        } else this.consumption = consumption;
        return this;
    }

    static String stringSpace = "[А-Яа-я]+\\s+[А-Яа-я]+";


    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public String getConsumption() {
        return consumption;
    }

    public String getNumberOfAccount() {
        return numberOfAccount;
    }

    public String getDateOfOperation() {
        return dateOfOperation;
    }

    public String getReference() {
        return reference;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public String getIncome() {
        return income;
    }


    public String getCurrency() {
        return currency;
    }


}
