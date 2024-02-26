package deliveryservice.service;

import deliveryservice.entity.Order;
import deliveryservice.entity.User;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Alex
 */
public class UserService {
    
    public static List<Long> usersToIds(List<User> users) {
        return users.stream().map(user -> user.getId()).collect(Collectors.toList());
    }
    
    public static List<User> getUsersWithoutOrders(List<User> users) {
        return users.stream().filter(user -> user.getOrders().isEmpty()).collect(Collectors.toList());
    }

    
    public static List<Order> getAndSortAllOrdersOfUsers(List<User> users) {
        return users.stream().map(User::getOrders)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Order::getPrice))
                .collect(Collectors.toList());
    }
    
    public static Map<Integer, List<Order>> groupOrdersByPrice(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getPrice))
                .collect(Collectors.groupingBy(Order::getPrice));
    }
    
    public static boolean checkIfOrdersAreExpensive(User user, int minPrice) {
        return user.getOrders().stream().allMatch(x -> x.getPrice() > minPrice);
    }
    
    public static boolean hasAnyExpensiveOrder(User user, int minPrice) {
        return user.getOrders().stream().anyMatch(x -> x.getPrice() > minPrice);
    }
    
    public static void filterSimilarAddressesAndPrint(List<User> users) {
        users.stream().map(User::getDeliveryAddresses)
                .flatMap(Collection::stream).map(x -> x.toString()).distinct().forEach(System.out::println);
    }
    
    public static int getTheMostExpensiveOrderPrice(List<Order> orders) {
        return orders.stream().map(Order::getPrice).max(Integer::compare).get();
    }

    public static Order getTheMostExpensiveOrder(List<Order> orders) {
        return orders.stream().max(Order::compare).get();
    }
    
    public static double getAveragePriceOfOrders(List<Order> orders) {
        return orders.stream().mapToInt(Order::getPrice).average().getAsDouble();
    }
}
