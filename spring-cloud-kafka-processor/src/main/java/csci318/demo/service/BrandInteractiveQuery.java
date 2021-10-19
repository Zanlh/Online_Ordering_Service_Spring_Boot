package csci318.demo.service;

import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BrandInteractiveQuery {

    private InteractiveQueryService interactiveQueryService;

    //@Autowired
    public BrandInteractiveQuery(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    public long getBrandQuantity(String brandName) {
        if (keyValueStore().get(brandName) != null) {
            return keyValueStore().get(brandName);
            //long quantity = keyValueStore().get(brandName);
            //return new BrandQuantity(brandName, quantity);
        } else {
            throw new NoSuchElementException(); //TODO: should use a customised exception.
        }
    }

    public List<String> getBrandNames() {
        List<String> brandList = new ArrayList<>();
        KeyValueIterator<String, Long> all = keyValueStore().all();
        while (all.hasNext()) {
            String next = all.next().key;
            brandList.add(next);
        }
        return brandList;
    }

    private ReadOnlyKeyValueStore<String, Long> keyValueStore() {
        return this.interactiveQueryService.getQueryableStore(ApplianceStreamProcessing.STATE_STORE,
                QueryableStoreTypes.keyValueStore());
    }

}
