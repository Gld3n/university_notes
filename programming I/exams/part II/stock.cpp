// For activity reference, see the README file in this directory.

// TODO: Check why the modifyProduct() func is not working properly.
// name modification erases the name of the product.

#include <iostream>

using namespace std;

string productsNames[10];
double productsPrices[10],
productsGrossTotals,
productsNetTotals;
int productsDiscounts[10],
productsQuantities[10],
productsCount;

//! Default Settings
double IVA = 0.16, // 16%
dollarPrice = 26.85; // Bs. (BCV 06/11/2023)


void wait() {
    cout << "\nPress enter to continue...";
    cin.ignore();
    system("cls");
}


void addProduct() {
    string name; double price;
    int discount, quantity;

    cout << "---Add product-------------\n";
    cout << "Fill the following fields:\n";

    cout << "Name: ";
    getline(cin, name);

    cout << "Price (USD): ";
    cin >> price;

    cout << "Discount (from 0 to 99): ";
    cin >> discount;
    while (discount < 0 || discount > 99) {
        cout << "Invalid discount, try again: ";
        cin >> discount;
        cin.clear();
        cin.ignore();
    }

    cout << "Quantity: ";
    cin >> quantity;
    while (quantity < 0) {
        cout << "Invalid quantity, try again: ";
        cin >> quantity;
        cin.clear();
        cin.ignore();
    }

    productsNames[productsCount] = name;
    productsPrices[productsCount] = price;
    productsDiscounts[productsCount] = discount;
    productsQuantities[productsCount] = quantity;
    productsGrossTotals += price * quantity;
    productsNetTotals += productsGrossTotals - (productsGrossTotals * (discount / 100.0)) + (productsGrossTotals * IVA);

    productsCount++;
    cin.ignore(10000, '\n');
}


void modifyProduct() {
    int index, opt;
    
    cout << "---Modify product----------\n";
    if (productsCount == 0) {
        cout << "No products to modify\n";
        return;
    }

    cout 
        << "Select the product to modify by index:\n"
        << "Index: ";
    cin >> index;
    cin.ignore();

    if (index < 0 || index >= productsCount) {
        cout << "Product not found\n";
        return;
    }
    
    cout 
        << "Product found: "
        << productsNames[index]
        << endl;
    
    cout 
        << "What do you want to modify?\n"
        << "1. Name\n"
        << "2. Price\n"
        << "3. Discount\n"
        << "4. Quantity\n"
        << "Option: ";
    cin >> opt;

    switch (opt) {
        case 1:
            cout << "New name: ";
            getline(cin, productsNames[index]);
            break;
        case 2:
            cout << "New price: ";
            cin >> productsPrices[index];
            break;
        case 3:
            cout << "New discount: ";
            cin >> productsDiscounts[index];
            while (productsDiscounts[index] < 0 || productsDiscounts[index] > 99) {
                cout << "Invalid discount, try again: ";
                cin >> productsDiscounts[index];
                cin.clear();
                cin.ignore();
            }
            break;
        case 4:
            cout << "New quantity: ";
            cin >> productsQuantities[index];
            while (productsQuantities[index] < 0) {
                cout << "Invalid quantity, try again: ";
                cin >> productsQuantities[index];
                cin.clear();
                cin.ignore();
            }
            break;
        default:
            cout << "Invalid option\n";
            break;
    }
    cin.ignore(10000, '\n');
}


void listProducts() {
    cout << "---Products----------------\n";
    if (productsCount == 0) {
        cout << "No products in stock\n";
        return;
    }
    for (int i = 0; i < productsCount; i++) {
        double gross = productsPrices[i] * productsQuantities[i],
        net = gross - (gross * (productsDiscounts[i] / 100.0)) + (gross * IVA);

        cout
            << "[Index: " << i << "]\n"
            << "Name: " << productsNames[i] << endl
            << "Price: " << productsPrices[i] << "$ (Bs."
            << (productsPrices[i] * dollarPrice) << ")" << endl
            << "Discount: " << productsDiscounts[i] << "%" << endl
            << "Quantity: " << productsQuantities[i] << " unit(s) in stock" << endl
            << "Gross total: " << (gross) << "$ (Bs." << (gross * dollarPrice) << ")" << endl
            << "Net total: " << (net) << "$ (Bs." << (net) * dollarPrice << ")" << endl
            << endl;
    }
}


