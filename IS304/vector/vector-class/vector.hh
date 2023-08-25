#ifndef mivector
#define mivector

#include <iostream>
#include <string>

template <typename T>
class vector {
  public:

    /** Constructor para inicializar un vector vacío, con una capacidad de 10:
      * @return la instancia de la clase con un vector vacío.
      * */
    vector(): capacity(10), elements(0) {
      pointer_vector = new T[capacity];
    }

    /** Contructor para inicializar un vector como copia de un array, con una capacidad de cinco
      * superior a la del array que se ha copiado:
      * @param array arreglo que se va a copiar y a generar como un vector.
      * @param size tamaño del arreglo que se va copiar.
      * @return la instancia de la clase con un vector que es copia del array añadido como parametro.
      * */
    vector(T *array_copy, size_t size): capacity(size + 5), elements(size) {
      pointer_vector = new T[capacity];
      for (size_t i = 0; i < elements; ++i) {
        pointer_vector[i] = array_copy[i];
      }
    }

    /** Constructor para inicializar un vector como copia de otro, generado con esta misma clase:
      * @param vector_copy vector que se va a copiar en la instancia de esta clase.
      * @return la instancia de la clase con un vector que es copia del añadido como parametro.
      * */
    vector(const vector &vector_copy): capacity(vector_copy.capacity), elements(vector_copy.elements) {
      pointer_vector = new T[capacity];
      for (size_t i = 0; i < elements; ++i) {
        pointer_vector[i] = vector_copy.pointer_vector[i];
      }
    }

    /** Destructor para liberar memoria:
      * */
    ~vector() {
      delete[] pointer_vector;
    }
  
  private:

    T* pointer_vector;
    size_t capacity;
    size_t elements;
  
  public:

    /** Se encarga de copiar un vector usando el operador de asignación:
      * @param vector_copy vector que se va a copiar.
      * @return dirección de memoria a la instancia de la clase con el vector nuevo.
      * */
    vector& operator=(const vector &vector_copy) {
      if (this != &vector_copy) {
        delete[] pointer_vector;

        capacity = vector_copy.capacity;
        elements = vector_copy.elements;

        pointer_vector = new T[capacity];
        for (size_t i = 0; i < elements; ++i) {
          pointer_vector[i] = vector_copy.pointer_vector[i];
        }
      }
      return *this;
    }

    /** Devuelve el elemento de una posición especifica del vector:
      * @param index posición del elemento que se va a devolver.
      * @return el valor del elemento en la posición indicada. 
      * */
    T at(size_t index) const {
      if (index >= elements) {
        throw std::out_of_range("Index out of range");
      }
      return pointer_vector[index];
    }

    /** Se encarga de devolver el total de elementos que existen en el vector:
      * */
    size_t size() const {
      return elements;
    }

    /** Se encarga de agregar un elemento al final del vector, agrega mas espacio de memoria si es necesario,
      * pidiendo mas memoria al heap y asignando este puntero recibido al atributo que tenia la dirección
      * a la primera posición del vector anterior:
      * @param value valor que se va a agregar al final del vector.
      * */
    void push_back(const T &value) {
      if (elements == capacity) {
        capacity += 100;
        
        T* new_pointer_vector = new T[capacity];
        for (size_t i = 0; i < elements; ++i) {
          new_pointer_vector[i] = pointer_vector[i];
        }

        delete[] pointer_vector;
        pointer_vector = new_pointer_vector;
      }
      pointer_vector[elements++] = value;
    }

    /** Se encarga de eliminar el ultimo elemento del vector, si es necesario, reducir la capacidad del vector
      * de memoria que ocupa el vector para no usar mas memoria de la necesaria:
      * */
    void pop_back() {
      if (!empty()) {
        throw std::out_of_range("vector is empty");
      }
      if ((elements + 10) == capacity) {
        capacity -= 5;

        T* new_pointer_vector = new T[capacity];
        for (size_t i = 0; i < (elements - 1); i++) {
          new_pointer_vector[i] = pointer_vector[i];
        }

        delete[] pointer_vector;
        pointer_vector = new_pointer_vector;
      }
      elements--;
    }

    /** Elimina todos los elementos del vector, reseteando la capacidad del vector a 10 y el numero de elementos a 0:
      * */
      void clear() {
        if (!empty()) {
          throw std::out_of_range("vector is empty");
        }
        capacity = 10;
        elements = 0;
        T* new_pointer_vector = new T[capacity];

        delete[] pointer_vector;
        pointer_vector = new_pointer_vector;
      }

      /** Se encarga de devolver si el vector esta vacio o no:
        * */
      bool empty() const {
        if (elements == 0){
          return true;
        }
        return false;
      }
};

#include <vector.hh>
#endif