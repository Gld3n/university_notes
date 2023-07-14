//TODO: Check for double wait() calls
//TODO: Implement setters for Account class to be able to modify data
//TODO: Final check for any bugs, missing features or improvements

# include <iostream>

using namespace std;

int account_count = 0;

//* Classes
class Account {
public:    
    string Name;
    string Id;
    float Balance;
    string History[64];

    Account() {} //* Default constructor
    Account(string name, string id, float balance) { //* Constructor
        Name = name;
        Id = id;
        Balance = balance;
    }

    string getHistory() {
        string history = "";
        for (int i = 0; i < sizeof(History); i++) {
            history += History[i] + "\n";
        }
        return history;
    }
};

//* UTILITY FUNCTIONS
void wait() {
    cout << "\n[Press enter to continue...]";
    cin.ignore();
    #ifdef _WIN32
        system("cls");
    #elif __linux__
        system("clear");    
    #endif
}

void cleanScreen() {
    #ifdef _WIN32
        system("cls");
    #elif __linux__
        system("clear");    
    #endif
}

void clearIn() {
    cin.clear();
    cin.ignore(100000, '\n');
}

bool isString(const string& input) {
    if (input.empty()) {
        return false;
    }
    for (char c : input) {
        if (!isalpha(c) && !isspace(c)) {
            return false;
        }
    }
    return true;
}

Account checkUser(string id, Account accounts[]) {
    for (int i = 0; i < account_count; i++) {
        if (id == accounts[i].Id) {
            return accounts[i];
        }
    }
    return Account("", "", 0);
}

//* ACCOUNT OPERATIONS
void deposit(Account origin) {
    float amount;
    cleanScreen();

    cout << "=== Deposit =========================\n";

    if (origin.Balance == 0) {
        cout << "[You have no money to deposit.]\n";
        return;
    }

    cout << "Enter amount to deposit (current balance: $" << origin.Balance << "): ";
    cin >> amount;
    while (amount <= 0 || cin.fail()) {
        clearIn();
        cout << "Invalid data or amount, try again: ";
        cin >> amount;
    }
    clearIn();

    origin.Balance += amount;
    origin.History[origin.History->length()] = "Amount: $" + to_string(amount) + " | Type: Deposit";

    cout << "[Deposit successful.]\n";
}

void transfer(Account origin, Account accounts[]) {
    string id; float amount;
    cleanScreen();

    cout << "=== Transfer =========================\n";

    if (origin.Balance == 0) {
        cout << "[You have no money to transfer.]\n";
        return;
    } else if (account_count < 2) {
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

    Account target = checkUser(id, accounts);
    if (target.Id == "") {
        cout << "[User not found.]\n";
        return;
    }

    cout << "Enter amoun to transfer (current balance: $" << origin.Balance << "): ";
    cin >> amount;
    while (amount <= 0 || cin.fail()) {
        clearIn();
        cout << "Invalid data or amount, try again: ";
        cin >> amount;
    }
    clearIn();

    if (origin.Balance < amount) {
        cout << "[Insufficient funds.]\n";
        return;
    }

    target.Balance += amount;
    origin.Balance -= amount;

    origin.History[origin.History->length()] = "Target: " + target.Id + " | Amount: $" + to_string(amount) + " | Type: Transfer";

    cout << "[Transfer successful.]\n";
}

void withdraw(Account origin) {
    float amount;
    cleanScreen();

    cout << "=== Withdraw =========================\n";

    if (origin.Balance == 0) {
        cout << "[You have no money to withdraw.]\n";
        return;
    }

    cout << "Enter amount to withdraw (current balance: $" << origin.Balance << "): ";
    cin >> amount;
    while (amount <= 0 || cin.fail()) {
        clearIn();
        cout << "Invalid data or amount, try again: ";
        cin >> amount;
    }
    clearIn();

    if (origin.Balance < amount) {
        cout << "[Insufficient funds.]\n";
        return;
    }

    origin.Balance -= amount;
    origin.History[origin.History->length()] = "Amount: $" + to_string(amount) + " | Type: Withdraw";

    cout << "[Withdraw successful. Now imagine there's money coming out somewhere.]\n";
}

//* ACCOUNT FUNCTIONS
void operations(Account origin, Account accounts[]) {
    int opOpt; bool flag = true;
    cleanScreen();

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
    cleanScreen();
    cout << "=== History =========================\n";
    
    if (account_count == 0) {
        cout << "[There are no accounts to show history for.]\n";
        return;
    } else if (origin.History->length() == 0) {
        cout << "[There is no history to show.]\n";
        return;
    }

    cout << origin.getHistory();
}

//* MAIN FUNCTIONS
void createAccount(Account account_list[]) {
    string name, id; float balance;

    cleanScreen();
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
    while (cin.fail() || id.length() < 7 || id.length() > 8 || id.empty()) {
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

    Account new_account = Account(name, id, balance);
    cleanScreen();

    cout
        << "[Account created successfully!]\n"
        << "- Name: " << new_account.Name << endl
        << "- ID: " << new_account.Id << endl
        << "- Balance: " << new_account.Balance << endl;

    account_list[account_count] = new_account;
    account_count++;
}

void login(Account account_list[]) {
    cleanScreen();
    string id;

    cout << "=== Login ===========================\n";

    if (account_count == 0) {
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
    
    Account userAccount = checkUser(id, account_list);
    if (userAccount.Id == "") {
        cout << "[User not found.]\n";
        return;
    }

    cout << "[Logged in successfully. Welcome, " << userAccount.Name << ".]" << endl;
    wait();
    
    int accOpt;
    bool flag = true;
    cleanScreen();

    do {
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
                operations(userAccount, account_list);
                break;
            case 2:
                // history();
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
    cleanScreen();

    int opt;
    bool flag = true;
    Account systemAccounts[12];
    
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
            createAccount(systemAccounts);
            wait();
            break;
        case 2:
            login(systemAccounts);
            wait();
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
