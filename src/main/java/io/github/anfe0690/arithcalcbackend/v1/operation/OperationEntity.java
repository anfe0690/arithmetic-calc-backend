package io.github.anfe0690.arithcalcbackend.v1.operation;

import javax.persistence.*;

@Entity(name = "operation_entity")
public class OperationEntity {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private int cost;

    public OperationEntity() {
    }

    public OperationEntity(Long id, Type type, int cost) {
        this.id = id;
        this.type = type;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
