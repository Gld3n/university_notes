#include <iostream>

using namespace std;

/* VARS:
- Name, lastname, gender, birthdate (day, month and year as ints), grades ammount. X

VALIDATE:
- Validate dates. (day, month and year) X
- Validate each grade. X

INDICATE:
- Student with the highest grades. X
- Student with the lowest grades. X
- Major or minor. X
- How many men and women were registered. X
- If the program must end or repeat. X
*/

int main() {
    string stuHi, stuLow;
    int male = 0, female = 0, flag;
    double highest = 0, lowest = 20;

    do {
        string name, lastname, gender;
        int grades, day, month, year, age;
        double grade, total = 0, sum = 0;
        
        cout << "Insert your name: ";
        cin >> name;
        cout << "Insert your lastname: ";
        cin >> lastname;
        cout << "Insert your gender (m/f): ";
        cin >> gender;
        
        while (gender != "m" && gender != "f") {
            cout << "Errooor! Try again: ";
            cin >> gender;
        }
        if (gender == "m") {
            male++;
        }
        else if (gender == "f")
        {
            female++;
        }
        

        cout << "Now insert your bithday date (day, month, year)." << endl;
        cout << "Day: ";
        cin >> day;
        while (day > 31 || day < 1) {
            cout << "Invalid date, please try again: " << endl;
            cin >> day;
        }
        
        cout << "Month: ";
        cin >> month; 
        while (month > 12 || month < 1) {
            cout << "Invalid date, please try again." << endl;
            cin >> month;
        };
        
        cout << "year: ";
        cin >> year;
        while (year > 2023 || year < 1900) {
            cout << "Invalid date, please try again." << endl;
            cin >> year;
        }

        cout << "Insert the number of grades you're going to provide: ";
        cin >> grades;
        for (int i = 0; i < grades; i++) {
            cout << "Insert grade " << i + 1 << ": ";
            cin >> grade;
            
            while (grade < 0 || grade > 20) {
                cout << "Invalid grade, please try again: ";
                cin >> grade;
            }
            
            sum += grade;
        }
        
        total = sum / grades;
        cout << "Your average is: " << total << endl;
        if (total > highest) {
                highest = total;
                stuHi = name + " " + lastname;
            }
            else if (total < lowest) {
                lowest = total;
                stuLow = name + " " + lastname;
            }

        age = 2023 - year;
        cout << "Your full name is: " << name << " " << lastname << endl; 
        if (age >= 18) {
            cout << "You're an adult." << endl;
        } else {
            cout << "You're a minor." << endl;
        }

        cout << "Do you want to repeat the program? (0 = no): ";
        cin >> flag;

        system("cls");
    } while (flag != 0);
    
    cout << "Number of men: " << male << ". Number of women: " << female << endl;
    cout << "Student with the highest grades: " << stuHi << " with: " << highest << endl;
    if (!stuLow.empty()) {
        cout << "Student with the lowest grades: " << stuLow << " with: " << lowest << endl;   
    }
}