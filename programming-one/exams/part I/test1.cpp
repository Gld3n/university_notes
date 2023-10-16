/*
* Indicate:
- The person is a minor or not. (+)
- Grade average (throw an error in case grade < 0 or > 20) (+)
- Largest and smallest grade and wether there are repeated grades. (+)
- A grade is invalid. (+)
*/

#include <iostream>

using namespace std;

string name, lastname;
int month, day, year, age;
double grade1, grade2, grade3, total;
bool flag = true;

main() {
    cout << "Insert your name: " << endl;
    cin >> name;
    cout << "Insert your lastname: " << endl;
    cin >> lastname;
    cout << endl;

    cout << "Insert your birthdate." << endl;
    cout << "Month (from 1 to 12): " << endl;
    cin >> month;
    cout << "Day (from 1 to 31): " << endl;
    cin >> day;
    cout << "Year (from 1900 to 2023): " << endl;
    cin >> year;
    cout << endl;

    cout << "Now insert your grades." << endl;
    cout << "First grade: " << endl;
    cin >> grade1;
    if (grade1 < 0 || grade1 > 20) {
        flag = false;
        cout << "Invalid grade." << endl;
    }
    cout << "Second grade: " << endl;
    cin >> grade2;
    if (grade2 < 0 || grade2 > 20) {
        flag = false;
        cout << "Invalid grade." << endl;
    }
    cout << "Third grade: " << endl;
    cin >> grade3;
    if (grade3 < 0 || grade3 > 20) {
        flag = false;
        cout << "Invalid grade." << endl;
    }
    cout << endl;

    age = 2023 - year;

    if (age < 17) {
        cout << "You're a minor. You don't go to jail for now." << endl;
    } else {
        cout << "You're a major. You're fucked up... At least you can drink." << endl;
    }

    if (grade1 == grade2 && grade2 == grade3) {
        cout << "All your grades are the same (" << grade1 << ")." << endl;
    } else if (grade1 == grade2 && grade2 != grade3) {
        cout << "You've equal grades: " << grade1 << " and " << grade2 << endl;
    } else if (grade1 != grade2 && grade2 == grade3) {
        cout << "You've equal grades: " << grade2 << " and " << grade3 << endl;
    } else if (grade1 == grade3 && grade3 != grade2) {
        cout << "You've equal grades: " << grade1 << " and " << grade3 << endl;
    }

    if (grade1 > grade2 && grade2 > grade3) {
        cout << "The largest grade was: " << grade1 << endl;
    } else if (grade2 > grade3 && grade3 > grade1) {
        cout << "The largest grade was: " << grade2 << endl;
    } else if (grade3 > grade1 && grade1 > grade2) {
        cout << "The largest grade was: " << grade3 << endl;
    }

    if (grade1 < grade2 && grade2 < grade3) {
        cout << "The smallest grade was: " << grade1 << endl;
    } else if (grade2 < grade3 && grade3 < grade1) {
        cout << "The smallest grade was: " << grade2 << endl;
    } else if (grade3 < grade1 && grade1 < grade2) {
        cout << "The smallest grade was: " << grade3 << endl;
    }

    if (flag) {
        total = (grade1 + grade2 + grade3) / 3;
        cout << "Your full name is: " << name << " " << lastname << endl;
        cout << "Your birthday is: " << month << "/" << day << "/" << year << endl;
        cout << "Your average grade is: " << total << endl;

    } else {
        cout << "You won't have any result. You should've entered valid grades." << endl;
    }
}
