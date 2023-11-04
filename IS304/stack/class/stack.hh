#ifndef STACK_HH
#define STACK_HH

#include <iostream>
#include <vector>

template <typename T>
class Stack {
  private:
    std::vector<T> elements;

  public:
    void push(const T& element) {
        elements.push_back(element);
    }

    void pop() {
        if (!empty()) {
            elements.pop_back();
        }
    }

    const T& top() const {
        if (!empty()) {
            return elements.back();
        }
        throw std::out_of_range("Stack is empty");
    }

    bool empty() const {
        return elements.empty();
    }

    size_t size() const {
        return elements.size();
    }
};

#endif