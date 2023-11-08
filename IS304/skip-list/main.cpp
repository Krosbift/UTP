#include <iostream>
#include <skip-list.hh>

using namespace std;

int main() {
    SkipList<int> s;
    s.insert(1);
    s.insert(2);
    cout << s.search(1) << endl;
    s.remove(1);
    return 0;
} 