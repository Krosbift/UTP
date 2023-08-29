#include <iostream>
#include <string>
#include <vector.hh>

int main() {
  vector<int> vector_uno;
  for (int i = 0; i < 5; i++) {
    vector_uno.push_back(i);
  }
  vector<int> vector_dos;
  for (int i = 0; i < 3; i++) {
    vector_dos.push_back(i*5);
  }

  return 0;
}