# 💰 Expense Manager
![](https://github.com/behzod1996/ExpenseManager/blob/master/Expense-Manager-Mock-Up.jpg)

💰 Expense Manager is simple, intuitive, stable and modern app that is just designed for you. Everything you need at your fingertips to manage the expenditures and budgets.

## 📄 What is Room?
The `Room`  persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

### `@Entity` annonation
The `@Entity` annonation in Room represent tables in your database.

```koltin
@Entity(
    tableName = "expense"
)
```

### `@PrimaryKey` annonation
The `@PrimaryKey` annonation allows you to define a primary key for your table to uniquely differentiate data. This means it is useful to separate your data.

```koltin
@PrimaryKey(autoGenerate = true)
```
With the annotation `@PrimaryKey(autoGenerate = true)` we are indicating that id is the primary key of the entity and should be autoGenerate by the database engine.

### `@ColumnInfo` annonation
By default, Room uses the field names as the column names in the database. If you want a column to have a different name, add the `@ColumnInfo` annotation to a field.

```kotlin
@Entity(
    tableName = "expense"
)
data class Expense(

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "total")
    var total : Double?,

    @ColumnInfo (name="expenseType")
    var expenseType : String?,

    / .../

)

```

### DAOs
Data Access Objects or DAOs are used to access our data when we implement Room. Each DAO have to include a set of methods to manipulate the data (insert, update, delete or get).
A `DAO` can be implemented as an interface or as an abstract class, in our case we are going to use an interface. Due to all the DAOs are basically identical we will show only one.

```kotlin
@Dao

interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expenseEntity: ExpenseEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateExpense(expenseEntity: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)

    @Query("SELECT * FROM expense ORDER by createDate DESC")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

}
```
- A function annotated with `@Insert` , `@Update` or `@Delete` have to receive an instance of the desired class as a parameter, which represents the object that we want to insert, update or delete respectively. 
- If we want to get specific information from one or more entities we can annotate a function with `@Query` and provide a SQL script as parameter.
