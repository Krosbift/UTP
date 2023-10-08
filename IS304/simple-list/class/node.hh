#ifndef NODELIST_HH
#define NODELIST_HH

/** @class NodeList
  * @brief Node implementation for a linked list.
  * 
  * This class represents a node in a linked list.
  * 
  * @tparam T The type of elements stored in the node.
  * */
template <typename T> class NodeList {
  public:

    /** Default constructor:
      * @result Node without information.
      * */
    NodeList() : data(T()), next(nullptr) { };

    /** Copy constructor with data:
      * @result Node with the added information.
      * */
    NodeList(const T& data) : data(data), next(nullptr) { };

    /** Destructor that frees the memory of this node:
      * */
    ~NodeList() { }

  private:

    T data; // Information contained in the node.
    NodeList* next; // Pointer to another node.

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

    /** Sets the information:
      * @param newData Information of the element.
      * @result Enters the new information.
      * */
    void setData(const T& newData) {
      data = newData;
    }

    /** Gets the information:
      * @return Returns the information of the node.
      * */
    const T& getData() const {
      return data;
    }
};

#endif