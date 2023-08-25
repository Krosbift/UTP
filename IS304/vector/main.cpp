#include <iostream>
#include <vector.hh>

int main() {
  vector<int> VECTOR;
  for (size_t i = 0; i < 10; i++) {
    VECTOR.push_back(i);
  }
  for (size_t i = 0; i < VECTOR.size(); i++) {
    std::cout << VECTOR.at(i) << std::endl;
  }
  
  return 0;
}