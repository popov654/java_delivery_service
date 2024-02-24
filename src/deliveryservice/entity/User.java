package deliveryservice.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Address> deliveryAddresses;
    private List<Order> orders;
}

