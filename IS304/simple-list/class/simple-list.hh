#ifndef my_simple_list

#include <iostream>
#include <node.hh>

typedef unsigned int sz;

template <typename T>
class simpleList {
  public:

    /** Constructor de la clase simpleList, que inicializar la lista vacia:
      * @result se encarga de instanciar la clase simpleList sin ningun elemento.
      * */
    simpleList() : first(nullptr), last(nullptr), nodes(0) { };

    /** Destructor de la clase simpleList, que libera la memoria de la lista.
      * @result se encarga de eliminar la memoria que no se haya borrado.
      * */
    ~simpleList() {
      NodeList<T>* current = first;
      while (current != nullptr) {
        delete current;
        current = current->getNext();
      }
    };

  private:
  
    NodeList<T>* first; // puntero al primer elemento de la lista.
    NodeList<T>* last; // puntero al ultimo elemento de la lista.
    sz nodes; // total de nodo en la lista
  
    /** Se encarga de ver si la lista esta vacia o no.
      * @return devuelve true si la lista esta vacia, false si no.
      * */
    bool empty() {
      return first == nullptr;
    }

  public:

    /** Se encarga de devolver el total de elementos de la lista.
      * @return devuelve el total de elementos de la lista. 
      * */
    sz size() const {
      return nodes;
    }

  /** Crea un nuevo nodo y lo enlaza al final junto con la
    * información que guardara.
    * @result agrega un nuevo nodo al final de la lista.
    * */
    void push_back(T data) {
      NodeList<T>* new_node = new NodeList<T>(data);

      if (empty()) {
        first = new_node;
      } else {
        last->setNext(new_node);
      }
      
      last = new_node;
      nodes++;
    }

    /** Crea un nuevo nodo y lo enlaza al inicio junto con la
      * información que guardara.
      * @result agrega un nuevo nodo al inicio de la lista.
      * */
    void push_front(T data) {
      NodeList<T>* new_node = new NodeList<T>(data);

      new_node->setNext(first);
      first = new_node;

      nodes++;
    }

    /** Elimina el ultimo nodo de la lista junto con su información.
      * @result la lista queda sin el ultimo nodo.
      * */
    void pop_back() {
      if (empty()) {
        throw std::domain_error("list is empty");
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

    /** Elimina el primer nodo de la lista junto con su información.
      * @result la lista queda sin el primer nodo.
      * */
    void pop_front() {
      if (empty()) {
        throw std::domain_error("list is empty");
      }

      NodeList<T>* node_aux = first;
      node_aux = node_aux->getNext();

      delete first;
      first = node_aux;
      
      nodes--;
    }

    /** Se encarga de insertar un nuevo nodo a la lista con la informacion deseada.
      * @param data informacion que se desea insertar en la lista.
      * @param pos posicion en la que se desea insertar un nuevo elemento.
      * @result inserta un nuevo elemento a la lista en la posicion indicada.
      * */
    void insert_element(T data, sz pos) {
      if (pos < 0 || pos >= nodes) {
        throw std::out_of_range("Index out of range");
      }
      
      NodeList<T>* new_node = new NodeList<T>(data);

      NodeList<T>* node_ant = first;
      NodeList<T>* node_pos = first;

      for (sz i = 0; i < pos; i++) {
        node_ant = node_ant->getNext();
        node_pos = node_pos->getNext();
      }
      node_pos = node_pos->getNext();
      
      node_ant->setNext(new_node);
      new_node->setNext(node_pos);

      nodes++;
    }

    /** Devuelve una referencia que contiene la información del nodo en la posicion indicada.
      * @param pos posicion en la que se encuentra el nodo.
      * @return referencia con el elemento en la posicion indicada.
      * */
    const T& at(sz pos) const {
      if (pos < 0 || pos >= nodes) {
        throw std::out_of_range("Index out of range");
      }

      NodeList<T>* node_aux = first;

      for (int i = 0; i < pos; i++) {
        node_aux = node_aux->getNext();
      }
      
      return node_aux->getData();
    }

    /** Inserta una lista dentro de esta lista desde la posicion indicada.
      * @param list lista a insertar.
      * @param pos posicion en la que se desea insertar la lista.
      * @result la lista con los nuevos elementos insertados.
      * */
    void insert_copylist(const simpleList<T>& list, sz pos) {
      if (pos < 0 || pos >= nodes) {
        throw std::out_of_range("Index out of range");
      }
      NodeList<T>* node_aux = list.first;
      
      for (int i = 0; i < list.size(); i++) {
        this->insert_element(node_aux->getData(), pos + i);
        node_aux = node_aux->getNext();
      }
    }

    /** Inserta una lista dentro de esta lista desde la posicion indicada.
      * @param list lista a insertar.
      * @param pos posicion en la que se desea insertar la lista.
      * @result la lista con los nuevos elementos insertados.
      * */
    void insert_heistlist(simpleList<T>& list, sz pos) {
      if (pos < 0 || pos >= nodes) {
        throw std::out_of_range("Index out of range");
      }
      
      NodeList<T>* node_ant = first;
      NodeList<T>* node_pos = first;

      for (sz i = 0; i < pos; i++) {
        node_ant = node_ant->getNext();
        node_pos = node_pos->getNext();
      }
      node_pos = node_pos->getNext();

      NodeList<T>* new_node = list.first;
      node_ant->setNext(new_node);

      for (int i = 0; i < (list.size() - 1); i++) {
        new_node = new_node->getNext();
      }

      new_node->setNext(node_pos);
      nodes += list.size();
      list.nodes = 0;
    }

    /** Elimina un nodo de la lista junto con su informacion.
      * @param pos posicion en la que se encuentra el nodo.
      * @result la lista queda sin el nodo en la posicion indicada.
      * */
    void remove_element(sz pos) {
      if (empty()) {
        throw std::domain_error("list is empty");
      }

      if (pos < 0 || pos >= nodes) {
        throw std::out_of_range("Index out of range");
      }

      NodeList<T>* node_ant = first;
      NodeList<T>* node_pos = first;
      NodeList<T>* node_del = first;

      for (sz i = 0; i < pos; i++) {
        node_del = node_del->getNext();
        node_pos = node_pos->getNext();
        if (i != (pos - 1)) {
          node_ant = node_ant->getNext();
        }
      }
      node_pos = node_pos->getNext();
      node_ant->setNext(node_pos);

      delete node_del;
      nodes--;
    }
};

#endif