package com.example.demo.service;

import com.example.demo.config.KafkaConfig;
import com.example.demo.models.Pedido;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.TopicPartitionOffset;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
    public Flux<Pedido> obtenerPedidosDesdeKafka(String topico) {
        ConsumerRecords<String, String> pedidosRecibidos;
        KafkaConfig kafkaConfig=new KafkaConfig();
        kafkaTemplate.setConsumerFactory(kafkaConfig.consumerFactory());
        pedidosRecibidos=kafkaTemplate.receive(Arrays.asList(new TopicPartitionOffset(topico,0)));

        return convertirPedidosDesdeKafka(pedidosRecibidos);
    }

    private Flux<Pedido> convertirPedidosDesdeKafka(ConsumerRecords<String, String> pedidosRecibidos) {
        List<Pedido> pedidos=new LinkedList<>();

        for (ConsumerRecord<String, String> consumerRecord:pedidosRecibidos) {
           pedidos.add(Pedido.StringToPedido(consumerRecord.value())) ;

        }
        return (Flux<Pedido>) pedidos;
    }
}
