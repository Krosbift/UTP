#ifndef QUEUE_HH
#define QUEUE_HH

#include <iostream>
#include <vector>

template <typename T>
class Queue {
private:
    std::vector<T> elements;

public:
    void enqueue(const T& element) {
        elements.push_back(element);
    }

    void dequeue() {
        if (!empty()) {
            elements.erase(elements.begin());
        }
    }

    const T& front() const {
        if (!empty()) {
            return elements.front();
        }
        throw std::out_of_range("Queue is empty");
    }

    bool empty() const {
        return elements.empty();
    }

    size_t size() const {
        return elements.size();
    }
};

#endif