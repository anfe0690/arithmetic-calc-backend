package io.github.anfe0690.arithcalcbackend.v1.operation;

public class OperationDto {

    public Type type;

    public String symbol;

    public Arity arity;

    public int cost;

    public int firstOperand;

    public int secondOperand;

    public OperationDto() {
    }

    public OperationDto(OperationEntity entity) {
        this.type = entity.getType();
        this.arity = entity.getType().getArity();
        this.symbol = entity.getType().getSymbol();
        this.cost = entity.getCost();
    }
}
