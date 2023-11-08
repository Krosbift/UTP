#ifndef SKIPLIST_HH
#define SKIPLIST_HH


#include <iostream>
#include <random>
#include <vector>


using namespace std;


/** Clase Nodo que se usa para crear la lista de saltos.
  * @class Node - Contrulle una structura con dos datos.
  * @private data - Información a almacenar.
  * @private next - Vector de nodos siguientes.
  * @include <iostream>, <random>, <vector>
  * */
template <typename T> class Node {
  public:

    /** Constructor por defecto de la clase Node:
      * @result inicializa con el elemento que se le ingreso, se le añade
      * un vector de nodos del nivel del que se va a generar y todas
      * estas posiciones se les define primero como nullptr inicialmente.
      * */
    Node(T data, int Level) : data(data), next(Level + 1, nullptr) { }


  public:


    T data; // Información almacenada por el nodo.
    vector<Node<T>*> next; // Vector que contendra las direcciones a los nodos siguientes.


};


/** Clase Nodo que se usa para crear la lista de saltos.
  * @class SkipList - Contrulle una structura con dos datos,
  * @private head - Nodo inicial de la lista.
  * @private Level - Nivel actual de la lista.
  * @include <iostream>, <random>, <vector>
  * */
template <typename T> class SkipList {
  public:


    /** Constructor por defecto de la clase SkipList:
      * @result
      * */
    SkipList() {
      head = new Node(0, 1);
      Level = 0;
    }


  private:


    Node<T>* head; // Nodo inicial de la lista.
    int Level; // Nivel actual de la lista.


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


    /** Inserta un nuevo nodo en la lista de saltos:
      * primero se encarga de ver cuantos niveles deben de añadirse,
      * crea un vector que guardara el recorrido o los nodos a actualizar,
      * hace un resize del primer nodo si es necesario, y luego se recorre
      * el vector de nodos siguientes para hallar en donde se debe hacer
      * la inserción y crea el nodo con los niveles que se generaran,
      * si el elemento ya existe no hace nada.
      * @param data - Información a almacenar.
      * @result nodo insertado en la lista de saltos.
      * */
    void insert(const T& data) {
      int newLevel = growthNode() - 1; 

      if (Level < newLevel) {
        head->next.resize(newLevel + 1, nullptr);
        Level = newLevel;
      }

      Node<T>* current = head;
      vector<Node<T>*> Update(Level + 1, nullptr);

      for (int i = Level; i >= 0; i--) {
        while (current->next[i] and current->next[i]->data < data)  {
          current = current->next[i];
        }
        Update[i] = current;
      }
      current = current->next[0];

      if (current == nullptr || current->data != data) {
        Node<T>* newNode = new Node<T>(data, Level);
        for (int i = 0; i <= newLevel; i++) {
          newNode->next[i] = Update[i]->next[i];
          Update[i]->next[i] = newNode;
        }
        cout << "Informacion " << data << " Insercion realizada con exito.\n";
      } else {
        cout << "Informacion " << data << " Informacion existente, insercion no realizada.\n";
      }
    }


    /** Elimina un nodo de la lista de saltos:
      * primera crea un nodo llamado actual y un vector, el nodo se usara
      * para ir moviendonos y el vector para guardar los nodos a actualizar.
      * luego se recorre la lista para hallar el nodo a elminar, si este
      * existe se elimina del heap y se hace el reenlace con los nodos
      * almacenados por el vector.
      * @param data - Información a eliminar.
      * @result nodo eliminado de la lista de saltos.
      * */
    void remove(const T& data) {
      Node<T>* current = head;
      vector<Node<T>*> Update(Level + 1, nullptr);

      for (int i = Level; i >= 0; i--) {
        while (current->next[i] and current->next[i]->data < data) {
          current = current->next[i];  
        }
        Update[i] = current;
      }

      current = current->next[0];

      if (current != nullptr and current->data == data) {
        for (int i = 0; i <= Level; i++) {
          if (Update[i]->next[i] != current) {
            i = level + 1;
          } else {
            Update[i]->next[i] = current->next[i];
          }
        }
        delete current;

        while (Level > 0 and head->next[Level] == nullptr) {
          Level--;
        }
        cout << "Informacion " << data << " Informacion eliminada con exito."<<endl;
      } else {
        cout << "Informacion " << data << " Informacion no encontrada."<<endl;
      }
    }


    /** Busca un nodo en la lista de saltos:
      * crea un nodo llamado actual para irnos moviendo,
      * busca el nodo en la lista con saltos, si lo encuentra avisa
      * que lo hallo y lo devuelve, de lo contrario avisa que no lo
      * hallo y devuelve un false.
      * @param data - Información a buscar.
      * @return información contenida por el nodo.
      * */
    T search(const T& data) {
      Node<T>* current = head;
      for (int i = Level; i >= 0; i--) {
        while (current->next[i] and current->next[i]->data < data) {
          current = current->next[i];
        }
      }

      current = current->next[0];

      if (current != nullptr && current->data == data) {
        cout << "Informacion " << data << " Informacion encontrada.\n";
        return current->data;
      } else {
        cout << "Informacion " << data << " Informacion no encontrada.\n";
        return false;
      }
    }


    /** sobrecarga del << de iostream:
      * @param os Referencia al puente de salida de ostream.
      * @result SkipList presentada en pantalla por niveles.
      * */
    friend std::ostream& operator<<(std::ostream& os, const SkipList<T>& skipList) {
      os << "skip List:" << std::endl;

      for (int i = skipList.Level; i >= 0; i--) {
        Node<T>* current = skipList.head->next[i];
        os << "Level " << i << ": ";
        while (current != nullptr) {
          os << current->data << " ";
          current = current->next[i];
        }
        os << std::endl;
      }

      return os;
    }
};


#endif // SKIPLIST_HH