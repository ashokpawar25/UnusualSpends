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
## domain.model.entity Package - It consist all the entities

## Customer
### States
- `private int id`
- `private final String name`
- `private final String email`

### Constructors
- `public Customer(int id, String name, String email)`
-  `public static Customer create(int id, String name, String email)`

### Behaviors
- `getters and setters`

## CreditCard
### States
- `private int id`
- `private Customer customer`

### Constructors
- `public CreditCard(int id)`

### Behaviors
- `getters and setters`

## Transaction
### States
- `private int id`
- `private final int cardId`
- `private final double amount`
- `private final Category category`
- `private final LocalDate date`

### Constructors
- `public Transaction(int transactionId, int cardId, double amount, Category category, LocalDate date)`
- `public static Transaction create(int id, int cardId, double amount, Category category, LocalDate date)`

### Behaviors
- `getters and setters`

## model.entity.validator Package - This package having classes for the validations of entities
## EmailValidator
### Behaviors
- `public static boolean isValidEmail(String email)`
- `public static boolean isValidEmailId(String email)`
- `public static boolean isEmptyEmailId(String email)`
- `public static boolean isNullEmailId(String email)`

## NameValidator
### Behaviors
- `public static boolean isValidName(String name)`
- `public static boolean isInvalidName(String name)`
- `public static boolean isEmptyName(String name)`
- `public static boolean isNullName(String name)`

## TransactionValidator
### Behaviors
- `public static boolean isValidCardId(int cardId)`
- `public static boolean isValidId(int id)`
- `public static boolean isValidAmount(double amount)`
- `public static boolean isValidCategory(Category category)`
- `public static boolean isValidDate(LocalDate date)`

## model.valueobject Package - It consist value object classes
## SpendRecordDto
### States
- `public Category category`
- `public double unusualSpendAmount`
- `public double usualSpendAmount`

### Constructors
- `public SpendRecordDto(Category category, double unusualSpendAmount, double usualSpendAmount)`

## Category
### Enum Values
- `GROCERIES`
- `TRAVEL`

## domain.model.service Package - This package having domain service classes
## SpendAnalyzer
### Behaviors
- `public static Map<Integer, List<SpendRecordDto>> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage)`

## EmailComposer
### Behaviors
- `public static String composeEmail(String name, List<SpendRecordDto> record)`

## EmailSender
### Behaviors
- `public static void sendEmail(String subject, String body, String email)`

# controller Package - This package having controller classes
## CustomerController
### States
- `private final CustomerService customerService`

### Constructors
- `public CustomerController(CustomerService customerService)`

### Behaviors
- `public Response create(String name, String email)`
- `public Customer find(int id)`

## CreditCardController
### States
- `private final CreditCardService creditCardService`

### Constructors
- `public CreditCardController(CreditCardService creditCardService)`

### Behaviors
- `public Response create()`
- `public CreditCard find(int id)`
- `public Response mapCustomer(int cardId, int customerId)`

## TransactionController
### States
- `private final TransactionService transactionService`

### Constructors
- `public TransactionController(TransactionService transactionService)`

### Behaviors
- `public Response create(int cardId, double amount, Category category, LocalDate date)`
- `public Transaction find(int id)`
- `public List<Transaction> getAllTransactions()`
- `public List<Transaction> filterTransactionsByMonth(Month month)`

## CreditCardCompanyController
### States
- `CreditCardCompanyService creditCardCompanyService`

### Constructors
- `public CreditCardCompanyController(CreditCardCompanyService creditCardCompanyService)`

### Behaviors
- `public Map<Integer, List<SpendRecordDto>> evaluateSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage)`
- `public Response sendEmail(Map<Integer, List<SpendRecordDto>> spendRecord)`

## controller.dto Package - This package consist class to return http status from the controller methods
## Response
### States
- `private final HttpStatus httpStatus`
- `private final String message`

### Constructors
- `public Response(HttpStatus httpStatus, String message)`

## HttpStatus
### Enum Values
- `BAD_REQUEST`
- `NOT_FOUND`
- `OK`

# service Package - Classes from this package are the middalware between controller layer and repository layer
## CustomerService
### States
- `private final CustomerRepository customerRepository`

### Constructors
- `public CustomerService(CustomerRepository customerRepository)`

### Behaviors
- `public Customer create(String name, String email)`
- `public Customer find(int id)`

## CreditCardService
### States
- `private final CreditCardRepository creditCardRepository`
- `private final CustomerService customerService`

### Constructors
- `public CreditCardService(CreditCardRepository creditCardRepository,CustomerService customerService)`

### Behaviors
- `public int create()`
- `public CreditCard find(int id)`
- `public boolean mapCustomer(int cardId, int customerId)`

## TransactionService
### States
- `private final CreditCardService creditCardService`
- `private final TransactionRepository transactionRepository`

### Constructors
- `public TransactionService(CreditCardService creditCardService, TransactionRepository transactionRepository)`

### Behaviors
- `public int create(int cardId, double amount, Category category, LocalDate date)`
- `public Transaction find(int id)`
- `public List<Transaction> getAllTransactions()`
- `public List<Transaction> filterTransactionsByMonth(Month month)`

## CreditCardCompanyService
### States
- `private final CreditCardService creditCardService`

### Constructors
- `public CreditCardCompanyService(CreditCardService creditCardService)`

### Behaviors
- `public Map<Integer, List<SpendRecordDto>> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage)`
- `public void sendEmail(Map<Integer, List<SpendRecordDto>> spendRecord)`

# Repository Package - Classes from this package interact with the database, performing operations requested by the services.

## InMemoryCustomerRepository
### States
- `private final InMemoryDatabase inMemoryDatabase`

### Constructors
- `public InMemoryCustomerRepository(InMemoryDatabase inMemoryDatabase)`

### Behaviors
- `public Customer add(Customer customer)`
- `public Customer findCustomer(int id)`

## InMemoryCreditCardRepository
### States
- `private final InMemoryDatabase inMemoryDatabase`

### Constructors
- `public InMemoryCreditCardRepository(InMemoryDatabase inMemoryDatabase)`

### Behaviors
- `public int add(CreditCard creditCard)`
- `public CreditCard find(int id)`

## InMemoryTransactionRepository
### States
- `InMemoryDatabase inMemoryDatabase`

### Constructors
- `public InMemoryTransactionRepository(InMemoryDatabase inMemoryDatabase)`

### Behaviors
- `public int add(Transaction transaction)`
- `public Transaction find(int id)`
- `public List<Transaction> getAllTransactions()`

# Repository.db Package - It gives the implementation for the fake database class.

## FakeInMemoryDatabase
### States
- `private List<Customer> customers`
- `private List<CreditCard> creditCards`
- `private List<Transaction> transactions`
- `private int customerIdCounter`
- `private int creditCardIdCounter`
- `private int transactionIdCounter`

### Behaviors
- `public Customer insertIntoCustomerTable(Customer customer)`
- `public Customer findCustomer(int id)`
- `public int insertIntoCreditCardTable(CreditCard creditCard)`
- `public CreditCard findCreditCard(int id)`
- `public int insertIntoTransactionTable(Transaction transaction)`
- `public Transaction selectTransaction(int id)`
- `public List<Transaction> getAllTransactions()`

