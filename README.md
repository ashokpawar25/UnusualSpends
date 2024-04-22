# UnusualSpends (Problem statement)

You work at a credit card company and as a value-add they want to start providing alerts to users when their spending in any particular category is higher than usual.
 - Compare the total amount paid for the current month, grouped by category with the previous month
 - Filter down to the categories for which the user spent at least 50% more this month than last month
 - Compose an e-mail message to the user that lists the categories for which spending was unusually high

Sample Email -
Unusual spending of ₹1076 detected!
Hello Baburao!
We have detected unusually high spending on your card in these categories:
* You spent ₹148 on groceries
* You spent ₹928 on travel
Thanks,
The Credit Card Company

Extensions -
- Change in Email format
- Change in threshold percentage
- Change in usual spending amount calculation logic
- Adding usual spending amount in email

# domain Package - It consist domain models and domain services
## model.entity Package - It consist all the entities

# Customer
## States
- `private int id`
- `private final String name`
- `private final String email`

## Constructors
- `public Customer(int id, String name, String email)`
-  `public static Customer create(int id, String name, String email)`

## Behaviors
- `getters and setters`


