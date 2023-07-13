# include <iostream>
# include <list>

using namespace std;

//* Classes
class Account {
public:    
    string Name;
    string Id;
    float Balance;

    Account(string name, string id, float balance) {
        Name = name;
        Id = id;
        Balance = balance;
    }
};

//* Utility functions
void wait() {
    cout << "\n[Press enter to continue...]";
    cin.ignore();
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

Account checkUser(string id, list<Account> accounts) {
    for (Account account : accounts) {
        if (account.Id == id) {
            cout << "Logged in successfully. Welcome, " << account.Name;
            return account;
        }
    }
}

//* Main functions
void createAccount(list<Account> account_list) {
    string name, id; float balance;

    cout << "Enter name: ";
    cin >> name;
    cout << "Enter id: ";
    cin >> id;
    cout << "Enter balance: ";
    cin >> balance;

    Account new_account = Account(name, id, balance);
    cout
        << "Account created successfully!" << endl
        << "Name: " << new_account.Name << endl
        << "Id: " << new_account.Id << endl
        << "Balance: " << new_account.Balance << endl;

    account_list.push_back(new_account);
}

void operations(Account origin, list<Account> accounts) {
    int opOpt;

    cout
        << "=== Operations ======================\n"
        << "1. Deposit\n"
        << "2. Transfer\n"
        << "3. Withdraw\n"
        << "Option: ";
    cin >> opOpt;
    while (opOpt < 1 || opOpt > 3 || cin.fail()) {
        clearIn();
        cout << "Invalid option, try again: ";
        cin >> opOpt;
    }
    clearIn();

    switch(opOpt) {
        case 1:
            // deposit();
            break;
        case 2:
            // transfer();
            break;
        case 3:
            // withdraw();
            break;
        default:
            cout << "[Invalid option. Try again.]: ";
            break;
    }

}

void login(list<Account> account_list) {
    string id;

    cout << "=== Login ===========================\n";
    cout << "Insert your ID: ";
    cin >> id;
    while (!isString(id)) {
        cin.clear();
        cout << "[Invalid ID. Try again.]: " << endl;
        cin >> id;
    }
    clearIn();
    
    Account logged_account = checkUser(id, account_list);

    int accOpt;

    cout 
        << "=== Account =========================\n"
        << "1. Operations\n"
        << "2. History of operations\n"
        << "3. <= Back\n"
        << "Option: ";
    cin >> accOpt;
    while (accOpt < 1 || accOpt > 4 || cin.fail()) {
        clearIn();
        cout << "Invalid option, try again: ";
        cin >> accOpt;
    }
    clearIn();

    switch(accOpt) {
        case 1:
            // operations();
            break;
        case 2:
            // history();
            break;
        case 3:
            cout << "[Exiting...]";
            break;
        default:
    }
}

int main() {
    #ifdef _WIN32
        system("cls");
    #elif __linux__
        system("clear");    
    #endif

    int opt;
    bool flag = true;
    list<Account> system_accounts;
    
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
            createAccount(system_accounts);
            wait();
            break;
        case 2:
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
