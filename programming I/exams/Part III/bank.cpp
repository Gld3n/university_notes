# include <iostream>
# include <string>
# include "utility.hpp"

using namespace std;

int accountCount = 0;

//! Classes
class Account {
public:    
    string Name;
    string Id;
    double Balance;
    string History[64];
    int EntryCount = 0;

    Account() {} //* Default constructor
    Account(string name, string id, double balance) { //* Constructor
        Name = name;
        Id = id;
        Balance = balance;
    }

    string getHistory() {
        string history = "";
        for (int i = 0; i < EntryCount; i++) {
            history += History[i] + "\n";
        }
        return history;
    }

    void setHistory(string entry) {
        History[EntryCount] = entry;
        EntryCount++;
    }
};

//! UTILITY FUNCTIONS

Account* findTarget(string id, Account accounts[]) {
    for (int i = 0; i < accountCount; i++) {
        if (id == accounts[i].Id) {
            return &accounts[i];
        }
    }
    return nullptr;
}

Account checkUser(string id, Account accounts[]) {
    for (int i = 0; i < accountCount; i++) {
        if (id == accounts[i].Id) {
            return accounts[i];
        }
    }
    return Account("", "", 0);
}

//! ACCOUNT OPERATIONS
void deposit(Account& origin) {
    double amount;
    clearScreen();

    cout << "=== Deposit =========================\n";

    cout << "Enter amount to deposit (current balance: $" << origin.Balance << "): ";
    cin >> amount;
    while (cin.fail() || amount <= 0) {
        clearIn();
        cout << "Invalid data or amount, try again: ";
        cin >> amount;
    }
    clearIn();

    origin.Balance += amount;
    origin.setHistory("Amount: $" + to_string(amount) + " | Type: Deposit");

    cout << "[Deposit successful.]\n";
    cout << "[New balance: $" << origin.Balance << "]\n";
}

void transfer(Account& origin, Account* accounts) {
    string id; double amount;
    clearScreen();

    cout << "=== Transfer =========================\n";

    if (origin.Balance == 0) {
        cout << "[You have no money to transfer.]\n";
        return;
    } else if (accountCount < 2) {
        cout << "[There are no other accounts to transfer to.]\n";
        return;
    }

    cout << "Enter ID of target account: ";
    cin >> id;
    while (cin.fail() || id.length() < 7 || id.length() > 8 || id.empty()) {
        clearIn();
        cout << "Invalid data, try again: ";
        cin >> id;
    }
    clearIn();

    Account* target = findTarget(id, accounts);
    if (target == nullptr) {
        cout << "[User not found.]\n";
        return;
    }

    cout << "Enter amoun to transfer (current balance: $" << origin.Balance << "): ";
    cin >> amount;
    while (cin.fail() || amount <= 0 || amount > origin.Balance) {
        clearIn();
        cout << "Invalid data or amount, try again: ";
        cin >> amount;
    }
    clearIn();

    origin.Balance -= amount;
    target->Balance += amount;

    origin.setHistory("Target: V" + target->Id + " | Amount: $" + to_string(amount) + " | Type: Transfer");

    cout << "[Transfer successful.]\n";
}

void withdraw(Account& origin) {
    double amount;
    clearScreen();

    cout << "=== Withdraw =========================\n";

    if (origin.Balance == 0) {
        cout << "[You have no money to withdraw.]\n";
        return;
    }

    cout << "Enter amount to withdraw (current balance: $" << origin.Balance << "): ";
    cin >> amount;
    while (cin.fail() || amount <= 0 || amount > origin.Balance) {
        clearIn();
        cout << "Invalid data or amount, try again: ";
        cin >> amount;
    }
    clearIn();

    origin.Balance -= amount;
    origin.setHistory("Amount: $" + to_string(amount) + " | Type: Withdraw");

    cout << "[Withdraw successful. Now imagine there's money coming out somewhere.]\n";
}

