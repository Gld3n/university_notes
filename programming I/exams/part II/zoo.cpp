#include <iostream>
#include <algorithm>

using namespace std;

string registeredSpcs[20], species[20], names[20], sexes[20];
double heights[20], weights[20];
int quantities[20], anmlCount = 0, spcsCount = 5;

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

string selectSpcs(int index, bool flag) {
    int opt;
    
    cout << "Select species: " << endl;
    for (int i = 0; i < spcsCount; i++) {
        cout << i + 1 << ". " << registeredSpcs[i] << endl;
    }
    cout << "Option: ";
    cin >> opt;
    while (opt < 1 || opt > spcsCount || cin.fail()) {
        clearIn();
        cout << "Invalid option, try again: ";
        cin >> opt;
    }
    clearIn();
    
    cout << "You selected: " << registeredSpcs[opt - 1] << endl;
    if (flag) {
        species[index] = registeredSpcs[opt - 1];
        return "";
    } else {
        return registeredSpcs[opt - 1];
    }
}

//* Main functions
void addAnimal() {
    string name, sex;
    double weight, height;
    int quantity;

    cout << "=== Add Animal ======================\n";
    selectSpcs(anmlCount, true);
    wait();

    cout 
        << "=== Add Animal ======================\n"
        << "Fill the following fields:\n";
    
    cout << "Name: ";
    getline(cin, name);
    while (!isString(name)) {
        cin.clear();
        cout << "Invalid data, try again: ";
        getline(cin, name);
    }
    clearIn();
    names[anmlCount] = name;

    cout << "Weight (Kilograms): ";
    cin >> weight;
    while (weight < 0 || cin.fail()) {
        clearIn();
        cout << "Invalid weight, try again: ";
        cin >> weight;
    }
    clearIn();
    weights[anmlCount] = weight;

    cout << "Height (Meters): ";
    cin >> height;
    while (height < 0 || cin.fail()) {
        clearIn();
        cout << "Invalid height, try again: ";
        cin >> height;
    }
    clearIn();
    heights[anmlCount] = height;

    cout << "Sex (M/F): ";
    cin >> sex;
    while (cin.fail() || (sex != "M" && sex != "F")) {
        clearIn();
        cout << "Invalid data, try again: ";
        cin >> sex;
    }
    clearIn();
    sexes[anmlCount] = sex;

    cout << "Quantity: ";
    cin >> quantity;
    while (quantity < 1 || cin.fail()) {
        clearIn();
        cout << "Invalid quantity, try again: ";
        cin >> quantity;
    }
    clearIn();
    quantities[anmlCount] = quantity;

    cout << "[Animal added successfully!]" << endl;
    anmlCount++;
}

