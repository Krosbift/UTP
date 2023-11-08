#ifndef SKIPLIST_HH
#define SKIPLIST_HH

#include <iostream>
#include <random>
#include "node.hh"
#include "vector.hh"

template <typename T> class SkipList {
  public:


    /** Constructor por defecto de la SkipList:
      * Crea el nodo inicial que funcionara como cabecera de la lista, el
      * cual a puntara a los nodos siguientes y este no contendra dato.
      * @result instancia de la clase SkipList.
      * */
    SkipList() : header(new Node<T>()), width(header->getDirections().size()) { }


    /** Destructor por defecto de la SkipList:
      * Se encarga de liberar la memoria del heap si es necesario.
      * @result memoria de la SkipList liberada.
      * */
    ~SkipList() { } 


  private:


    Node<T>* header; // cabecera de la SkipList.
    int width; // ancho de la SkipList (número de datos).


    /** Devuelve cuantos niveles se le deben de añadir al nodo insertado:
      * Obtiene una semilla aleatoria del hardware si está disponible,
      * usa Mersenne Twister 19937 como generador, define una distribución 
      * uniforme para generar números [0 o 1] con la misma probabilidad de salir.
      * Luego se realiza un while para obtener la altura del nodo
      * donde se tomara el 0 como el caso de exito.
      * @return crecimiento total del nodo.
      * */
    int growthNode() {
      std::random_device rd;
      std::mt19937 generator(rd());
      std::uniform_int_distribution<int> distribution(0, 1);

      int totalGrowthLevels = 0;
      int numInAttempt = 0;

      while (!numInAttempt) {
        numInAttempt = distribution(generator);
        totalGrowthLevels++;
      }
      return totalGrowthLevels;
    }


  public:


    /** Se encarga de devovler la altura de la SkipList:
      * @return altura de la SkipList.
      * */
    int getHeight() {
      return header->getDirections().size();
    }


    /** Se encarga de devolver el ancho de la SkipList:
      * @return devuelve el total de datos de la SkipList.
      * */
    int getWidth() {
      return width;
    }


    /** Se encarga de devolver la información contenida por el nodo
      * que se desee buscar, si no la encuentra devuelve cero.
      * @return información del nodo o cero en si no la encontro
      * */
    T search(const T& data) {
      Node<T>* currentNode = header;

      for (int i = currentNode->getDirections().size() - 1; i >= 0; i--) {
        while (currentNode->getDirections()[i] != nullptr && currentNode->getDirections()[i]->getData() < data) {
          currentNode = currentNode->getDirections()[i];
        }

        if (currentNode->getDirections()[i] != nullptr && currentNode->getDirections()[i]->getData() == data) {
          return currentNode->getDirections()[i]->getData();
        }
      }

      return 0;
    }


    /** Inserta un nuevo nodo en la SkipList:
      * Analiza los dos posibles casos de inserción y elije a cual se debe proceder.
      * @param data el dato que se desea insertar.
      * @result Nuevo nodo con su información insertado.
      * */
    void insert(const T& data) {
      if (header->getDirections().size() == 0) {
        insertCaseOne(data);
      } else {
      insertCaseTwo(data);
      }
    }


    /** Caso uno del insert, lista sin datos:
      * inserta el nodo al inicio debido a que no hay mas nodos existes.
      * @param data el dato que se desea insertar.
      * @result Nodo insertado al inicio.
      * */
    void insertCaseOne(const T& data) {
      Node<T>* currentNode = header;
      Node<T>* newNode = new Node<T>(data);
      header->getDirections().push_back(newNode);
      int growthLevels = growthNode() - 1;
      for (int i = 0; i < growthLevels; i++) {
          header->getDirections().push_back(newNode);
      }
      width++;
    }


    /** Caso dos del insert, lista con datos:
      * busca el lugar donde se debe insertar el nuevo nodo con su información.
      * Luego se empieza a hacer el enlace entre el nuevo nodo, el anterior, el siguiente y
      * con el header si este nodo supera la altura base de la lista.
      * @param data el dato que se desea insertar.
      * @result Nodo insertado en la posición indicada, enlaces siguientes hechos.
      * */
    void insertCaseTwo(const T& data) {
      Node<T>* currentNode = header;
      Node<T>* newNode = new Node<T>(data);
      Vector<Node<T>*> path = Vector<Node<T>*>();

      for (int i = currentNode->getDirections().size() - 1; i >= 0; i--) {
              std::cout << " X " << i << std::endl;
        while (currentNode->getDirections()[i] != nullptr && currentNode->getDirections()[i]->getData() < data) {
          currentNode = currentNode->getDirections()[i];
        }
        path.push_back(currentNode);
      }

      if ((currentNode->getDirections()[0] == nullptr || currentNode->getDirections()[0]->getData() != data)) {
        int growthLevels = growthNode() - 1;
        int pathIndex = growthLevels - 1;

        for (int i = 0; i < growthLevels; i++) {
          if (i < currentNode->getDirections().size()) {
            Node<T>* previousNode = path[pathIndex];
            newNode->getDirections().push_back(previousNode->getDirections()[i]);
            previousNode->getDirections()[i] = newNode;
          } else {
            currentNode->getDirections().push_back(newNode);
          }
            pathIndex--;
        }
         width++;
      } else {
        delete newNode;
      }
    }


    /** Se encarga de eliminar un nodo de la SkipList:
      * @param data el dato que se desea eliminar.
      * @result Nodo eliminado de la SkipList.
      * */
    void remove(const T& data) {
      Node<T>* currentNode = header;
      Vector<Node<T>*> path = Vector<Node<T>*>();

      for (int i = currentNode->getDirections().size() - 1; i >= 0; i--) {
        while (currentNode->getDirections()[i] != nullptr && currentNode->getDirections()[i]->getData() < data) {
          currentNode = currentNode->getDirections()[i];
        }
        path.push_back(currentNode);
      }

      if (currentNode->getDirections()[0] != nullptr && currentNode->getDirections()[0]->getData() == data) {
        int pathIndex = path.size() - 1;

        for (int i = 0; i < path.size(); i++) {
          if (i < currentNode->getDirections().size()) {
            Node<T>* previousNode = path[pathIndex];
            previousNode->getDirections()[i] = currentNode->getDirections()[i];
          }
          pathIndex--;
        }
        delete currentNode;
        width--;
      }
    }
};

#endif