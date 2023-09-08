#ifndef my_node

template <typename T>
class NodeList {
  public:

    /** Constructor que instancia un nodo sin datos para la lista:
      * @result Nodo sin información.
      * */
    NodeList() : data(T()), next(nullptr) { };

    /** Constructor que instancia un nodo con el elemento ingresado al constructor:
      * @result Nodo con la información añadida.
      * */
    NodeList(const T& data) : data(data), next(nullptr) { };

    /** Destructor que libera la memoria de este nodo:
      * */
    ~NodeList() { }

  private:

    T data; // información que contiene el noto.
    NodeList* next; // puntero a otro Nodo.

  public:

    /** Recibe un puntero a un nodo para conectar este nodo con el siguiente:
      * @param nextNode puntero al siguiente nodo.
      * @result ingresa el elemento en el nodo.
      * */
    void setNext(NodeList* nextNode) {
      next = nextNode;
    }

    /** Se encarga de devolver la direccion de memoria la siguiente nodo
      * @return dirección al nodo siguiente.
      * */
    NodeList* getNext() {
      return next;
    }

    /** Se encarga de ingresar la información a este nodo:
      * @param newData referencia al elemento que se va a agregar a este nodo.
      * @result Ingresa la nueva información.
      * */
    void setData(T& newData) {
      data = newData;
    }

    /** Se encarga de obtener la información del nodo seleccionado:
      * @return devuelve la información del nodo.
      * */
    const T& getData() {
      return data;
    }
};

#endif