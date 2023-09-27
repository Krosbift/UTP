#ifndef VECTOR_HH
#define VECTOR_HH

#include <my-exceptions.hh>
#include <iostream>

typedef unsigned int sz; // tipo para el tamaño del vector.

/** @class Libreria con la implementación básica de un vector:
  * 
  * */
template <typename T> class Vector {
  public:

    /** Constructor por defecto:
      * @result Inicializa sin elementos con capacidad de 5.
      * */
    Vector() : capacity(5), elements(0), storage(new T[capacity]) { }

    /** Constructor para elegir capacidad inicial:
      * @param cap capacidad inicial.
      * @result Inicializa sin elementos y con la capacidad elegida.
      * */
    Vector(sz cap): capacity(cap), elements(0), storage(new T[capacity]) { }

    /** Contructor de copia, con capacidad de cinco extra:
      * @param vector_copy vector a copiar.
      * @result Inicializa como copia de otro, con capacidad adicional.
      * */
    Vector(const Vector<T>& vector_copy): capacity(vector_copy.size() + 5), elements(vector_copy.size()), storage(new T[capacity]) {
      for (int i = 0; i < elements; ++i) {
        storage[i] = vector_copy[i];
      }
    }

    /** Destructor por defecto:
      * @result libera memoria no necesaria.
      * */
    ~Vector() {
      delete[] storage;
    }

  private:

    sz capacity; // capacida de elementos.
    sz elements; // cantidad de elemento.
    T* storage; // puntero de acceso.

  private:

  /** Incrementa la capacidad maxima:
    * @param increment factor de aumento.
    * @result capacidad aumentada.
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
    
    /** Sobre carga de corchetes, acceso a los elementos por indice:
      * @param index posicion del elemento.
      * @return elemento extraido por su indice.
      * */
    T& operator[](sz index) {
      if (index >= elements || index < 0) {
        throw Out_of_range("Indice fuera de rango");
      }
      return storage[index];
    }

    /** Sobre carga de igual, igualar esta instancia del objeto a otra:
      * @param vector_copy instancia del objeto a copiar.
      * @result instancia de este objeto igualada a otra.
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

    /** Sobre carga de operador de inserción para iostream:
      * @param os referencia a ostream output.
      * @param vector referencia a vector.
      * @result vector mostrado por consola.
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

    /** Obtiene el estado de almacenamiento:
      * @return true si es vacio, false si no.
      * */
    bool empty() const {
      return elements == 0;
    }

    /** Accede al número de elementos:
      * @return total de elementos. 
      * */
    sz size() const {
      return elements;
    };

    /** Accede a la diferencia entre la capacidad y los elementos:
      * @return espacio libre.
      * */
    sz waste() const {
      return capacity - elements;
    }

    /** Inserta un elemento al final:
      * @param element elemento a Insertar.
      * @result elemento Insertado al final.
      * */
    void push_back(const T& element) {
      if (capacity == elements) {
        resize(5);
      }
      
      storage[elements] = element;
      elements++;
    };

    /** Elimina el ultimo elemento:
      * @result elemento final eliminado.
      * */
    void pop_back() {
      if (empty()){
        throw Vector_empty("El vector esta vacio");
      }

      elements--;
    }

    /** Inserta un elemento al inicio:
      * @param element elemento a insertar.
      * @result elemento insertado al inico.
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

    /** Elimina el primer elemento:
      * @result elemento inicial eliminado.
      * */
    void pop_front() {
      if (empty()){
        throw Vector_empty("El vector esta vacio");
      }

      for (int i = 0; i < elements; i++) {
        storage[i] = storage[i + 1];
      }

      elements--;
    };

    /** Inserta un elemento en una posicion determinada:
      * @param element elemento a insertar.
      * @param index posicion donde se insertara el elemento.
      * @result elemento agregado en la posicion indicada.
      * */
    void insert(const T& element, sz index) {
      if (index >= elements || index < 0) {
        throw Out_of_range("Indice fuera de rango");
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

    /** Elimina el elemento de la posicion indicada:
      * @param index posicion del elemento a eliminar.
      * @result elemento eliminado.
      * */
    void remove(sz index) {
      if (empty()){
        throw Vector_empty("El vector esta vacio");
      }

      if (index >= elements || index < 0) {
        throw Out_of_range("Indice fuera de rango");
      }
      
      for (int i = index - 1; i < elements; i++) {
        storage[i] = storage[i + 1];
      }
      elements--;
    }

    /** Lleva el waste a cero:
      * @result capacidad reducidad a elements.
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

    /** Intercambia dos elementos:
      * @param first primer elemento.
      * @param second segundo elemento.
      * @result elementos intercambiados.
      * */
    void swap(sz first, sz second) {
      if (first >= elements || first < 0 || second >= elements || second < 0) {
        throw Out_of_range("Indice fuera de rango");
      }

      T temp = storage[first];
      storage[first] = storage[second];
      storage[second] = temp;
    }

    /** Accede al elemento de la posicion idicada:
      * @param index posición del elemento.
      * @return referencia al elemento accedido. 
      * */
    const T& at(sz index) const {
      if (index >= elements || index < 0) {
        throw Out_of_range("Indice fuera de rango");
      }
      return storage[index];
    }

    /** Inserta otro vector en una posicion determinada:
      * @param vector vector a insertar.
      * @param index posicion donde se insertara.
      * @result vector añadido a este objeto.
      * */
    void insert(const Vector<T>& vector, sz index) {
      if (index >= elements || index < 0) {
        throw Out_of_range("Indice fuera de rango");
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