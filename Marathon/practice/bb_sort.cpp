#include <iostream>
#include <vector>

std::vector<int> bubbleSort(std::vector<int> array) {
    // Do not modify the original array
    std::vector<int> result = array;

    for (int i = 0; i < std::size(array) - 1; i++) {
        // Swap the elements if the current element is greater than the next one
        if (array[i] > array[i + 1]) {
            std::swap(result[i], result[i + 1]);            
        }
    }
    return result;
}

int main() {
    std::vector<int> array = {5, 1, 4, 2, 8};
    std::vector<int> result = bubbleSort(array);

    for (int i = 0; i < std::size(result); i++) {
        std::cout << result[i] << " ";
    }
    std::cout << std::endl;
}