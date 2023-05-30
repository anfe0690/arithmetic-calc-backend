package io.github.anfe0690.arithcalcbackend.v1.operation;

public enum Type {
    ADDITION("+", Arity.BINARY),
    SUBTRACTION("-", Arity.BINARY),
    MULTIPLICATION("*", Arity.BINARY),
    DIVISION("/", Arity.BINARY),
    SQUARE_ROOT("√", Arity.UNARY),
    RANDOM_STRING(null, Arity.NULLARY);

    private final String symbol;

    private final Arity arity;

    Type(String symbol, Arity arity) {
        this.symbol = symbol;
        this.arity = arity;
    }

    public String getSymbol() {
        return symbol;
    }

    public Arity getArity() {
        return arity;
    }
}
