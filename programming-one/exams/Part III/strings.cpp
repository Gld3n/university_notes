#include <iostream>
#include <string>
#include <vector>
#include "utility.hpp"
#include <sstream>

using std::cout, std::cin, std::endl, std::string;

//! UTILITY FUNCTIONS

void calculate_appearances(std::vector<string>& words, std::vector<string>& texts) {
    for (string& word : words) {
        int count = 0;
        std::istringstream iss(texts[0]);
        std::string temp;

        while (std::getline(iss, temp, ' ')) {
            if (word == temp) {
                count++;
            }
        }

        cout << "- " << word << " = " << count << endl;
    }
}

//! MAIN FUNCTIONS

void insert_words(std::vector<string>& words) {
    std::vector<string> temp;
    string word;

    clearScreen();
    cout << "=== INSERT WORDS ====================" << endl;

    cout << "Insert words one by one. Enter '0' to quit" << endl;
    do {
        cout << "- ";
        cin >> word;
        clearIn();

        if (word == "0") {
            break;
        }

        if (!isString(word)) {
            cout << "[Invalid data]" << endl;
            continue;
        }
        
        words.push_back(word);
        temp.push_back(word);
    } while (true);

    if (temp.empty()) {
        cout << "[No words were inserted]" << endl;
        return;
    }

    clearScreen();
    cout << "[Words inserted]" << endl;
    for (string word : temp) {
        cout << "- " << word << endl;
    }
}

void insert_text(std::vector<string>& texts) {
    string text;
    
    clearScreen();
    cout << "=== INSERT TEXT =====================" << endl;

    cout << (texts.empty() ? "Insert text: " : "Insert a new text: ") << endl;
    std::getline(cin, text);
    clearIn();

    if (text.empty()) {
        cout << "[No text was inserted]" << endl;
        return;
    }

    clearScreen();
    cout << "[Text inserted]" << endl;
    cout << "- " << text << endl;

    texts.clear();
    texts.push_back(text);
}

void statistics(std::vector<string>& words, std::vector<string>& texts) {
    clearScreen();
    cout << "=== STATISTICS ======================" << endl;
    
    if (words.empty() || texts.empty()) {
        cout << "[No statistics to show]" << endl;
        return;
    }

    cout << "[Current words]" << endl;
    calculate_appearances(words, texts);

    cout << endl;

    cout << "[Current text]" << endl;   
    cout << "'" << texts[0] << "'" << endl;
}

int main() {
    std::vector<string> words;
    std::vector<string> texts;
    bool signal = true;
    int opt;

    do {
        clearScreen();

        cout 
            << "=== MENU ============================" << endl
            << "1. Insert words" << endl
            << "2. Insert text" << endl
            << "3. Statistics" << endl
            << "4. [Exit]" << endl
            << "Choose an option: ";
        cin >> opt;
        clearIn();

        switch (opt) {
            case 1:
                insert_words(words);
                wait();
                break;
            case 2:
                insert_text(texts);
                wait();
                break;
            case 3:
                statistics(words, texts);
                wait();
                break;
            case 4:
                cout << "[Exiting...]" << endl;
                wait();
                signal = false;
                break;
            default:
                cout << "[Invalid option]" << endl;
                wait();
                break;
        }
    } while (signal);

    clearScreen();
}