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
|
├── model               # Local Persistence Database. Room (SQLite) database
|   ├── data            # Model classes  (Expense)
|   ├── local           # Data Access Object for Room  and Database Instance
|   ├── repo            # Used to handle all data operations
|
├── utils               # All extension functions
|
├── view                # Activity/Fragment View layer
│   ├── adapter         # Adapter for RecyclerView
|   │  
│   ├── base            # Base root folder
|   |   |__ base        # Base
|   │  
│   ├── details         # Details root folder
|   |   |__ details     # Details
|   │  
│   ├── home            # Home root folder
|   |   |__ home        # Home
|   │  
│   ├── add             # Add root folder
|   |   |__ add         # Add
|   │  
│   ├── splash          # Splash root folder

