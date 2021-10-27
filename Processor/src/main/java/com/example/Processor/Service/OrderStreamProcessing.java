package com.example.Processor.Service;

import java.io.InputStream;
import java.util.function.Function;

import com.example.Processor.Model.Order;
import com.example.Processor.Model.ProductQuantity;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderStreamProcessing {

    public final static String STATE_STORE = "product-store";

    @Bean
    public Function<KStream<?, Order>, KStream<String, ProductQuantity>> process(){
        return inputStream -> {

            KTable<String, Long> orderKTable = inputStream.
            mapValues(Order::getProduct).
            groupBy((keyIgnored,value)-> value).
            count(
                    Materialized.<String, Long ,KeyValueStore<Bytes,byte[]>>as("product-store").
                    withKeySerde(Serdes.String()).
                    withValueSerde(Serdes.Long())
            );
            KStream<String, ProductQuantity> orderQuantityStream = orderKTable.
            toStream().
            map((k, v) -> KeyValue.pair(k, new ProductQuantity(k, v)));
            
            orderQuantityStream.print(Printed.<String, ProductQuantity>toSysOut().withLabel("Console Output"));

            return orderQuantityStream;
        };
    }
    
}
