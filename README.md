# 💰 Expense Manager
<img src="https://github.com/behzod1996/ExpenseManager/blob/master/Expense-Manager-Mock-Up.jpg" width="max-width;"/>

### 💰 Expense Manager is simple, intuitive, stable and modern app that is just designed for you. Everything you need at your fingertips to manage the expenditures and budgets.

## 📄 Technologies used:
### `Kotlin`
Kotlin is a modern but already mature programming language aimed to make developers happier. It’s concise, safe, interoperable with Java and other languages, and provides many ways to reuse code between multiple platforms for productive programming.
### `Coroutines`
A coroutine is an instance of suspendable computation. It is conceptually similar to a thread, in the sense that it takes a block of code to run that works concurrently with the rest of the code. However, a coroutine is not bound to any particular thread. It may suspend its execution in one thread and resume in another one.
### `Room`
The `Room`  persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
### `Android Architecture Components`

## Package Structure 📦

com.example.expensemanager # Root Package

├── di                  # Hilt DI Modules 
    ├── data                # For data handling.
    │   ├── local           # Local Persistence Database. Room (SQLite) database
    |   │   ├── dao         # Data Access Object for Room   
    |   |   |── database    # Database Instance
    |
    ├── model               # Model classes (Expense)
    |
    |-- repo                # Used to handle all data operations
    |
    ├── view                # Activity/Fragment View layer
    │   ├── main            # Main root folder
    |   │   ├── main        # Main Activity for RecyclerView
    |   │   └── viewmodel   # Transaction ViewModel
    |   │   ├── adapter     # Adapter for RecyclerView
    │   ├── Dashboard       # Dashboard root folder
    |   |   |__ dashboard   # Dashboard 
    │   ├── Add             # Add Transaction root folder
    |   |   |__ add         # Add Transaction 
    │   ├── Edit            # Edit Transaction root folder
    |   |   |__ edit        # Edit Transaction
    │   ├── Details         # Add Transaction root folder
    |   |   |__ details     # Transaction Details
    │   ├── About           # About root folder
    |   |   |__ about       # About 
    │   ├── Dialog          # All Dialogs root folder
    |   |   |__ dialog      # Error Dialog 
    ├── utils               # All extension functions


<br/>
