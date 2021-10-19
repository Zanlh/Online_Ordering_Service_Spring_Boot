package csci318.demo.service;

/* This class computes a stream of brand quantities
 * and creates a state store for interactive queries.
 */

import csci318.demo.model.Appliance;
import csci318.demo.model.BrandQuantity;
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

import java.util.function.Function;

@Configuration
public class ApplianceStreamProcessing {

    public final static String STATE_STORE = "my-store";

    @Bean
    public Function<KStream<?, Appliance>, KStream<String, BrandQuantity>> process() {
        return inputStream -> {
            KTable<String, Long> brandKTable = inputStream.
                    mapValues(Appliance::getBrand).
                    groupBy((keyIgnored, value) -> value).
                    count(
                            Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as(STATE_STORE).
                                    withKeySerde(Serdes.String()).
                                    withValueSerde(Serdes.Long())
                    );
            KStream<String, BrandQuantity> brandQuantityStream = brandKTable.
                    toStream().
                    map((k, v) -> KeyValue.pair(k, new BrandQuantity(k, v)));
            // use the following code for testing
            brandQuantityStream.print(Printed.<String, BrandQuantity>toSysOut().withLabel("Console Output"));

            return brandQuantityStream;
        };
    }
}
