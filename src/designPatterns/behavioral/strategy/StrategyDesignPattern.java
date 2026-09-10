package designPatterns.behavioral.strategy;

interface PaymentStrategy {
    void pay();
}

class UPIPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("UPI Payment");
    }
}

class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("Credit Card Payment");
    }
}


class PaymentContext {
    PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay() {
        paymentStrategy.pay();
    }
}


public class StrategyDesignPattern {
    static void main() {
        PaymentContext context = new PaymentContext(new  UPIPaymentStrategy());
        context.pay();
    }
}
