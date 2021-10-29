package com.example.Processor.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.Processor.Model.Customer;

import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.aspectj.lang.reflect.NoSuchAdviceException;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Service;

@Service
public class OrderInteractiveQuery {

    private final InteractiveQueryService interactiveQueryService;


    public OrderInteractiveQuery(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    public long getOrderQuantity(String productName){
        if(productStore().get(productName)!= null){
            return productStore().get(productName);
        }else{
            throw new NoSuchElementException();
        }
    }

    public List<String> getOrderList(){
        List<String> orderList = new ArrayList<>();
        KeyValueIterator<String,Long> all = productStore().all();
        while(all.hasNext()){
            String next = all.next().key;
            orderList.add(next);
        }
        return orderList;
    }

    public List<String> getProductListByCustomer(Long id){
        List<String> productList = new ArrayList<>();
        KeyValueIterator<String, Customer> all = customerStore().all();
        while(all.hasNext()){
            Customer customer = all.next().value;
            Long customer_id = customer.getId();
            String product_name = customer.getProduct();
            if(customer_id.equals(id)){
                productList.add(product_name);
            }
        }
        return productList;

    }


    private ReadOnlyKeyValueStore<String,Long> productStore(){
        return this.interactiveQueryService.getQueryableStore(OrderStreamProcessing.PRODUCT_STATE_STORE,
                QueryableStoreTypes.keyValueStore());
    }

    private ReadOnlyKeyValueStore<String, Customer> customerStore(){
        return this.interactiveQueryService.getQueryableStore(OrderStreamProcessing.CUSTOMER_STATE_STORE, 
        QueryableStoreTypes.keyValueStore());
    }
    
}
