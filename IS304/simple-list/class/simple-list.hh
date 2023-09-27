#ifndef SIMPLE_LIST_HH
#define SIMPLE_LIST_HH

#include <my-exceptions.hh>
#include <node.hh>

typedef unsigned int sz; // tipo de dato para tamaño de la lista.

/** @class Libreria con la implementación básica de la lista simple:
  * 
  * */
template <typename T> class simpleList {
  public:

    /** Constructor por defecto:
      * @result inicializa sin nodos.
      * */
    simpleList() : first(nullptr), last(nullptr), nodes(0) { }

    /** Constructor de copia:
      * @result inicializa con la copia de la lista.
      * */
    simpleList(const simpleList& list_copy) : first(nullptr), last(nullptr), nodes(0) {
      if (list_copy.empty()) {
        throw Invalid_argument("La lista a copiar esta vacia");
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

    /** Destructor que libera la memoria:
      * @result memoria liberada.
      * */
    ~simpleList() {
      NodeList<T>* current = first;
      while (current != nullptr) {
        delete current;
        current = current->getNext();
      }
    }

  private:
  
    NodeList<T>* first; // puntero al primer elemento.
    NodeList<T>* last; // puntero al ultimo elemento.
    sz nodes; // total de nodos.

  public:

    /** Sobre carga de los parentesis, acceso a informacion del nodo por indice:
      * @param index indice del nodo.
      * @return elemento extraido del nodo.
      * */
    T& operator()(sz index) {
      if (index < 0 || index >= nodes) {
        throw Out_of_range("Indice fuera de rango");
      }

      NodeList<T>* node_aux = first;

      for (int i = 0; i < index; i++) {
        node_aux = node_aux->getNext();
      }
      
      return node_aux->getData();
    }

    /** Sobre carga del igual, igualar esta instancia del objeto a otra:
      * @param list_copy instancia del objeto a copiar
      * @result instancia de este objeto igualada a otra.
      * */
    void operator=(const simpleList& list_copy) {
      if (list_copy.empty()) {
        throw Invalid_argument("La lista a copiar esta vacia");
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

    /** Sobre carga de operador de inserción para iostream:
      * @param os referencia a ostream output.
      * @param vector referencia a lista.
      * @result lista mostrada por consola.
      * */
    friend std::ostream& operator<<(std::ostream& os, const simpleList<T>& lista) {
      os << "[";
      for (int i = 0; i < lista.size(); ++i) {
        os << lista.at(i);
        if (i < lista.size() - 1) {
          os << ", ";
        }
      }
      os << ")";
      return os;
    }

    /** Obtiene el estado de almacenamiento:
      * @return true si es vacio, false si no.
      * */
    bool empty() const {
      return first == nullptr;
    }

    /** Accede al número de elementos:
      * @return total de elementos. 
      * */
    sz size() const {
      return nodes;
    }

    /** Inserta un nuevo nodo al final:
      * @param data informacion a almacenar.
      * @result nodo agregado al final.
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

    /** Elimina el ultimo nodo:
      * @result ultimo nodo elimnado.
      * */
    void pop_back() {
      if (empty()) {
        throw List_empty("la lista esta vacia");
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

    /** Inserta un elemento al inicio:
      * @param data informacion a almacenar.
      * @result nodo agregado al inicio.
      * */
    void push_front(const T& data) {
      NodeList<T>* new_node = new NodeList<T>(data);

      new_node->setNext(first);
      first = new_node;

      nodes++;
    }

    /** Elimina el primer nodo:
      * @result primer nodo eliminado.
      * */
    void pop_front() {
      if (empty()) {
        throw List_empty("las lista esta vacia");
      }

      NodeList<T>* node_aux = first;
      node_aux = node_aux->getNext();

      delete first;
      first = node_aux;
      
      nodes--;
    }

    /** Inserta un nodo en la posicion indicada:
      * @param data informacion a almacenar.
      * @param index posicion a insertar.
      * @result nodo insertado en la posicion indicada.
      * */
    void insert(const T& data, sz index) {
      if (index < 0 || index >= nodes) {
        throw Out_of_range("Indice fuera de rango");
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

      for (sz i = 0; i < pos - 1; i++) {
        node_ant = node_ant->getNext();
        node_pos = node_pos->getNext();
      }
      node_pos = node_pos->getNext();
      
      node_ant->setNext(new_node);
      new_node->setNext(node_pos);

      nodes++;
    }

    /** Accede al elemento de la posicion idicada:
      * @param index posición del elemento.
      * @return referencia al elemento del nodo accedido. 
      * */
    const T& at(sz index) const {
      if (index < 0 || index >= nodes) {
        throw Out_of_range("Indice fuera de rango");
      }

      NodeList<T>* node_aux = first;

      for (int i = 0; i < index; i++) {
        node_aux = node_aux->getNext();
      }
      
      return node_aux->getData();
    }

    /** Elimina un nodo de la lista:
      * @param index posicion del nodo.
      * @result nodo eliminado.
      * */
    void remove(sz index) {
      if (empty()) {
        throw List_empty("la lista esta vacia");
      }

      if (index < 0 || index >= nodes) {
        throw Out_of_range("indice fuera de rango");
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

    // ! por corregir

    /** Inserta una lista desde la posicion indicada:
      * @param list lista a insertar.
      * @param index posicion a insertar.
      * @result union de la copia de la lista y la original.
      * */
    void insert_copylist(const simpleList<T>& list, sz index) {
      if (index < 0 || index >= nodes) {
        throw Out_of_range("indice fuera de rango");
      }
      NodeList<T>* node_aux = list.first;
      
      for (int i = 0; i < list.size(); i++) {

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

    // ! por corregir
};

#endif