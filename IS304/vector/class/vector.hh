#ifndef VECTOR_HH
#define VECTOR_HH

#include <my-exceptions.hh>
#include <iostream>

typedef unsigned int sz; // Type for vector size.

/** @class Vector
  * @brief Basic vector implementation.
  * 
  * This class provides basic functionalities to manipulate a dynamic vector.
  * 
  * @tparam T The type of elements stored in the vector.
  * */
template <typename T> class Vector {
  public:

    /** Default constructor:
      * @result Initializes with no elements and a capacity of 5.
      * */
    Vector() : capacity(5), elements(0), storage(new T[capacity]) { }

    /** Constructor to choose initial capacity:
      * @param cap Initial capacity.
      * @result Initializes with no elements and the chosen capacity.
      * */
    Vector(sz cap): capacity(cap), elements(0), storage(new T[capacity]) { }

    /** Copy constructor, with an additional capacity of five:
      * @param vector_copy Vector to copy.
      * @result Initializes as a copy of another vector, with additional capacity.
      * */
    Vector(const Vector<T>& vector_copy): capacity(vector_copy.size() + 5), elements(vector_copy.size()), storage(new T[capacity]) {
      for (int i = 0; i < elements; ++i) {
        storage[i] = vector_copy[i];
      }
    }

    /** Default destructor:
      * @result Frees unnecessary memory.
      * */
    ~Vector() {
      delete[] storage;
    }

  private:

    sz capacity; // Capacity of elements.
    sz elements; // Number of elements.
    T* storage; // Access pointer.

  private:

    /** Increases the maximum capacity:
      * @param increment Increase factor.
      * @result Increased capacity.
      * */
    void resize(int increment) {
      capacity += increment;
      T* new_storage = new T[capacity];

      for (int i = 0; i < elements; i++) {
        new_storage[i] = storage[i];
      };

      delete[] storage;
      storage = new_storage;
    }

  public:
    
    /** Bracket overload, access to elements by index:
      * @param index Position of the element.
      * @return Element extracted by its index.
      * */
    T& operator[](sz index) {
      if (index >= elements || index < 0) {
        throw Out_of_range("Index out of range");
      }
      return storage[index];
    }

    /** Equal overload, make this instance of the object equal to another:
      * @param vector_copy Instance of the object to copy.
      * @result Instance of this object equalized to another.
      * */
    void operator=(const Vector<T>& vector_copy) {
      delete[] storage;
      storage = new T[capacity];

      capacity = vector_copy.size() + 5;
      elements = vector_copy.size();

      for (int i = 0; i < elements; ++i) {
        storage[i] = vector_copy[i];
      }
    }

    /** Insertion operator overload for iostream:
      * @param os Reference to ostream output.
      * @param vector Reference to vector.
      * @result Vector displayed on the console.
      * */
    friend std::ostream& operator<<(std::ostream& os, const Vector<T>& vector) {
      os << "[";
      for (int i = 0; i < vector.size(); ++i) {
        os << vector.at(i);
        if (i < vector.size() - 1) {
          os << ", ";
        }
      }
      os << "]";
      return os;
    }

    /** Get the storage state:
      * @return True if it's empty, false otherwise.
      * */
    bool empty() const {
      return elements == 0;
    }

    /** Access the number of elements:
      * @return Total number of elements. 
      * */
    sz size() const {
      return elements;
    };

    /** Access the difference between capacity and elements:
      * @return Free space.
      * */
    sz waste() const {
      return capacity - elements;
    }

    /** Insert an element at the end:
      * @param element Element to insert.
      * @result Element inserted at the end.
      * */
    void push_back(const T& element) {
      if (capacity == elements) {
        resize(5);
      }
      
      storage[elements] = element;
      elements++;
    };

    /** Remove the last element:
      * @result Last element removed.
      * */
    void pop_back() {
      if (empty()){
        throw Vector_empty("The vector is empty");
      }

      elements--;
    }

    /** Insert an element at the beginning:
      * @param element Element to insert.
      * @result Element inserted at the beginning.
      * */
    void push_front(const T& element) {
      if (capacity == elements) {
        resize(5);
      }

      for (int i = elements; i > 0; i--) {
        storage[i] = storage[i - 1];
      }      

      storage[0] = element;
      elements++;
    }

    /** Remove the first element:
      * @result First element removed.
      * */
    void pop_front() {
      if (empty()){
        throw Vector_empty("The vector is empty");
      }

      for (int i = 0; i < elements; i++) {
        storage[i] = storage[i + 1];
      }

      elements--;
    };

    /** Insert an element at a specific position:
      * @param element Element to insert.
      * @param index Position where the element will be inserted.
      * @result Element added at the specified position.
      * */
    void insert(const T& element, sz index) {
      if (index >= elements || index < 0) {
        throw Out_of_range("Index out of range");
      }

      if (capacity == elements) {
        resize(5);
      }
      
      for (int i = elements; i > index; i--) {
        storage[i] = storage[i - 1];
      }

      storage[index] = element;
      elements++;
    }

    /** Remove the element at the specified position:
      * @param index Position of the element to remove.
      * @result Element removed.
      * */
    void remove(sz index) {
      if (empty()){
        throw Vector_empty("The vector is empty");
      }

      if (index >= elements || index < 0) {
        throw Out_of_range("Index out of range");
      }
      
      for (int i = index - 1; i < elements; i++) {
        storage[i] = storage[i + 1];
      }
      elements--;
    }

    /** Reduce the waste to zero:
      * @result Capacity reduced to elements.
      * */
    void shrink_to_fit() {
      T* new_storage = new T[elements];
      
      for (int i = 0; i < elements; i++) {
        new_storage[i] = storage[i];
      }

      delete[] storage;
      storage = new_storage;
      capacity = elements;
    }

    /** Swap two elements:
      * @param first First element.
      * @param second Second element.
      * @result Elements swapped.
      * */
    void swap(sz first, sz second) {
      if (first >= elements || first < 0 || second >= elements || second < 0) {
        throw Out_of_range("Index out of range");
      }

      T temp = storage[first];
      storage[first] = storage[second];
      storage[second] = temp;
    }

    /** Access the element at the specified position:
      * @param index Position of the element.
      * @return Reference to the accessed element. 
      * */
    const T& at(sz index) const {
      if (index >= elements || index < 0) {
        throw Out_of_range("Index out of range");
      }
      return storage[index];
    }

    /** Insert another vector at a specific position:
      * @param vector Vector to insert.
      * @param index Position where it will be inserted.
      * @result Vector added to this object.
      * */
    void insert(const Vector<T>& vector, sz index) {
      if (index >= elements || index < 0) {
        throw Out_of_range("Index out of range");
      }

      sz increment = vector.elements;
      if (capacity < elements + increment) {
        resize(elements + increment);
      }

      for (int i = elements - 1; i >= index; i--) {
        storage[i + increment] = storage[i];
      }

      for (int i = 0; i < increment; i++) {
        storage[index + i] = vector.storage[i];
      }

      elements += increment;
    }
};

#endif