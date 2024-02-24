package deliveryservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Order implements Comparable<Order> {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    
    @Override
    public int compareTo(Order order) {
        return price - order.price;
    }
    
    public static int compare(Order order1, Order order2) {
        return order1.compareTo(order2);
    }
}