//! ACCOUNT FUNCTIONS
void operations(Account& origin, Account* accounts, int accountCount) {
    int opOpt; bool flag = true;
    clearScreen();

    do {
        cout
            << "=== Operations ======================\n"
            << "1. Deposit\n"
            << "2. Transfer\n"
            << "3. Withdraw\n"
            << "4. <= Back\n"
            << "Option: ";
        cin >> opOpt;
        while (opOpt < 1 || opOpt > 4 || cin.fail()) {
            clearIn();
            cout << "Invalid option, try again: ";
            cin >> opOpt;
        }
        clearIn();

        switch(opOpt) {
            case 1:
                deposit(origin);
                wait();
                break;
            case 2:
                transfer(origin, accounts);
                wait();
                break;
            case 3:
                withdraw(origin);
                wait();
                break;
            case 4:
                cout << "[Exiting...]";
                wait();
                flag = false;
                break;
            default:
                cout << "[Invalid option. Try again.]: ";
                wait();
                break;
        }
    } while (flag);
}

void history(Account origin) {
    clearScreen();
    cout << "=== History =========================\n";

    if (accountCount == 0) {
        cout << "[There are no accounts to show history for.]\n";
        return;
    } else if (origin.History->length() == 0) {
        cout << "[There is no history to show.]\n";
        return;
    }

    cout << origin.getHistory();
}

//! MAIN FUNCTIONS
void createAccount(Account* accounts, int& accountCount) {
    string name, id; double balance;

    clearScreen();
    cout << "=== Create account ==================\n";
    cout << "Enter name: ";
    getline(cin, name);
    while (!isString(name)) {
        cin.clear();
        cout << "Invalid data, try again: ";
        getline(cin, name);
    }
    clearIn();

    cout << "Enter ID: ";
    cin >> id;
    while (cin.fail() || id.length() < 7 || id.length() > 8) {
        clearIn();
        cout << "Invalid data, try again: ";
        cin >> id;
    }
    clearIn();

    cout << "Enter balance: ";
    cin >> balance;
    while (balance < 0 || cin.fail()) {
        clearIn();
        cout << "Invalid data or amount, try again: ";
        cin >> balance;
    }
    clearIn();

    Account* newAccount = new Account(name, id, balance);
    clearScreen();

    cout
        << "[Account created successfully!]\n"
        << "- Name: " << newAccount->Name << endl
        << "- ID: " << newAccount->Id << endl
        << "- Balance: " << newAccount->Balance << endl;

    accounts[accountCount] = *newAccount;
    accountCount++;
    delete newAccount;
}

void login(Account* accounts, int accountCount) {
    clearScreen();
    string id;

    cout << "=== Login ===========================\n";

    if (accountCount == 0) {
        cout << "[There are no accounts to log in to.]\n";
        return;
    }

    cout << "Insert your ID: ";
    cin >> id;
    while (cin.fail() || id.length() < 7 || id.length() > 8 || id.empty()) {
        clearIn();
        cout << "Invalid data, try again: ";
        cin >> id;
    }
    clearIn();
    
    Account userAccount = checkUser(id, accounts);
    if (userAccount.Name == "") {
        cout << "[User not found.]\n";
        return;
    }

    cout << "[Logged in successfully. Welcome, " << userAccount.Name << ".]" << endl;
    wait();
    
    int accOpt;
    bool flag = true;

    do {
        clearScreen();
        cout 
            << "=== Account =========================\n"
            << "1. Operations\n"
            << "2. History of operations\n"
            << "3. <= Back\n"
            << "Option: ";
        cin >> accOpt;
        while (accOpt < 1 || accOpt > 3 || cin.fail()) {
            clearIn();
            cout << "Invalid option, try again: ";
            cin >> accOpt;
        }
        clearIn();

        switch(accOpt) {
            case 1:
                operations(userAccount, accounts, accountCount);
                break;
            case 2:
                history(userAccount);
                wait();
                break;
            case 3:
                cout << "[Exiting...]";
                wait();
                flag = false;
                break;
            default:
                cout << "[Invalid option. Try again.]: ";
                break;
        }
    } while (flag);
}

int main() {
    clearScreen();

    int opt;
    bool flag = true;
    Account* systemAccounts = new Account[12];
    
    do {
        cout
        << "=== Menu ============================\n"
        << "1. Add account\n"
        << "2. Log in to account\n"
        << "3. Exit\n"
        << "Option: ";
        cin >> opt;
        clearIn();

        switch (opt) {
        case 1:
            createAccount(systemAccounts, accountCount);
            wait();
            break;
        case 2:
            login(systemAccounts, accountCount);
            break;
        case 3:
            cout << "[Exiting...]\n";
            wait();
            flag = false;
            break;
        default:
            cout << "[Invalid option]\n";
            wait();
            break;
        }
    } while (flag);
}
