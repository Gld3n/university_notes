# include <iostream>
# include <list>

using namespace std;

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

void login() {
    string id;
    cout << "Enter id: ";
    cin >> id;
}

int main() {
    #ifdef _WIN32
        system("cls");
    #elif __linux__
        system("clear");    
    #endif

    int opt;
    bool flag = true;
    list<Account> accounts;
    
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
            createAccount(accounts);
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

    for (Account account : accounts) {
        cout
            << "Name: " << account.Name << endl
            << "Id: " << account.Id << endl
            << "Balance: " << account.Balance << endl;
    }
}
