package br.com.fabricio.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FraudDetectorService {

    private final KafkaDispatcher<Order> orderKafkaDispatcher = new KafkaDispatcher<>();

    public static void main(String[] args) {

        var fraudService = new FraudDetectorService();
        try (var service = new KafkaService<>(FraudDetectorService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER", fraudService::parse, Order.class, Map.of())) {
            service.run();
        }
    }



    private void parse(ConsumerRecord<String, Order> record) throws ExecutionException, InterruptedException {
        System.out.println("-------------------------------------------");
        System.out.println("Processing new order, checking for fraud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        var order = record.value();
        if (isFraud(order)) {
            //pretending that fraud happens when the amount is >= 4500
            System.out.println("Order is a fraud!!!");
            orderKafkaDispatcher.send("ECOMMERCE_ORDER_REJECTED", order.getUserId(), order);
        } else {
            System.out.println("Approved: " + order);
            orderKafkaDispatcher.send("ECOMMERCE_ORDER_APPROVED", order.getUserId(), order);
        }

        System.out.println("Order processed");
    }

    private boolean isFraud(Order order) {
        return order.getAmount().compareTo(new BigDecimal("4500")) >= 0;
    }
}
