#include <iostream>
#include "skipList.hh"


int main() {

    SkipList<int> skipList;

    for (int i = -32; i < 32; i++) {
      if (i != 0) {
        skipList.insert(i);
      }
    }
    std::cout << skipList << std::endl;
    
  return 0;
}