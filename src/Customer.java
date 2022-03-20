/**
 * Customer record.
 * @param ccNum             Credit card number of the customer.
 * @param firstName         First name of the customer.
 * @param lastName          Last name of the customer.
 * @param email             Email of the customer.
 * @param address           Address of the customer.
 * @param city              City of the customer.
 * @param state             State of the customer.
 * @param postalCode        Postal code of the customer.
 * @param balance           Balance of the customer.
 */
public record Customer(String ccNum, String firstName, String lastName, String email,String address, String city,
                       String state, String postalCode, String balance) { }
