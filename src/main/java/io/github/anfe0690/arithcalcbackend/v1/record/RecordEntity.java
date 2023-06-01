package io.github.anfe0690.arithcalcbackend.v1.record;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RecordEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private long operationId;

    private String type;

    private long userId;

    private int amount;

    private int userBalance;

    private String operationResponse;

    private String date;

    public RecordEntity() {
    }

    public RecordEntity(long operationId, String type, long userId, int amount, int userBalance, String operationResponse,
                        String date) {
        this.operationId = operationId;
        this.type = type;
        this.userId = userId;
        this.amount = amount;
        this.userBalance = userBalance;
        this.operationResponse = operationResponse;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getOperationId() {
        return operationId;
    }

    public void setOperationId(long operationId) {
        this.operationId = operationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
    }

    public String getOperationResponse() {
        return operationResponse;
    }

    public void setOperationResponse(String operationResponse) {
        this.operationResponse = operationResponse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
