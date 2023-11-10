#include <iostream>
#include "skipList.hh"


int main() {

    SkipList<int> skipList;

    for (int i = -9; i < 32; i++) {
      skipList.insert(i);
    }
    std::cout << skipList << std::endl;
    
  return 0;
}