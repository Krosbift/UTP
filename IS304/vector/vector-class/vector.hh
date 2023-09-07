#ifndef myvector

typedef unsigned int sz;

template <typename T>
class vector {
  public:

    /** Constructor vacio para inicializar un vector sin elementos.
      * con capacidad de 5 y 0 elementos.
      * @result instancia de una clase con un vector sin elementos.
      * */
    vector() : capacity(5), elements(0), storage(new T[capacity]) { };

    /** Constructor para inicializar una instancia de la clase vector con la capacidad
      * que pase como parametro:
      * @result instancia de una clase con un vector sin elementos y con la capacidad elejida.
      * */
    vector(sz cap): capacity(cap), elements(0), storage(new T[capacity]) { }

    /** Contructor para inicializar un vector como copia de un array, con una capacidad de cinco
      * superior a la del array que se ha copiado:
      * @param array arreglo que se va a copiar y a generar como un vector.
      * @param size tamaño del arreglo que se va copiar.
      * @result la instancia de la clase con un vector que es copia del array añadido como parametro.
      * */
    vector(T *array_copy, sz size): capacity(size + 5), elements(size), storage(new T[capacity]) {
      for (int i = 0; i < elements; ++i) {
        storage[i] = array_copy[i];
      }
    }

    /** Destructor para liberar memoria del heap ocupada por el vector:
      * */
    ~vector() {
      delete[] storage;
    }

  private:
    sz capacity;
    sz elements;
    T* storage;

  /** Función que aumenta la capacidad del vector de elementos.
    * @param increment es el factor de aumento.
    * @result aumenta la capacidad del vector.
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

    /** Se encarga de devolver si el vector esta vacio o no,
      * devolviendo verdadero de estar vacio y falso en caso contrario
      * */
    int empty() {
      return elements == 0;
    }

  public:
    
    /** operador de sobrecarga para los corchetes, para acceder a los elementos del vector
      * con la notacion de acceso comun de arreglos.
      * @return el elemento del vector en la posicion indicada.
      * */
    T& operator[](sz posicion) {
      if (posicion >= elements || posicion < 0) {
        throw std::out_of_range("Index out of range");
      }
      return storage[posicion];
    }


    /** Retorna el total de elementos del vector.
      * @return total de elementos del vector. 
      * */
    sz size() {
      return elements;
    };

    /** Retorna el total de capacidad del vector libre.
      * @return espacio libre del vector.
      * */
    sz waste() {
      return capacity - elements;
    }

    /** Agrega un elemento al final del vector.
      * @param element es el elemento a agregar al vector.
      * @result agrega el elemento al final del vector.
      * */
    void push_back(T element) {
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
      if (posicion > elements && posicion < 0) {
        throw std::domain_error("Index out of range");
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
      T* new_storage = new T[elements];
      
      for (int i = 0; i < elements; i++) {
        new_storage[i] = storage[i];
      }

      delete[] storage;
      storage = new_storage;
      capacity = elements;
    }


    /** Devuelve una referencia hacia el elemento de una posición especifica del vector:
      * @param posicion posición del elemento que se va a devolver como referencia.
      * @return una referencia con la direccion al valor almacena en la posicion solicitada. 
      * */
    T& at(sz posicion) {
      if (posicion >= elements || posicion < 0) {
        throw std::out_of_range("Index out of range");
      }
      return storage[posicion];
    }

    /** Cambia el valor de un elemento del vector a partir de su posición:
      * @param posicion posición del elemento que se va a cambiar.
      * @param element es el elemento que se va a asignar a la posicion.
      * @result el vector con el nuevo elemento en la posicion indicada. 
      * */
    void at(sz posicion, T& element) {
      if (posicion >= elements || posicion < 0) {
        throw std::out_of_range("Index out of range");
      }
      storage[posicion] = element;
    }

    /** Se encarga de insertar un vector en un determinado lugar del vector, teniendo en cuenta que la posición 0 sera la inicial tambien conocida como el primer elemento. (vector[0]).
      * @param other_vector es el vector a insertar.
      * @param posicion es la posicion donde se insertara el vector.
      * @result el vector con el nuevo vector en la posicion indicada.
      * */
    void insert_vector(vector<T>& other_vector, sz posicion) {
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
    }
};

#endif 