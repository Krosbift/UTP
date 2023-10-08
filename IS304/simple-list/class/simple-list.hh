#ifndef SIMPLE_LIST_HH
#define SIMPLE_LIST_HH

#include <my-exceptions.hh>
#include <node.hh>

typedef unsigned int sz; // Data type for the size of the list.

/** @class SimpleList
  * @brief Basic implementation of a simple linked list.
  *
  * This class provides a basic implementation of a simple linked list. It supports
  * operations such as insertion, deletion, access by index, and various utility functions.
  *
  * @tparam T The type of elements stored in the list.
  */
template <typename T> class SimpleList {
  public:

    /** Default constructor:
      * @result initializes without nodes.
      * */
    SimpleList() : first(nullptr), last(nullptr), nodes(0) { }

    /** Copy constructor:
      * @result initializes with a copy of the list.
      * */
    SimpleList(const SimpleList& list_copy) : first(nullptr), last(nullptr), nodes(0) {
      if (list_copy.empty()) {
        throw Invalid_argument("The list to copy is empty");
      }

      NodeList<T>* current = new NodeList<T>(list_copy.first->getData());
      NodeList<T>* aux = list_copy.first;
      first = current;

      while (aux->getNext() != nullptr) {
        current->setNext(aux->getNext());
        aux = aux->getNext();
        current = new NodeList<T>(aux->getData());
      }
      last = current;

      nodes = list_copy.size();
    }

    /** Destructor that frees memory:
      * @result memory freed.
      * */
    ~SimpleList() {
      NodeList<T>* current = first;
      while (current != nullptr) {
        delete current;
        current = current->getNext();
      }
    }

  private:
  
    NodeList<T>* first; // Pointer to the first element.
    NodeList<T>* last; // Pointer to the last element.
    sz nodes; // Total number of nodes.

  public:

    /** Parentheses overload, access node information by index:
      * @param index index of the node.
      * @return element extracted from the node.
      * */
    T& operator()(sz index) {
      if (index < 0 || index >= nodes) {
        throw Out_of_range("Index out of range");
      }

      NodeList<T>* node_aux = first;

      for (int i = 0; i < index; i++) {
        node_aux = node_aux->getNext();
      }
      
      return node_aux->getData();
    }

    /** Assignment operator, assign this object instance to another:
      * @param list_copy object instance to copy.
      * @result instance of this object assigned to another.
      * */
    void operator=(const SimpleList& list_copy) {
      if (list_copy.empty()) {
        throw Invalid_argument("The list to copy is empty");
      }

      if (!empty()) {
        NodeList<T>* current = first;
        while (current != nullptr) {
          delete current;
          current = current->getNext();
        }
      }

      NodeList<T>* current = new NodeList<T>(list_copy.first->getData());
      NodeList<T>* aux = list_copy.first;
      first = current;

      while (aux->getNext() != nullptr) {
        current->setNext(aux->getNext());
        aux = aux->getNext();
        current = new NodeList<T>(aux->getData());
      }
      last = current;

      nodes = list_copy.size();
    }

    /** Insertion operator overload for iostream:
      * @param os reference to ostream output.
      * @param vector reference to list.
      * @result list shown on the console.
      * */
    friend std::ostream& operator<<(std::ostream& os, const SimpleList<T>& list) {
      os << "(";
      for (int i = 0; i < list.size(); ++i) {
        os << list.at(i);
        if (i < list.size() - 1) {
          os << ", ";
        }
      }
      os << ")";
      return os;
    }

    /** Gets the storage state:
      * @return true if empty, false if not.
      * */
    bool empty() const {
      return first == nullptr;
    }

    /** Access the number of elements:
      * @return total number of elements. 
      * */
    sz size() const {
      return nodes;
    }

    /** Adds a new node to the end:
      * @param data information to store.
      * @result node added at the end.
      * */
    void push_back(const T& data) {
      NodeList<T>* new_node = new NodeList<T>(data);

      if (empty()) {
        first = new_node;
      } else {
        last->setNext(new_node);
      }
      
      last = new_node;
      nodes++;
    }

    /** Removes the last node:
      * @result last node removed.
      * */
    void pop_back() {
      if (empty()) {
        throw List_empty("The list is empty");
      }

      NodeList<T>* node_aux = first;

      while (node_aux->getNext() != last) {
        node_aux = node_aux->getNext();
      }
      
      delete last;
      last = node_aux;
      last->setNext(nullptr);
      nodes--;
    }

    /** Adds an element to the beginning:
      * @param data information to store.
      * @result node added at the beginning.
      * */
    void push_front(const T& data) {
      NodeList<T>* new_node = new NodeList<T>(data);

      new_node->setNext(first);
      first = new_node;

      nodes++;
    }

    /** Removes the first node:
      * @result first node removed.
      * */
    void pop_front() {
      if (empty()) {
        throw List_empty("The list is empty");
      }

      NodeList<T>* node_aux = first;
      node_aux = node_aux->getNext();

      delete first;
      first = node_aux;
      
      nodes--;
    }

    /** Accesses the element at the specified position:
      * @param index position of the element.
      * @return reference to the accessed node element. 
      * */
    const T& at(sz index) const {
      if (index < 0 || index >= nodes) {
        throw Out_of_range("Index out of range");
      }

      NodeList<T>* node_aux = first;

      for (int i = 0; i < index; i++) {
        node_aux = node_aux->getNext();
      }
      
      return node_aux->getData();
    }

    /** Inserts a node at the specified position:
      * @param data information to store.
      * @param index position to insert.
      * @result node inserted at the specified position.
      * */
    void insert(const T& data, sz index) {
      if (index < 0 || index >= nodes) {
        throw Out_of_range("Index out of range");
      }

      if (index == 0) {
        push_front(data);
        return;
      }

      if (index == nodes - 1) {
        push_back(data);
        return;
      }

      NodeList<T>* new_node = new NodeList<T>(data);

      NodeList<T>* node_ant = first;
      NodeList<T>* node_pos = first;

      for (sz i = 0; i < index - 1; i++) {
        node_ant = node_ant->getNext();
        node_pos = node_pos->getNext();
      }
      node_pos = node_pos->getNext();

      node_ant->setNext(new_node);
      new_node->setNext(node_pos);

      nodes++;
    }

    /** Removes a node from the list:
      * @param index position of the node.
      * @result node removed.
      * */
    void remove(sz index) {
      if (empty()) {
        throw List_empty("The list is empty");
      }

      if (index < 0 || index >= nodes) {
        throw Out_of_range("Index out of range");
      }

      if (index == 0) {
        pop_front();
        return;
      }
      
      if (index == nodes - 1) {
        pop_back();
        return;
      }

      NodeList<T>* node_ant = first;
      NodeList<T>* node_pos = first;
      NodeList<T>* node_del = first;

      for (sz i = 0; i < index; i++) {
        node_del = node_del->getNext();
        node_pos = node_pos->getNext();
        if (i != (index - 1)) {
          node_ant = node_ant->getNext();
        }
      }
      node_pos = node_pos->getNext();
      node_ant->setNext(node_pos);

      delete node_del;
      nodes--;
    }

    /** Inserts a list from the specified position:
      * @param list list to insert.
      * @param index position to insert.
      * @result union of the copy of the list and the original.
      * */
    void insert_copylist(const SimpleList<T>& list, sz index) {
      if (index < 0 || index >= nodes) {
        throw Out_of_range("Index out of range");
      }

      NodeList<T>* node_aux = list.first;

      SimpleList<T> list_temp;

      for (int i = 0; i < list.size(); i++) {
        node_aux = node_aux->getNext();
        list_temp.push_back(node_aux->getData());
      }

      if (index == 0) {
        list_temp.last->setNext(first);
        first = list_temp.first;
      }

      if (index == nodes - 1) {
        last->setNext(list_temp.first);
        last = list_temp.last;
      }

      if (index > 0 && index < nodes - 1) {
        NodeList<T>* node_ant = first;
        NodeList<T>* node_pos = first;

        for (sz i = 0; i < index - 1; i++) {
          node_ant = node_ant->getNext();
          node_pos = node_pos->getNext();
        }
        node_pos = node_pos->getNext();

        node_ant->setNext(list_temp.first);
        list_temp.last->setNext(node_pos);
      }

      list_temp.first = nullptr;
      list_temp.last = nullptr;
      nodes += list.size();
    }

    /** Inserts a list into this list from the specified position.
      * @param list list to insert.
      * @param pos position to insert the list.
      * @result the list with the new elements inserted.
      * */
    void insert_heistlist(SimpleList<T>& list, sz pos) {
      if (pos < 0 || pos >= nodes) {
        throw std::out_of_range("Index out of range");
      }

      if (pos == 0) {
        list.last->setNext(first);
        first = list.first;
      }

      if (pos == nodes - 1) {
        last->setNext(list.first);
        last = list.last;
      }

      if (pos > 0 && pos < nodes - 1) {
        NodeList<T>* node_ant = first;
        NodeList<T>* node_pos = first;

        for (sz i = 0; i < pos - 1; i++) {
          node_ant = node_ant->getNext();
          node_pos = node_pos->getNext();
        }
        node_pos = node_pos->getNext();

        node_ant->setNext(list.first);
        list.last->setNext(node_pos);
      }

      list.first = nullptr;
      list.last = nullptr;
      nodes += list.size();
    }
};

#endif