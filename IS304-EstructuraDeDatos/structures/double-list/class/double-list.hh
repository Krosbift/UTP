#ifndef DOUBLELIST_HH
#define DOUBLELIST_HH

#include <my-exceptions.hh>
#include <iostream>
#include <node.hh>

/** @class DoublyList
  * @brief Basic implementation of a doubly linked list.
  *
  * @tparam T Type of elements stored in the list.
  * */
template <typename T> class DoublyList {
  public:

    /** Default constructor:
      * @result Initializes without nodes.
      * */
    DoublyList() : first(nullptr), last(nullptr), nodes(0) { }

    /** Copy constructor:
      * @result Initializes with a copy of the list.
      * */
    DoublyList(const DoublyList& list_copy) : first(nullptr), last(nullptr), nodes(0) {
      if (list_copy.empty()) {
        throw Invalid_argument("The list to copy is empty");
      }

      NodeList<T>* current = new NodeList<T>(list_copy.first->getData());
      NodeList<T>* aux = list_copy.first;
      first = current;

      while (aux->getNext() != nullptr) {
        current->setNext(new NodeList<T>(aux->getNext()->getData()));
        current->getNext()->setPrev(current);
        aux = aux->getNext();
        current = current->getNext();
      }

      last = current;
      nodes = list_copy.size();
    }

    /** Destructor that frees memory:
      * @result Memory freed.
      * */
    ~DoublyList() {
      NodeList<T>* current = first;
      while (current != nullptr) {
        delete current;
        current = current->getNext();
      }
    }

  private:

    NodeList<T>* first; // Pointer to the first element.
    NodeList<T>* last;  // Pointer to the last element.
    sz nodes;           // Total nodes.

  public:

    /** Overloading the parentheses operator, accessing node information by index:
      * @param index Index of the node.
      * @return Element extracted from the node.
      * */
    T& operator()(sz index) {
      if (index < 0 || index >= nodes) {
        throw Out_of_range("Index out of range");
      }

      NodeList<T>* node_aux;

      if (index <= nodes / 2) {
        node_aux = first;
        for (int i = 0; i < index; i++) {
          node_aux = node_aux->getNext();
        }
      } else {
        node_aux = last;
        for (int i = nodes - 1; i > index; i--) {
          node_aux = node_aux->getPrev();
        }
      }

      return node_aux->getData();
    }

    /** Assignment operator, assigns this object instance to another:
      * @param list_copy Object instance to copy.
      * @result Instance of this object assigned to another.
      * */
    void operator=(const DoublyList& list_copy) {
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
        current->setNext(new NodeList<T>(aux->getNext()->getData()));
        current->getNext()->setPrev(current);
        aux = aux->getNext();
        current = current->getNext();
      }

      last = current;
      nodes = list_copy.size();
    }

    /** Overloading the insertion operator for iostream:
      * @param os Reference to ostream output.
      * @param lista Reference to the list.
      * @result List displayed on the console.
      * */
    friend std::ostream& operator<<(std::ostream& os, const DoublyList<T>& lista) {
      os << "(";
      NodeList<T>* current = lista.first;
      while (current != nullptr) {
        os << current->getData();
        if (current->getNext() != nullptr) {
          os << ", ";
        }
        current = current->getNext();
      }
      os << ")";
      return os;
    }

    /** Obtains the empty state:
      * @return true if it's empty, false otherwise.
      * */
    bool empty() const {
      return first == nullptr;
    }

    /** Accesses the number of elements:
      * @return Total number of elements.
      * */
    sz size() const {
      return nodes;
    }

    /** Adds a new node at the end:
      * @param data Information to store.
      * @result Node added at the end.
      * */
    void push_back(const T& data) {
      NodeList<T>* new_node = new NodeList<T>(data);

      if (empty()) {
        first = new_node;
      } else {
        last->setNext(new_node);
        new_node->setPrev(last);
      }

      last = new_node;
      nodes++;
    }

    /** Removes the last node:
      * @result Last node removed.
      * */
    void pop_back() {
      if (empty()) {
        throw List_empty("The list is empty");
      }

      NodeList<T>* node_aux = last->getPrev();
      delete last;

      if (node_aux != nullptr) {
        node_aux->setNext(nullptr);
        last = node_aux;
      } else {
        first = nullptr;
        last = nullptr;
      }

      nodes--;
    }

    /** Adds an element at the beginning:
      * @param data Information to store.
      * @result Node added at the beginning.
      * */
    void push_front(const T& data) {
      NodeList<T>* new_node = new NodeList<T>(data);

      if (empty()) {
        last = new_node;
      } else {
        new_node->setNext(first);
        first->setPrev(new_node);
      }

      first = new_node;
      nodes++;
    }

    /** Removes the first node:
      * @result First node removed.
      * */
    void pop_front() {
      if (empty()) {
        throw List_empty("The list is empty");
      }

      NodeList<T>* node_aux = first->getNext();
      delete first;

      if (node_aux != nullptr) {
        node_aux->setPrev(nullptr);
        first = node_aux;
      } else {
        first = nullptr;
        last = nullptr;
      }

      nodes--;
    }

    /** Accesses the element at the specified position:
      * @param index Position of the element.
      * @return Reference to the accessed node element.
      * */
    const T& at(sz index) const {
      if (index < 0 || index >= nodes) {
        throw Out_of_range("Index out of range");
      }

      NodeList<T>* node_aux = first;

      if (index <= nodes / 2) {
        node_aux = first;
        for (int i = 0; i < index; i++) {
          node_aux = node_aux->getNext();
        }
      } else {
        node_aux = last;
        for (int i = nodes - 1; i > index; i--) {
          node_aux = node_aux->getPrev();
        }
      }

      return node_aux->getData();
    }

    /** Inserts a node at the specified position:
      * @param data Information to store.
      * @param index Position to insert.
      * @result Node inserted at the specified position.
      * */
    void insert(const T& data, sz index) {
      if (index < 0 || index > nodes) {
        throw Out_of_range("Index out of range");
      }

      if (index == 0) {
        push_front(data);
        return;
      }

      if (index == nodes) {
        push_back(data);
        return;
      }

      NodeList<T>* new_node = new NodeList<T>(data);

      NodeList<T>* node_ant;
      NodeList<T>* node_pos;

      if (index <= nodes / 2) {
        node_ant = first;
        for (sz i = 0; i < index - 1; i++) {
          node_ant = node_ant->getNext();
        }
        node_pos = node_ant->getNext();
      } else {
        node_pos = last;
        for (sz i = nodes - 1; i > index; i--) {
          node_pos = node_pos->getPrev();
        }
        node_ant = node_pos->getPrev();
      }

      node_ant->setNext(new_node);
      new_node->setPrev(node_ant);

      new_node->setNext(node_pos);
      node_pos->setPrev(new_node);

      nodes++;
    }

    /** Removes a node from the list:
      * @param index Position of the node.
      * @result Node removed.
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

      NodeList<T>* node_ant;
      NodeList<T>* node_pos;
      NodeList<T>* node_del;

      if (index <= nodes / 2) {
        node_ant = first;
        for (sz i = 0; i < index - 1; i++) {
          node_ant = node_ant->getNext();
        }
        node_del = node_ant->getNext();
        node_pos = node_del->getNext();
      } else {
        node_pos = last;
        for (sz i = nodes - 1; i > index; i--) {
          node_pos = node_pos->getPrev();
        }
        node_del = node_pos->getPrev();
        node_ant = node_del->getPrev();
      }

      node_ant->setNext(node_pos);
      node_pos->setPrev(node_ant);

      delete node_del;
      nodes--;
    }

    /** Inserts a list starting from the specified position:
      * @param list List to insert.
      * @param index Position to insert.
      * @result Union of the copy of the list and the original.
      * */
    void insert_copylist(const DoublyList<T>& list, sz index) {
      if (index < 0 || index >= nodes) {
        throw Out_of_range("Index out of range");
      }

      NodeList<T>* node_aux = list.first;

      DoublyList<T> list_temp;

      for (int i = 0; i < list.size(); i++) {
        list_temp.push_back(node_aux->getData());
        node_aux = node_aux->getNext();
      }

      if (index == 0) {
        list_temp.last->setNext(first);
        if (first != nullptr) {
          first->setPrev(list_temp.last);
        }
        first = list_temp.first;
      }

      if (index == nodes - 1) {
        if (last != nullptr) {
          last->setNext(list_temp.first);
        }
        list_temp.first->setPrev(last);
        last = list_temp.last;
      }

      if (index > 0 && index < nodes - 1) {
        NodeList<T>* node_ant = first;
        NodeList<T>* node_pos = first;

        if (index < nodes / 2) {
          for (sz i = 0; i < index - 1; i++) {
            node_ant = node_ant->getNext();
            node_pos = node_pos->getNext();
          }
        } else {
          for (sz i = nodes - 1; i > index; i--) {
            node_pos = node_pos->getPrev();
            node_ant = node_ant->getPrev();
          }
        }

        node_pos = node_pos->getNext();

        node_ant->setNext(list_temp.first);
        list_temp.first->setPrev(node_ant);

        list_temp.last->setNext(node_pos);
        if (node_pos != nullptr) {
          node_pos->setPrev(list_temp.last);
        }
      }

      list_temp.first = nullptr;
      list_temp.last = nullptr;
      nodes += list.size();
    }

    /** Inserts a list into this list starting from the specified position:
      * @param list List to insert.
      * @param pos Position to insert the list.
      * @result The list with the new elements inserted.
      * */
    void insert_heistlist(DoublyList<T>& list, sz pos) {
      if (pos < 0 || pos >= nodes) {
        throw std::out_of_range("Index out of range");
      }

      if (pos == 0) {
        list.last->setNext(first);
        first->setPrev(list.last);
        first = list.first;
      }

      if (pos == nodes - 1) {
        last->setNext(list.first);
        list.first->setPrev(last);
        last = list.last;
      }

      if (pos > 0 && pos < nodes - 1) {
        NodeList<T>* node_ant = first;
        NodeList<T>* node_pos = first;

        if (pos < nodes / 2) {
          for (sz i = 0; i < pos - 1; i++) {
            node_ant = node_ant->getNext();
            node_pos = node_pos->getNext();
          }
        } else {
          for (sz i = nodes - 1; i > pos; i--) {
            node_pos = node_pos->getPrev();
            node_ant = node_ant->getPrev();
          }
        }

        node_pos = node_pos->getNext();

        node_ant->setNext(list.first);
        list.first->setPrev(node_ant);

        list.last->setNext(node_pos);
        if (node_pos != nullptr) {
          node_pos->setPrev(list.last);
        }
      }

      list.first = nullptr;
      list.last = nullptr;
      nodes += list.size();
    }
};

#endif