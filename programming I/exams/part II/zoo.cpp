#include <iostream>

using namespace std;

string registeredSpcs[10], species[10], names[10], sexes[10];
double heights[10], weights[10];
int quantities[10], anmlCount = 0, spcCount = 5;

//* Utility functions
void wait() {
    cout << "\nPress enter to continue...";
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

void selectSpc(int index) {
    int opt;
    
    cout << "Select species: " << endl;
    for (int i = 0; i < spcCount; i++) {
        cout << i + 1 << ". " << registeredSpcs[i] << endl;
    }
    cout << "Option: ";
    cin >> opt;
    while (opt < 1 || opt > spcCount || cin.fail()) {
        clearIn();
        cout << "Invalid option, try again: ";
        cin >> opt;
    }
    clearIn();
    
    cout << "You selected: " << registeredSpcs[opt - 1] << endl;
    species[index] = registeredSpcs[opt - 1];
}

//* Main functions
void addAnimal() {
    string name, sex;
    double weight, height;
    int quantity;

    cout << "=== Add Animal ======================\n";
    selectSpc(anmlCount);
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

    cout << "Animal added successfully!" << endl;
    anmlCount++;
}

void modifyAnimal() {
    int index, opt;

    cout << "=== Modify Animal ===================\n";

    if (anmlCount < 1) {
        cout << "There are no animals to modify.\n";
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
        cout << "No transgender animals allowed, sorry.\n";
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
        selectSpc(index);
        break;
    case 7:
        cout << "Exiting...\n";
        break;
    default:
        cout << "Invalid option." << endl;
        wait();
        break;
    }
}

void listAnimals() {
    cout << "=== List Animals ====================\n";

    if (anmlCount < 1) {
        cout << "There are no animals to list.\n";
        return;
    }

    for (int i = 0; i < anmlCount; i++) {
        cout << "Index[" << i << "] " << "(Name: " << names[i] << " - Species: " << species[i] << ")" << endl;
    }
}

void zooDetails() {
    cout << "=== Zoo Details =====================\n";
}

void addSpecies() {
    cout << "=== Add Species =====================\n";
    if (spcCount <= 10) {
        cout << "Enter species name: ";
        cin >> registeredSpcs[spcCount];
        spcCount++;
    } else {
        cout << "You have reached the maximum number of species allowed.\n";
    }
    clearIn();
}

void zooReport() {
    cout
        << "=== Zoo Report ======================\n"
        << "Species: " << spcCount << endl
        << "Animals: " << anmlCount << endl
        << "===================================\n";
}

void mainMenu() {
    int opt;
    bool flag = true;
    
    do {
        cout
        << "=== Menu ============================\n"
        << "1. Add animal [By species]\n"
        << "2. Modify animal [Properties]\n"
        << "3. List animals [Species - Name]\n"
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
            zooDetails();
            wait();
            break;
        case 5:
            addSpecies(); //! DONE
            wait();
            break;
        case 6:
            zooReport();
            wait();
            break;
        case 7:
            cout << "Nos welemos luego biejo! :)\n";
            flag = false;
            wait();
            break;
        default:
            cout << "Invalid option\n";
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
    cout << "Thank you! Your zoo will now be created." << endl;
    wait();

    mainMenu();
}