void modifyAnimal() {
    int index, opt;

    cout << "=== Modify Animal ===================\n";

    if (anmlCount < 1) {
        cout << "[There are no animals to modify.]\n";
        return;
    }

    cout << "Select animal to modify: " << endl;
    for (int i = 0; i < anmlCount; i++) {
        cout << "Index[" << i << "] " << "(Name: " << names[i] << " - Species: " << species[i] << ")" << endl;
    }
    cout << "Option: ";
    cin >> index;
    while (index < 0 || index >= anmlCount || cin.fail()) {
        clearIn();
        cout << "Invalid option, try again: ";
        cin >> index;
    }
    clearIn();

    cout << "You selected: (" << names[index] << " - " << species[index] << ")" << endl;
    wait();

    cout << "=== Modify Animal ===================\n";
    cout 
        << "Select property to modify: \n"
        << "1. Name\n"
        << "2. Weight\n"
        << "3. Height\n"
        << "4. Sex\n"
        << "5. Quantity\n"
        << "6. Species\n"
        << "7. Exit\n"
        << "Option: ";
    cin >> opt;
    while (opt < 1 || opt > 7 || cin.fail()) {
        clearIn();
        cout << "Invalid option, try again: ";
        cin >> opt;
    }
    clearIn();

    switch (opt) {
    case 1:
        cout << "Enter new name: ";
        getline(cin, names[index]);
        while (!isString(names[index])) {
            cin.clear();
            cout << "Invalid data, try again: ";
            getline(cin, names[index]);
        }
        clearIn();
        break;
    case 2:
        cout << "Enter new weight: ";
        cin >> weights[index];
        while (weights[index] < 0 || cin.fail()) {
            clearIn();
            cout << "Invalid weight, try again: ";
            cin >> weights[index];
        }
        clearIn();
        break;
    case 3:
        cout << "Enter new height: ";
        cin >> heights[index];
        while (heights[index] < 0 || cin.fail()) {
            clearIn();
            cout << "Invalid height, try again: ";
            cin >> heights[index];
        }
        clearIn();
        break;
    case 4:
        cout << "[No transgender animals allowed, sorry.]\n";
        break;
    case 5:
        cout << "Enter new quantity: ";
        cin >> quantities[index];
        while (quantities[index] < 1 || cin.fail()) {
            clearIn();
            cout << "Invalid quantity, try again: ";
            cin >> quantities[index];
        }
        clearIn();
        break;
    case 6:
        selectSpcs(index, true);
        break;
    case 7:
        cout << "[Exiting...]\n";
        break;
    default:
        cout << "[Invalid option.]" << endl;
        wait();
        break;
    }
}

void listAnimals() {
    cout << "=== List Animals ====================\n";

    if (anmlCount < 1) {
        cout << "[There are no animals to list.]\n";
        return;
    }

    for (int i = 0; i < anmlCount; i++) {
        cout << "Index[" << i << "] " << "(Name: " << names[i] << " - Species: " << species[i] << ")" << endl;
    }
}

void zooDetails() {
    int opt;

    cout << "=== Zoo Details =====================\n";
    
    if (anmlCount < 1) {
        cout << "[There are no animals to detail.]\n";
        return;
    }
    
    string slct = selectSpcs(0, false);
    int count = 0;
    for (int i = 0; i < anmlCount; i++) {
        if (slct != species[i]) {
            count++;
        }
    }
    if (count == anmlCount) {
        cout << "[The species have no info related to it.]" << endl;
        return;
    }
    wait();

    cout
        << "=== Zoo Details =====================\n"
        << "Select the data to detail [" << slct << "]: \n"
        << "1. All the animals in the species.\n"
        << "2. The weight of each animal of the species.\n"
        << "3. The height of each animal of the species.\n"
        << "4. The sex of each animal of the species.\n"
        << "5. The quantity of each animal of the species.\n"
        << "6. Exit\n"
        << "Option: ";
    cin >> opt;
    while (opt < 1 || opt > 6 || cin.fail()) {
        clearIn();
        cout << "Invalid option, try again: ";
        cin >> opt;
    }
    clearIn();
    #ifdef _WIN32
        system("cls");
    #elif __linux__
        system("clear");
    #endif

    cout << "=== Zoo Details =====================\n";
    switch (opt) {
    case 1:
        for (int i = 0; i < anmlCount; i++) {
            if (slct == species[i]) {
                cout << "Index[" << i << "] " << "(Name: " << names[i] << " - Species: " << species[i] << ")" << endl;
            }
        }
        break;
    case 2:
        for (int i = 0; i < anmlCount; i++) {
            if (slct == species[i]) {
                cout << "Index[" << i << "] " << "(Name: " << names[i] << " - Weight: " << weights[i] << "Kg)" << endl;
            }
        }
        break;
    case 3:
        for (int i = 0; i < anmlCount; i++) {
            if (slct == species[i]) {
                cout << "Index[" << i << "] " << "(Name: " << names[i] << " - Height: " << heights[i] << "m)" << endl;
            }
        }
        break;
    case 4:
        for (int i = 0; i < anmlCount; i++) {
            if (slct == species[i]) {
                cout << "Index[" << i << "] " << "(Name: " << names[i] << " - Sex: " << sexes[i] << ")" << endl;
            }
        }
        break;
    case 5:
        for (int i = 0; i < anmlCount; i++) {
            if (slct == species[i]) {
                cout << "Index[" << i << "] " << "(Name: " << names[i] << " - Qtty: " << quantities[i] << "u)" << endl;
            }
        }
        break;
    case 6:
        cout << "[Exiting...]\n";
        break;
    }
}

