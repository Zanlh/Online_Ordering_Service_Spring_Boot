package com.example.Processor.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.example.Processor.Model.Customer;
import com.example.Processor.Model.Order;
import com.example.Processor.Model.ProductQuantity;
import com.example.Processor.Model.TotalAmount;

import org.apache.kafka.common.metrics.stats.Total;
import org.apache.kafka.common.serialization.Serde;
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
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class OrderStreamProcessing {

    public final static String PRODUCT_STATE_STORE = "product-store";
    public final static String CUSTOMER_STATE_STORE = "customer-store";
    public final static String TOTAL_STATE_STORE = "total-store";

    @Bean
    public Function<KStream<?, Order>, KStream<String, ProductQuantity>> process(){
        return inputStream -> {

            inputStream.map((k,v) -> {
                String product_name = v.getProduct();
                Long customer_id = v.getSupplier();
                Customer customer = new Customer();
                customer.setId(customer_id);
                customer.setProduct(product_name);
                String new_key =customer_id+product_name;

                return KeyValue.pair(new_key, customer);

            }).toTable(
                Materialized.<String, Customer, KeyValueStore<Bytes, byte[]>>as(CUSTOMER_STATE_STORE).
                withKeySerde(Serdes.String()).
                // a custom value serde for this state store
                withValueSerde(customerSerde())
            );

            inputStream.map((k,v) ->{
                Long customer_id = v.getSupplier();
                int quantity =+ v.getQuantity();
                int price = Integer.parseInt(v.getPrice());
                int amount =+ quantity*price;
                TotalAmount totalAmount = new TotalAmount();
                totalAmount.setId(customer_id);
                totalAmount.setTotalAmount(amount);
                String new_key = customer_id + String.valueOf(amount);
                return KeyValue.pair(new_key,totalAmount);
            }).toTable(
                Materialized.<String, TotalAmount, KeyValueStore<Bytes, byte[]>>as(TOTAL_STATE_STORE).
                withKeySerde(Serdes.String()).
                // a custom value serde for this state store
                withValueSerde(totalAmountSerde())
            );




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

    public Serde<Customer> customerSerde(){
        final JsonSerde<Customer> customerJsonSerde = new JsonSerde<>();
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.Processor.Model.Customer");
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        customerJsonSerde.configure(configProps, false);
        return customerJsonSerde;
        
    }

    public Serde<TotalAmount> totalAmountSerde(){
        final JsonSerde<TotalAmount> totalAmountJsonSerde = new JsonSerde<>();
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.Processor.Model.TotalAmount");
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        totalAmountJsonSerde.configure(configProps, false);
        return totalAmountJsonSerde;
        
    }
    
}
