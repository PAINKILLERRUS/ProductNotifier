package by.developing.example.productmmcroservice.service;

import by.developing.example.eventcore.ProductCreatedEvent;
import by.developing.example.productmmcroservice.service.dto.CreateProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements ProductService {

    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductDTO dto) {
        //TODO save DB
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, dto.getTitle(), dto.getPrice(), dto.getQuantity());

        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate
                .send("product-created-events-topic", productId, productCreatedEvent);//отправка в асинхронном режиме

        future.whenComplete((result, exception) -> {
            if (exception != null) {
                LOGGER.error("Failed to send message: {}", exception.getMessage());
            } else {
                LOGGER.info("Message sent successfully: {}", result.getRecordMetadata());
            }
        });
        LOGGER.info("Return: {}", productId);//проверка работы асинхронного режима

        return productId;
    }

//    @Override
//    @SneakyThrows
//    public String createProduct(CreateProductDTO dto) { //синхронный вариант
//        //TODO save DB
//        String productId = UUID.randomUUID().toString();
//
//        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, dto.getTitle(), dto.getPrice(), dto.getQuantity());
//
//        SendResult<String, ProductCreatedEvent> result = kafkaTemplate
//                .send("product-created-events-topic", productId, productCreatedEvent).get();
//
//        LOGGER.info("Topic: {}", result.getRecordMetadata().topic());
//        LOGGER.info("Partition: {}", result.getRecordMetadata().partition());
//        LOGGER.info("Offset: {}", result.getRecordMetadata().offset());
//        LOGGER.info("Return: {}", productId);
//
//        return productId;
//    }
}
