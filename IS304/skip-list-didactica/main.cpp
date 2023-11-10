#include <iostream>
#include "skipList.hh"


int main() {
    SkipList<int> skipList;

    for (int i = 0; i < 32; i++) {
      skipList.insert(i, i*50);
    }
    std::cout << skipList << std::endl;
    
  return 0;
}
