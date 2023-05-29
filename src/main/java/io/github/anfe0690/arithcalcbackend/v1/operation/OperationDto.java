package io.github.anfe0690.arithcalcbackend.v1.operation;

public class OperationDto {

    public Type type;

    public int cost;

    public OperationDto() {
    }

    public OperationDto(OperationEntity entity) {
        this.type = entity.getType();
        this.cost = entity.getCost();
    }
}
