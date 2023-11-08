#ifndef SKIPLISTNODE_HH
#define SKIPLISTNODE_HH

#include <vector.hh>

template <typename T> class Node {
  public:

    /** Constructor por defecto:
      * @result instancia del Nodo sin información
      * y la inicializacion del vector de punteros.
      * */
    Node() : data(T()), directions(Vector<Node<T>*>()) { }

    /** Constructor que copia el dato ingresado en su nodo:
      * @result instancia del Nodo con el dato ingresado
      * y la inicializacion del vector de punteros.
      * */
    Node(const T& data) : data(data), directions(Vector<Node<T>*>()) { }

    /** Destructor por defecto:
      * */
    ~Node() { }

  private:

    T data; // información contenida por el nodo.
    Vector<Node<T>*> directions; // vector de direcciones a los nodos siguientes.

  public:

    /** Devuelve la información contenida por el nodo:
      * @return referencia a la información contenida por el nodo.
      * */
    T getData() {
      return data;
    }

    /** Devuelve el vector de direcciones a los nodos siguientes:
      * @return referencia al vector de direcciones a los nodos siguientes.
      * */
    Vector<Node<T>*>& getDirections() {
      return directions;
    }

    /** Permite asignar una nueva información al nodo:
      * @param newData información a asignar al nodo.
      * @result asigna la nueva información al nodo.
      * */
    void setData(const T& newData) {
      data = newData;
    }

    /** Permite asignar un nuevo vector de direcciones a los nodos siguientes:
      * @param newDirections vector de direcciones a los nodos siguientes.
      * @result asigna el nuevo vector de direcciones a los nodos siguientes.
      * */
    void setDirections(Vector<Node<T>*> newDirections) {
      directions = newDirections;
    }

    /** Permite cambiar una sola direccion del vector de punteros a los nodos:
      * @param node nodo a asignar a la direccion.
      * @param index posicion de la direccion a cambiar.
      * @result cambia la direccion del vector de punteros a los nodos.
      * */
    void setDirection(Node<T>* node, int index) {
      directions[index] = node;
    }
};

#endif