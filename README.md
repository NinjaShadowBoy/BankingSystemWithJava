# Banking System with Java

A standalone desktop application that simulates a bank, built with Java and
JavaFX. Customers can register, log in, view their accounts and transaction
history, apply for loans, and manage their profile.

## Features

- **Login and registration**
- **Dashboard** with the customer's savings and current accounts
- **Account details** and **transaction history**
- **Loan agreements**
- **Profile management** and **logout**
- Regular users and admins

All data is stored in a JSON file, `test.json`, read and written with Gson, so
there is no database to set up. `output.json` is a sample of the same format.

## Tech

- Java 22 and JavaFX 22 (FXML views in `src/main/resources/com/example/demo/`)
- ControlsFX, JFoenix, BootstrapFX, Ikonli, TilesFX for the UI
- Gson for persistence
- Maven (wrapper included)

## Run it

You need JDK 22 or newer. JavaFX is downloaded by Maven.

```bash
git clone https://github.com/NinjaShadowBoy/BankingSystemWithJava.git
cd BankingSystemWithJava
chmod +x mvnw            # macOS/Linux: the wrapper is not marked executable in git
./mvnw clean javafx:run  # Windows: mvnw.cmd clean javafx:run
```

Run it from the repository root: the app reads and writes `test.json` in the
current directory.

### Sample accounts

`test.json` ships with a few users you can log in with:

| Username | Password | Role |
|---|---|---|
| `jsmith` | `1234` | Customer |
| `jdoe` | `1234` | Customer |
| `alex` | `alex` | Customer |
| `admin` | `1234` | Admin |

## Project layout

```
src/main/java/com/bankmanagement/models/  domain model: Bank, User, Account, Transaction, Loan...
src/main/java/com/example/demo/           JavaFX application and page controllers
src/main/resources/com/example/demo/      FXML layouts and styles
src/main/banking sys.md                   the page-by-page design notes
```
