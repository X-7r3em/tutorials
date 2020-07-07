package org.example.objectmapper;

public class Dto {
    private String transactionId;
    private Object body;

    public Dto() {
    }

    public Dto(String transactionId, Object body) {
        this.transactionId = transactionId;
        this.body = body;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
