package schemas;

public class Transaction {
    private String clientName;
    private String clientId;
    private Double amount;
    private Operation operation;
    private String targetClientId;

    public static enum Operation {
		DEPOSIT,
		WITHDRAW,
        TRANSFER
	}

    public Transaction(String clientName, String clientId, Double amount, Operation operation, String targetClientId) {
        this.clientName = clientName;
        this.clientId = clientId;
        this.amount = amount;
        this.operation = operation;
        this.targetClientId = targetClientId;
    }

    @Override
    public String toString() {
        if (operation.equals(Operation.TRANSFER)) {
            return "Transaction{" +
                "clientName='" + clientName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", amount=" + amount +
                ", operation='" + operation + '\'' +
                ", targetClientId='" + targetClientId + '\'' +
                '}';
        }

        return "Transaction{" +
                "clientName='" + clientName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", amount=" + amount +
                ", operation='" + operation + '\'' +
                '}';
    }
}
