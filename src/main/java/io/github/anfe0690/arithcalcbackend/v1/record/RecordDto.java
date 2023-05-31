package io.github.anfe0690.arithcalcbackend.v1.record;

import io.github.anfe0690.arithcalcbackend.v1.operation.Type;

public class RecordDto {

    public long id;

    public Type type;

    public int amount;

    public int balance;

    public String result;

    public String date;

    public RecordDto() {
    }

    public RecordDto(RecordEntity entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.amount = entity.getAmount();
        this.balance = entity.getUserBalance();
        this.result = entity.getOperationResponse();
        this.date = entity.getDate().toString();
    }
}
