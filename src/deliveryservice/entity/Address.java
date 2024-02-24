package deliveryservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address implements Comparable<Address> {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String house;
    private String index;
    
    public boolean equals(Address addr) {
        return toString().equals(addr.toString());
    }
    
    @Override
    public String toString() {
        return index + " " + country + ", " + city + ", " + house + " " + street;
    }

    @Override
    public int compareTo(Address addr) {
        return toString().compareTo(addr.toString());
    }
}
