package deliveryservice;

import deliveryservice.entity.Address;
import deliveryservice.entity.Order;
import deliveryservice.entity.User;
import deliveryservice.service.UserService;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Alex
 */
public class Main {
    
    public static List<User> seedUsers() {
        User user1 = new User(1L, "Alex", "Coleman",
                Arrays.asList(new Address (1L, "USA", "Chicago", "Farland Street", "2579", "60605")),
                Arrays.asList(new Order(1L, "Order 1", "Order description", 2200)));
        User user2 = new User(2L, "Jessica", "Simmons",
                Arrays.asList(new Address (2L, "USA", "Phoenix", "Burnside Court", "3196", "85016")),
                Arrays.asList(new Order(2L, "Order 2", "Order description", 1760)));
        User user3 = new User(3L, "Gordon", "Stewart",
                Arrays.asList(new Address (3L, "USA", "Renton", "Ryder Avenue", "3278", "98055")),
                Arrays.asList(new Order(3L, "Order 3", "Order description", 1148)));
        User user4 = new User(4L, "Ann", "Lewis",
                Arrays.asList(new Address (4L, "USA", "Los Angeles", "Edsel Road", "4592", "90017")),
                Arrays.asList(new Order(4L, "Order 4", "Order description", 3250), new Order(5L, "Order 5", "Order description", 3390)));
        User user5 = new User(5L, "Douglas", "Hampton",
                Arrays.asList(new Address (5L, "USA", "Boston", "Smith Street", "2972", "02110")),
                Arrays.asList());
        User user6 = new User(6L, "Mary", "Stewart",
                Arrays.asList(user3.getDeliveryAddresses().get(0)),
                Arrays.asList());
        User user7 = new User(7L, "Elisabeth", "Coleman",
                Arrays.asList(user1.getDeliveryAddresses().get(0)),
                Arrays.asList(new Order(6L, "Order 6", "Order description", 1550)));
        return Arrays.asList(user1, user2, user3, user4, user5, user6, user7);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<User> users = Main.seedUsers();
        
        System.out.print("User IDs: ");
        List<Long> ids = UserService.usersToIds(users);
        ids.stream().forEach(x -> System.out.print(String.format("%d ", x)));
        System.out.println();
        System.out.println();
        
        System.out.println("Users with no orders:");
        List<User> usersWithNoOrders = UserService.getUsersWithoutOrders(users);
        usersWithNoOrders.stream().map(x -> x.getFirstName() + " " + x.getLastName()).forEach(System.out::println);
        System.out.println();
        
        System.out.println("Orders sorted by price");
        List<Order> sortedOrders = UserService.getAndSortAllOrdersOfUsers(users);
        sortedOrders.stream().forEach(x -> System.out.println(String.format("%d %s %d", x.getId(), x.getName(), x.getPrice())));
        System.out.println();
        
        System.out.println("List of users with expensive orders:");
        users.stream().filter(user -> !user.getOrders().isEmpty())
                .filter(user -> UserService.checkIfOrdersAreExpensive(user, 3000))
                .map(x -> x.getFirstName() + " " + x.getLastName())
                .forEach(System.out::println);
        System.out.println();
        
        int testUserIndex = 4;
        List<Order> userOrders = users.get(testUserIndex).getOrders();
        System.out.print("The most expensive order price of " + users.get(testUserIndex).getFirstName() + " " + users.get(testUserIndex).getLastName() + ": ");
        if (!userOrders.isEmpty()) {
            System.out.println(UserService.getTheMostExpensiveOrderPrice(userOrders));
        } else {
            System.out.println("0");
        }
        System.out.println();
        
        System.out.print("The most expensive order ID of " + users.get(testUserIndex).getFirstName() + " " + users.get(testUserIndex).getLastName() + ": ");
        if (!userOrders.isEmpty()) {
            Order order = UserService.getTheMostExpensiveOrder(userOrders);
            System.out.println(order != null ? order.getId() : "null");
        } else {
            System.out.println("null");
        }
        System.out.println();
        
        System.out.print("The average order price of " + users.get(testUserIndex).getFirstName() + " " + users.get(testUserIndex).getLastName() + ": ");
        if (!userOrders.isEmpty()) {
            double average = UserService.getAveragePriceOfOrders(users.get(testUserIndex).getOrders());

            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

            symbols.setGroupingSeparator('\0');
            formatter.setDecimalFormatSymbols(symbols);
            System.out.println(formatter.format(average));
        } else {
            System.out.println("0");
        }
        System.out.println();
        
        System.out.println("All addresses:");
        UserService.filterSimilarAddressesAndPrint(users);
        System.out.println();
    }
    
}
