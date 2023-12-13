package com.example.demo.service;

import com.example.demo.config.KafkaConfig;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class PostresServiceKafkaConsumer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    public String obtenerPedidoKafka(String topico) {
        ConsumerRecord<String, String> pedidoRecibido;
        KafkaConfig kafkaConfig=new KafkaConfig();
        kafkaTemplate.setConsumerFactory(kafkaConfig.consumerFactory());
        pedidoRecibido=kafkaTemplate.receive(topico,0,0);

        return Objects.requireNonNull(pedidoRecibido.value());
    }
}