void addSpecies() {
    cout << "=== Add Species =====================\n";
    
    if (spcsCount <= 10) {
        cout << "Enter species name: ";
        cin >> registeredSpcs[spcsCount];
        while (!isString(registeredSpcs[spcsCount])) {
            clearIn();
            cout << "Invalid data, try again: ";
            cin >> registeredSpcs[spcsCount];
        }
        spcsCount++;
    } else {
        cout << "[You have reached the maximum number of species allowed.]\n";
    }
    clearIn();
}

void zooMatrix() {
    int opt;

    if (anmlCount < 1) {
        cout << "[There are no animals to detail.]\n";
        return;
    }

    cout
        << "=== Zoo Report ======================\n"
        << "Select the desired data: \n"
        << "1. Animals by species.\n"
        << "2. Animals by sex.\n"
        << "3. Animals by weight (heaviest to lightest).\n"
        << "4. Animals by weight (lightest to heaviest).\n"
        << "5. Animals by height (tallest to shortest).\n"
        << "6. Animals by height (shortest to tallest).\n"
        << "7. Exit\n"
        << "Option: ";
    cin >> opt;
    clearIn();
    wait();

    switch(opt) {
        case 1:
            cout << "=== Zoo Report ======================\n";

            int nAnmls = 0;
            
            for (int row = 0; row < spcsCount; row++) {
                cout << registeredSpcs[row] << ": ";
                for (string spcs : registeredSpcs) {
                    for (int i = 0; i < anmlCount; i++) {
                        if (spcs == species[i]) {
                            nAnmls++;
                        }
                    }
                }
                for (int column = 0; column < anmlCount; column++) {
                    if (registeredSpcs[row] == species[column]) {
                        cout << "[" << row << "]" << "[" << column << "]" << "(" << names[column] << ")" << "\t";
                    }
                }
                cout << endl;
            }
            break;
        case 2:
            cout << "=== Zoo Report ======================\n";

            int nAnmls = 0;
            
            for (int row = 0; row < spcsCount; row++) {
                cout << registeredSpcs[row] << ": ";
                for (string spcs : registeredSpcs) {
                    for (int i = 0; i < anmlCount; i++) {
                        if (spcs == species[i]) {
                            nAnmls++;
                        }
                    }
                }
                for (int column = 0; column < anmlCount; column++) {
                    if (registeredSpcs[row] == species[column]) {
                        cout << "[" << row << "]" << "[" << column << "]" << "(" << names[column] << ")" << "\t";
                    }
                }
                cout << endl;
            }
            break;
        case 3:
            cout << "=== Zoo Report ======================\n";

            int nAnmls = 0;
            
            for (int row = 0; row < spcsCount; row++) {
                cout << registeredSpcs[row] << ": ";
                for (string spcs : registeredSpcs) {
                    for (int i = 0; i < anmlCount; i++) {
                        if (spcs == species[i]) {
                            nAnmls++;
                        }
                    }
                }
                for (int column = 0; column < anmlCount; column++) {
                    if (registeredSpcs[row] == species[column]) {
                        cout << "[" << row << "]" << "[" << column << "]" << "(" << names[column] << ")" << "\t";
                    }
                }
                cout << endl;
            }
            break;
        case 4:
            cout << "=== Zoo Report ======================\n";

            int nAnmls = 0;
            
            for (int row = 0; row < spcsCount; row++) {
                cout << registeredSpcs[row] << ": ";
                for (string spcs : registeredSpcs) {
                    for (int i = 0; i < anmlCount; i++) {
                        if (spcs == species[i]) {
                            nAnmls++;
                        }
                    }
                }
                for (int column = 0; column < anmlCount; column++) {
                    if (registeredSpcs[row] == species[column]) {
                        cout << "[" << row << "]" << "[" << column << "]" << "(" << names[column] << ")" << "\t";
                    }
                }
                cout << endl;
            }
            break;
        case 5:
            cout << "=== Zoo Report ======================\n";

            int nAnmls = 0;
            
            for (int row = 0; row < spcsCount; row++) {
                cout << registeredSpcs[row] << ": ";
                for (string spcs : registeredSpcs) {
                    for (int i = 0; i < anmlCount; i++) {
                        if (spcs == species[i]) {
                            nAnmls++;
                        }
                    }
                }
                for (int column = 0; column < anmlCount; column++) {
                    if (registeredSpcs[row] == species[column]) {
                        cout << "[" << row << "]" << "[" << column << "]" << "(" << names[column] << ")" << "\t";
                    }
                }
                cout << endl;
            }
            break;
        case 6:
            cout << "=== Zoo Report ======================\n";

            int nAnmls = 0;
            
            for (int row = 0; row < spcsCount; row++) {
                cout << registeredSpcs[row] << ": ";
                for (string spcs : registeredSpcs) {
                    for (int i = 0; i < anmlCount; i++) {
                        if (spcs == species[i]) {
                            nAnmls++;
                        }
                    }
                }
                for (int column = 0; column < anmlCount; column++) {
                    if (registeredSpcs[row] == species[column]) {
                        cout << "[" << row << "]" << "[" << column << "]" << "(" << names[column] << ")" << "\t";
                    }
                }
                cout << endl;
            }
            break;
        case 7:
            cout << "[Exiting...]\n";
            break;
    }
}

