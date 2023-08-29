#ifndef mivector

#include <iostream>

typedef unsigned int sz;

template <typename T>
class vector {
  public:

    /** Constructor vacio para inicializar un vector sin elementos.
      * con capacidad de 5 y 0 elementos.
      * @result instancia de una clase con un vector sin elementos.
      * */
    vector() : capacity(5), elements(0), storage(new T[capacity]) {
      std::cout << "Constructor simple llamado.\n";
    };

  private:
    sz capacity;
    sz elements;
    T* storage;

  /** Función que aumenta la capacidad del vector de elementos.
    * @param increment es el factor de aumento.
    * @result aumenta la capacidad del vector.
    * */
    void resize(int increment) {
      std::cout << "Resize ejecutado.\n";

      capacity += increment;
      T* new_storage = new T[capacity];

      for (int i = 0; i < elements; i++) {
        new_storage[i] = storage[i];
      };

      delete[] storage;
      storage = new_storage;
    }

    /** Se encarga de devolver si el vector esta vacio o no:
      * */
    int empty() {
      if (elements == 0){
        return 1;
      }
      return 0;
    }
  public:
    
    /** Retorna el total de elementos del vector.
      * @return total de elementos del vector. 
      * */
    sz size() {
      std::cout << "Size ejecutado.\n";

      return elements;
    };

    /** Retorna el total de capacidad del vector libre.
      * @return espacio libre del vector.
      * */
    sz waste() {
      std::cout << "Waste ejecutado.\n";

      return capacity - elements;
    }

    /** Agrega un elemento al final del vector.
      * @param element es el elemento a agregar al vector.
      * @result agrega el elemento al final del vector.
      * */
    void push_back(T element) {
      std::cout << "Push back ejecutado.\n";

      if (capacity == elements) {
        resize(5);
      }
      
      storage[elements] = element;
      elements++;
    };

    /** Elimina el ultimo elemento del vector.
      * @result elimina el ultimo elemento del vector.
      * */
    void pop_back() {
      std::cout << "Pop back ejecutado.\n";

      if (empty()){
        throw std::domain_error("vector is empty");
      }

      elements--;
    }

    /** Inserta un elemento al inicio del arreglo y corre el resto
      * @param element es el elemento a insertar.
      * @result el vector con el nuevo elemento al inico.
      * */
    void push_front(T element) {
      std::cout << "Push front ejecutado.\n";

      if (capacity == elements) {
        resize(5);
      }

      for (int i = elements; i > 0; i--) {
        storage[i] = storage[i - 1];
      }      

      storage[0] = element;
      elements++;
    }

    /** Elimina el primer elemento del vector.
      * @result el vector sin el primer elemento.
      * */
    void pop_front() {
      std::cout << "Pop front ejecutado.\n";

      if (empty()){
        throw std::domain_error("vector is empty");
      }

      for (int i = 0; i < elements; i++) {
        storage[i] = storage[i + 1];
      }

      elements--;
    };

    /** Inserta un elemento en una posicion determinada del vector.
      * @param element es el elemento a insertar.
      * @param posicion es la posicion donde se insertara el elemento.
      * @result el vector con el nuevo elemento en la posicion indicada.
      * */
    void insert_element(T element, sz posicion) {
      std::cout << "Insert element ejecutado.\n";

      if (posicion > elements && posicion < 0) {
        throw std::domain_error("la posicion excede el rango");
      }

      if (capacity == elements) {
        resize(5);
      }
      
      for (int i = elements; i > posicion; i--) {
        storage[i] = storage[i - 1];
      }

      storage[posicion] = element;
      elements++;
    }

    /** Elimina un elemento del vector a partir de la posicion.
      * @param posicion es la posicion del elemento a eliminar.
      * @result el vector sin el elemento en la posicion indicada.
      * */
    void remove(sz posicion) {
      std::cout << "Remove ejecutado.\n";

      if (empty()){
        throw std::domain_error("vector is empty");
      }

      if (posicion > elements && posicion < 0) {
        throw std::domain_error("la posicion excede el rango");
      }
      
      for (int i = posicion - 1; i < elements; i++) {
        storage[i] = storage[i + 1];
      }
      elements--;
    }

    /** Se encarga de reducir la capacidad del vector a la de elementos que tenga
      * @result el vector con la nueva capacidad.
      */
    void shrink_to_fit() {
      std::cout << "Shrink to fit ejecutado.\n";

      T* new_storage = new T[elements];
      
      for (int i = 0; i < elements; i++) {
        new_storage[i] = storage[i];
      }

      delete[] storage;
      storage = new_storage;
      capacity = elements;
    }


    /** Devuelve una referencia hacia el elemento de una posición especifica del vector:
      * @param index posición del elemento que se va a devolver como referencia.
      * @return una referencia con la direccion al valor almacena en la posicion solicitada. 
      * */
    T& at(sz posicion) {
      std::cout << "at ejecutado.\n";

      if (posicion >= elements || posicion < 0) {
        throw std::out_of_range("Index out of range");
      }
      return storage[posicion];
    }

    /** Se encarga de insertar un vector en un determinado lugar del vector, teniendo en cuenta que la posición 0 sera la inicial tambien conocida como el primer elemento. (vector[0]).
      * @param other_vector es el vector a insertar.
      * @param posicion es la posicion donde se insertara el vector.
      * @result el vector con el nuevo vector en la posicion indicada.
      * */
    void insert_vector(vector<T>& other_vector, sz posicion) {
      std::cout << "Insert vector ejecutado.\n";

      if (posicion >= elements || posicion < 0) {
        throw std::domain_error("la posicion excede el rango");
      }

      sz increment = other_vector.elements;
      if (capacity < elements + increment) {
        resize(elements + increment);
      }

      for (int i = elements - 1; i >= (int)posicion; i--) {
        storage[i + increment] = storage[i];
      }

      for (int i = 0; i < increment; i++) {
        storage[posicion + i] = other_vector.storage[i];
      }

      elements += increment;

      for (int i = 0; i < elements; i++) {
        std::cout << storage[i] << " ";
      }
    }
};

#endif 