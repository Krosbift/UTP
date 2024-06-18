#ifndef NODELIST_HH
#define NODELIST_HH

typedef unsigned int sz; // Data type for the size of the list.

/** @class NodeList
  * @brief Basic implementation of a node for a doubly linked list.
  *
  * @tparam T Type of elements stored in the node.
  */
template <typename T> class NodeList {
  public:

    /** Default constructor:
      * @result Node without information.
      * */
    NodeList() : data(T()), next(nullptr), prev(nullptr) { };

    /** Constructor with data:
      * @result Node with the provided information.
      * */
    NodeList(const T& newData) : data(newData), next(nullptr), prev(nullptr) { };

    /** Destructor that frees the memory of the node:
      * */
    ~NodeList() { }

  private:

    T data;           // Information contained in the node.
    NodeList* next;   // Pointer to the next node.
    NodeList* prev;   // Pointer to the previous node.

  public:

    /** Sets the pointer to the next node:
      * @param nextNode Pointer to the next node.
      * @result Pointer set to the node.
      * */
    void setNext(NodeList* nextNode) {
      next = nextNode;
    }

    /** Gets the pointer to the next node:
      * @return Pointer to the next node.
      * */
    NodeList* getNext() const {
      return next;
    }

    /** Sets the pointer to the previous node:
      * @param prevNode Pointer to the previous node.
      * @result Pointer set to the previous node.
      * */
    void setPrev(NodeList* prevNode) {
      prev = prevNode;
    }

    /** Gets the pointer to the previous node:
      * @return Pointer to the previous node.
      * */
    NodeList* getPrev() const {
      return prev;
    }

    /** Sets the information of the node:
      * @param newData Information of the element.
      * @result Sets the new information.
      * */
    void setData(const T& newData) {
      data = newData;
    }

    /** Gets the information of the node:
      * @return Returns the information of the node.
      * */
    const T& getData() const {
      return data;
    }
};

#endif