void mainMenu() {
    int opt;
    bool flag = true;
    
    do {
        cout
        << "=== Menu ============================\n"
        << "1. Add animal [By species]\n"
        << "2. Modify animal [Properties]\n"
        << "3. List animals [Name - Species]\n"
        << "4. Zoo details [By species]\n"
        << "5. Add Species [Name]\n"
        << "6. Zoo report [Matrix]\n"
        << "7. Exit\n"
        << "Option: ";
        cin >> opt;
        clearIn();

        switch (opt) {
        case 1:
            addAnimal(); //! DONE
            wait();
            break;
        case 2:
            modifyAnimal(); //! DONE
            wait();
            break;
        case 3:
            listAnimals(); //! DONE
            wait();
            break;
        case 4:
            zooDetails(); //! DONE
            wait();
            break;
        case 5:
            addSpecies(); //! DONE
            wait();
            break;
        case 6:
            zooMatrix();
            wait();
            break;
        case 7:
            cout << "[Bye!]\n";
            flag = false;
            wait();
            break;
        default:
            cout << "[Invalid option]\n";
            wait();
            break;
        }
    } while (flag);
}

int main() {
    #ifdef _WIN32
        system("cls");
    #elif __linux__
        system("clear");    
    #endif

    cout 
        << "===Welcome to the Zoo!=============\n"
        << "To start off, please enter the names\n" 
        << "of 5 species of animals that you\n"
        << "would like to start your zoo with."   
        << endl;

    for (int i = 0; i < 5; i++) {
        cout << "Enter species " << i + 1 << ": ";
        cin >> registeredSpcs[i];
        while (!isString(registeredSpcs[i])) {
            clearIn();
            cout << "Invalid data, try again: ";
            cin >> registeredSpcs[i];
        }
        clearIn();
    }
    cout << "[Thank you! Your zoo will now be created.]" << endl;
    wait();

    mainMenu();
}
