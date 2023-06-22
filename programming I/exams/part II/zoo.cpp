#include <iostream>

using namespace std;

string species[5], names[10], sexes[10];
double heights[10], weights[10];
int quantities[10], anmlCount = 0, spcCount = 5;

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
    cin.ignore(10000, '\n');
}

void selectSpc() {
    int opt;
    
    cout
        << "Select species: " << endl;
    for (int i = 0; i < spcCount; i++) {
        cout << i + 1 << ". " << species[i] << endl;
    }
    cout << "Option: ";
    cin >> opt;
    while (opt < 1 || opt > spcCount || cin.fail()) {
        cout << "Invalid option, try again: ";
        cin >> opt;
        clearIn();
    }
    
    cout << "You selected " << species[opt - 1] << endl;
    wait();
}

void addAnimal() {
    string name, sex;
    double weight, height;
    int quantity;

    selectSpc();
    cout 
        << "=== Add Animal ======================\n"
        << "Fill the following fields:\n";
    
    cout << "Name: ";
    cin >> name;
    while (cin.fail()) {
        cout << "Invalid data, try again: ";
        cin >> name;
        clearIn();
    }
    names[anmlCount] = name;

    cout << "Weight (Kilograms): ";
    cin >> weight;
    while (weight < 0 || cin.fail()) {
        cout << "Invalid weight, try again: ";
        cin >> weight;
        clearIn();
    }
    weights[anmlCount] = weight;

    cout << "Height (Meters): ";
    cin >> height;
    while (height < 0 || cin.fail()) {
        cout << "Invalid height, try again: ";
        cin >> height;
        clearIn();
    }
    heights[anmlCount] = height;

    cout << "Sex (M/F): ";
    cin >> sex;
    while (cin.fail() || sex != "M" || sex != "F") {
        cout << "Invalid data, try again: ";
        cin >> sex;
        clearIn();
    }
    sexes[anmlCount] = sex;

    cout << "Quantity: ";
    cin >> quantity;
    while (quantity < 1 || cin.fail()) {
        cout << "Invalid quantity, try again: ";
        cin >> quantity;
        clearIn();
    }
    quantities[anmlCount] = quantity;

    cout << "Animal added successfully!" << endl;
    clearIn();
    anmlCount++;
}

void modifyAnimal() {
    cout << "=== Modify Animal ===================\n";

}

void listAnimals() {
    cout << "=== List Animals ====================\n";
}

void zooDetails() {
    cout << "=== Zoo Details =====================\n";
}

void addSpecies() {
    cout << "=== Add Species =====================\n";
    if (spcCount < 10) {
        cout << "Enter species name: ";
        cin >> species[spcCount];
        clearIn();
        spcCount++;
    } else {
        cout << "You have reached the maximum number of species allowed.\n";
    }
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
    
    do
    {
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
        addAnimal();
        wait();
        break;
    case 2:
        modifyAnimal();
        wait();
        break;
    case 3:
        listAnimals();
        wait();
        break;
    case 4:
        zooDetails();
        wait();
        break;
    case 5:
        addSpecies();
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
    cout 
        << "===Welcome to the Zoo!=============\n"
        << "To start off, please enter the names\n" 
        << "of 5 species of animals that you\n"
        << "would like to start your zoo with."   
        << endl;

    for (int i = 0; i < 5; i++) {
        cout << "Enter species " << i + 1 << ": ";
        cin >> species[i];
        clearIn();
    }
    cout << "Thank you! Your zoo will now be created." << endl;
    wait();

    mainMenu();
}
