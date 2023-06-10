/* Excercise 1:
* Check between three values which one is the 
* largest and if there are equal values. 
*/
#include <iostream>

using namespace std;

main() {
    int a, b, c;
    cout << "Enter three numbers (a, b and c): ";
    cin >> a >> b >> c;
    if (a == b && b == c) {
        cout << "All numbers are equal" << endl;
    }
    else if (a == b && b != c) {
        cout << "a and b are equal" << endl;
    }
    else if (a != b && b == c) {
        cout << "b and c are equal" << endl;
    }
    else if (a == c && b != c) {
        cout << "a and c are equal" << endl;
    }
    else if (a > b && a > c) {
        cout << "The largest number is " << a << endl;
    }
    else if (b > a && b > c) {
        cout << "The largest number is " << b << endl;
    }
    else {
        cout << "The largest number is " << c << endl;
    }
}
