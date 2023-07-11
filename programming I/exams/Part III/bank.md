# **ATM Bank App**

## **Make a virtual ATM with the following options**

### Person data (_struct_)

* Name (_e.g: John Doe_)
* ID (_e.g: 41946230_)
* Initial balance (_e.g: 1000_)

### MENU

1. ### **Add person (_Create account_)**

2. ### **Login (_Enter account_)**

    1. Operation Type to perform (_Submenu_)
        * Deposit
        * Transfer
        * Withdraw
    2. History
    3. Back to main menu

3. ### **Exit.**

---

### VALIDATIONS

* No negative amounts (_e.g: -1000_)
* If there's not enough money, don't allow the operation
* If the account doesn't exist, don't allow the operation (_e.g: transfer to an incorrect ID_)
* Only USD currency management
* Main() must only have the switch and the call to the functions
* Enter the system with the ID
* If there's less that 2 accounts, don't allow transfer operations
* History must contain the following:
  * ID of the person who the operation was made to
  * Amount
  * Operation type  

#### **EXTRA**

* To transfer, the current user must enter the ID of the person who will receive the money