void statistics() {
    int cheapest, mostExpensive, largerQuantity, 
    smallerQuantity, discountedProducts = 0;

    for (int i = 0; i < productsCount; i++) {
        if (productsPrices[i] < productsPrices[cheapest]) {
            cheapest = i;
        }
    }
    for (int i = 0; i < productsCount; i++) {
        if (productsPrices[i] > productsPrices[mostExpensive]) {
            mostExpensive = i;
        }
    }
    for (int i = 0; i < productsCount; i++) {
        if (productsQuantities[i] > productsQuantities[largerQuantity]) {
            largerQuantity = i;
        }
    }
    for (int i = 0; i < productsCount; i++) {
        if (productsQuantities[i] < productsQuantities[smallerQuantity]) {
            smallerQuantity = i;
        }
    }
    for (int i = 0; i < productsCount; i++) {
        if (productsDiscounts[i] > 0) {
            discountedProducts++;
        }
    }

    cout << "---Statistics--------------\n";
    cout 
        << "Total products: " << productsCount << endl
        << "Gross total in stock: " << productsGrossTotals << "$" << endl
        << "Net total in stock: " << productsNetTotals << "$" << endl
        << "Cheapest product: " << productsNames[cheapest] 
            << " [" << productsPrices[cheapest] << "$ - Bs."
            << (productsPrices[cheapest] * dollarPrice) << "]" << endl
        << "Most expensive product: " << productsNames[mostExpensive] 
            << " [" << productsPrices[mostExpensive] << "$ - Bs."
            << (productsPrices[mostExpensive] * dollarPrice) << "]" << endl
        << "Product with larger stock: " << productsNames[largerQuantity]
        << " [" << productsQuantities[largerQuantity] << "u]" << endl
        << "Product with smaller stock: " << productsNames[smallerQuantity]
        << " [" << productsQuantities[smallerQuantity] << "u]" << endl
        << "Products with discount: " << discountedProducts << endl
        << "IVA: " << (IVA * 100) << "%\n"
        << "Exchange rate (BCV): Bs." << dollarPrice << endl;
}


void settings() {
    int opt;

    cout << "---Settings----------------\n";
    cout 
        << "What do you want to modify?\n"
        << "1. IVA\n"
        << "2. Dollar price\n"
        << "Option: ";
    cin >> opt;

    switch (opt) {
        case 1:
            cout << "New IVA (as decimal): ";
            cin >> IVA;
            break;
        case 2:
            cout << "New dollar price: ";
            cin >> dollarPrice;
            break;
        default:
            cout << "Invalid option\n";
            break;
    }
    cin.ignore(10000, '\n');
}


int main() {
    int opt, flag = 1;
    
    cout << "*---Welcome to the store---*\n";
    do {
        cout
            << "---Menu--------------------\n"
            << "1. Add products\n"
            << "2. Modify products\n"
            << "3. List products\n"
            << "4. Statistics\n"
            << "5. Settings\n"
            << "6. Exit\n"
            << "Option: ";
        cin >> opt;
        cin.clear();
        cin.ignore(10000, '\n');

        switch (opt) {
            case 1:
                addProduct();
                wait();
                break;
            case 2:
                modifyProduct();
                wait();
                break;
            case 3:
                listProducts();
                wait();
                break;
            case 4:
                statistics();
                wait();
                break;
            case 5:
                settings();
                wait();
                break;
            case 6:
                cout << "Ba-bye!\n";
                flag = 0;
                break;
            default:
                cout << "Invalid option\n";
                wait();
                break;
        }

    } while (flag == 1);
}
