/*
* Insert name, lastname, date of birth and print them out.
! Indicate:
- If the person is a minor or not.
- Validate the date.
    - From 1900 to the actual date.
    - Months from 1 to 12.
    - Days from 1 to 31.
- Get the month in letters.

Example output:
Your full name is {name} {lastname}. You were 
born in {day} of {month} of {year}.
Your are {age} years old. You are {minor/major}.
*/
#include <iostream>

using namespace std;

string name, lastname, monthInLetters;
int month, day, year, age;
bool flag = true;

main() {
    cout << "Insert your name: " << endl;
    cin >> name;
    cout << "Insert your lastname: " << endl;
    cin >> lastname;
    cout << endl << endl;
    cout << "-- Insert your day of birth --" << endl;
    cout << "Month (from 1 to 12): " << endl;
    cin >> month;
    if (month < 1 || month > 12) {
        cout << "Invalid month." << endl;
        flag = false;
    }
    cout << "Day (from 1 to 31): " << endl;
    cin >> day;
    if (day < 1 || day > 31) {
        cout << "Invalid day." << endl;
        flag = false;
    }
    cout << "Year (from 1900 to 2023): " << endl;
    cin >> year;
    if (year < 1900 || year > 2023) {
        cout << "Invalid year." << endl;
        flag = false;
    }
    cout << "Now enter your month of birth in letters: " << endl;
    cin >> monthInLetters;
    cout << endl << endl;
    
    if (flag) {
        cout << "You full name is " << name << " " << lastname << 
        ". You were born in " << monthInLetters << day << " of " << year << "." << endl;
        age = 2023 - year;
        cout << "Your are " << age << " years old. You are a ";
        if (age < 18) {
            cout << "minor. Your back doesn't hurt for now (maybe)." << endl;
        } else {
            cout << "major. That means you can go to jail." << endl;
        }
    } else {
        cout << "If you wanted to see your results, you shoul've entered valid values." << endl;
    }
}
