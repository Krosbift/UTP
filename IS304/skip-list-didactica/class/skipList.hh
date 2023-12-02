#ifndef SKIPLIST_HH
#define SKIPLIST_HH


#include <iostream>
#include <random>
#include <vector>


using namespace std;


/** Clase Nodo que se usa para crear la lista de saltos.
  * @class Node - Contrulle una structura con dos datos.
  * @private key - Clave de la información a almacenar.
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
    Node(int key, T data, int Level) : key(key), data(data), next(Level + 1, nullptr) { }


  public:


    int key; // Clave de la información a almacenar.
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
      head = new Node(0, 0, 1);
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
      random_device rd;
      mt19937 generator(rd());
      uniform_int_distribution<int> distribution(1, 100);

      int totalGrowthLevels = 0;
      int numGenerate = 0;

      while (numGenerate <= 50) {
        numGenerate = distribution(generator);
        totalGrowthLevels++;
      }
      totalGrowthLevels--;
      
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
      * @param key - Clave de la información a almacenar.
      * @param data - Información a almacenar.
      * @result nodo insertado en la lista de saltos.
      * */
    void insert(const int& key, const T& data) {
      int newLevel = growthNode(); 

      if (Level < newLevel) {
        head->next.resize(newLevel + 1, nullptr);
        Level = newLevel;
      }

      Node<T>* current = head;
      vector<Node<T>*> Update(Level + 1, nullptr); 

      for (int i = Level; i >= 0; i--) {
        while (current->next[i] && current->next[i]->key < key)  {
          current = current->next[i];
        }
        Update[i] = current;
      }
      current = current->next[0];

      if (current == nullptr || current->key != key) {
        Node<T>* newNode = new Node<T>(key, data, Level);
        for (int i = 0; i <= newLevel; i++) {
          newNode->next[i] = Update[i]->next[i];
          Update[i]->next[i] = newNode;
        }
        cout << "Informacion con llave " << key << " y valor " << data << " se inserto con exito.\n";
      } else {
        cout << "Llave " << key << " existente, insercion no realizada.\n";
      }
    }


    /** Elimina un nodo de la lista de saltos:
      * primera crea un nodo llamado actual y un vector, el nodo se usara
      * para ir moviendonos y el vector para guardar los nodos a actualizar.
      * luego se recorre la lista para hallar el nodo a elminar, si este
      * existe se elimina del heap y se hace el reenlace con los nodos
      * almacenados por el vector.
      * @param key - clave de la información a eliminar.
      * @result nodo eliminado de la lista de saltos.
      * */
    void remove(const T& key) {
      Node<T>* current = head;
      vector<Node<T>*> Update(Level + 1, nullptr);

      for (int i = Level; i >= 0; i--) {
        while (current->next[i] && current->next[i]->key < key) {
          current = current->next[i];  
        }
        Update[i] = current;
      }

      current = current->next[0];

      if (current != nullptr && current->key == key) {
        for (int i = 0; i <= Level; i++) {
          if (Update[i]->next[i] != current) {
            i = i + (Level + 1);
          } else {
            Update[i]->next[i] = current->next[i];
          }
        }
        delete current;

        while (Level > 0 && head->next[Level] == nullptr) {
          Level--;
        }
        cout << "Llave " << key << " eliminada con exito."<<endl;
      } else {
        cout << "Llave " << key << " no existente."<<endl;
      }
    }


    /** Busca un nodo en la lista de saltos:
      * crea un nodo llamado actual para irnos moviendo,
      * busca el nodo en la lista con saltos, si lo encuentra avisa
      * que lo hallo y lo devuelve, de lo contrario avisa que no lo
      * hallo y devuelve un false.
      * @param key - clave de la información a buscar.
      * @return información contenida por el nodo.
      * */
    T search(const T& key) {
      Node<T>* current = head;
      for (int i = Level; i >= 0; i--) {
        while (current->next[i] && current->next[i]->key < key) {
          current = current->next[i];
        }
      }

      current = current->next[0];

      if (current != nullptr && current->key == key) {
        cout << "Informacion con llave " << key << " encontrada.\n";
        return current->data;
      } else {
        cout << "Informacion con llave " << key << " no encontrada.\n";
        return false;
      }
    }

    
    /** sobrecarga del << de iostream:
      * @param os Referencia al puente de salida de ostream.
      * @result SkipList presentada en pantalla por niveles.
      * */
    friend std::ostream& operator<<(std::ostream& os, const SkipList<T>& skipList) {
      if (skipList.head == nullptr) {
        os << "La lista está vacía." << std::endl;
        return os;
      }

      os << "skip List:" << std::endl;

      for (int i = skipList.Level; i >= 0; i--) {
        int previusSpaces = 0;
        Node<T>* base = skipList.head->next[0];
        Node<T>* current = skipList.head->next[i];
        if (i >= 10) {
          os << "Level " << i << ": ";
        } else {
          os << "Level  " << i << ": ";
        }
        while (current != nullptr) {
          int Spaces = previusSpaces;
          int temporal = Spaces;
          while (base != nullptr && base->key <= current->key && i != 0) {
            if (temporal != 0) {
              if (base->key >= 0) {
                if (base->key > 9) {
                  os << "   ";
                } else {
                  os << "  ";
                }
              } else {
                os << "   ";
              }
            }
            base = base->next[0];
            temporal--;
          }
          previusSpaces = Spaces;
          os << current->key << " ";
          current = current->next[i];
        }
        os << endl;
      }

      return os;
    }
};


#endif // SKIPLIST_HH