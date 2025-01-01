#ifndef TREAP_HH
#define TREAP_HH

#include <iostream>
#include <cstdlib>
#include <string>
#include <ctime>

using namespace std;

template <typename K, typename V>
class TreapNode {
    public:
    K key;                 ///< La clave del nodo.
    V value;               ///< El valor asociado a la clave.
    int priority;          ///< La prioridad utilizada para equilibrar el Treap.
    TreapNode<K, V>* left; ///< Puntero al hijo izquierdo.
    TreapNode<K, V>* right;///< Puntero al hijo derecho.

    /**
     *Constructor de la clase TreapNode.
     *
     * Crea un nuevo nodo con la clave y valor especificados. La prioridad se asigna
     * aleatoriamente para garantizar un equilibrio aleatorio del Treap.
     *
     * @param key La clave del nodo.
     * @param value El valor asociado a la clave.
     */
    TreapNode(const K& key, const V& value)
        : key(key), value(value), priority(rand()), left(nullptr), right(nullptr) {}
};

template<typename K, typename V>
class TreapMap{
    private:
    TreapNode<K, V>* root;  // Puntero a la raíz del Treap

    // Función para rotar un nodo hacia la izquierda
    void rotateLeft(TreapNode<K, V>*& node) {
        // Guarda una referencia al subárbol derecho del nodo
        TreapNode<K, V>* temp = node->right;

        // Actualiza los punteros para realizar la rotación
        node->right = temp->left;
        temp->left = node;

        // Actualiza el nodo a la nueva raíz después de la rotación
        node = temp;
    }

    // Función para rotar un nodo hacia la derecha
    void rotateRight(TreapNode<K, V>*& node) {
        // Guarda una referencia al subárbol izquierdo del nodo
        TreapNode<K, V>* temp = node->left;

        // Actualiza los punteros para realizar la rotación
        node->left = temp->right;
        temp->right = node;

        // Actualiza el nodo a la nueva raíz después de la rotación
        node = temp;
    }

        /**
     * Inserta un nuevo nodo en el Treap.
     *
     */
    void insert(TreapNode<K, V>*& node, const K& key, const V& value) {
        if (node == nullptr) {
            // Si el nodo actual es nulo, crea un nuevo nodo con la clave y el valor dados.
            node = new TreapNode(key, value);
            return;
        }
       
        // Compara la clave del nuevo nodo con la clave del nodo actual.
        if (key < node->key) {
            // Si la clave es menor, realiza la inserción en el subárbol izquierdo del nodo actual.
            insert(node->left, key, value);
           
            // Luego, verifica si la prioridad del hijo izquierdo es mayor que la prioridad del nodo actual.
            if (node->left->priority > node->priority) {
                // En caso afirmativo, rota el nodo actual hacia la derecha para mantener la propiedad del Treap.
                rotateRight(node);
            }
        } else {
            // Si la clave es mayor o igual, realiza la inserción en el subárbol derecho del nodo actual.
            insert(node->right, key, value);
           
            // Luego, verifica si la prioridad del hijo derecho es mayor que la prioridad del nodo actual.
            if (node->right->priority > node->priority) {
                // En caso afirmativo, rota el nodo actual hacia la izquierda para mantener la propiedad del Treap.
                rotateLeft(node);
            }
        }
    }

        /**
     * Elimina un nodo con la clave específica del Treap.
     *
     */
    void remove(TreapNode<K, V>*& node, const K& key) {
        if (!node) {
            // El nodo actual es nulo, la clave no se encontró en el Treap.
            return;
        }

        if (key < node->key) {
            // La clave a eliminar está en el subárbol izquierdo.
            remove(node->left, key);
        } else if (key > node->key) {
            // La clave a eliminar está en el subárbol derecho.
            remove(node->right, key);
        } else {
            // Se encontró un nodo con la clave deseada.

            if (!node->left) {
                // Si no hay hijo izquierdo, simplemente sustituye el nodo actual por el hijo derecho.
                TreapNode<K, V>* temp = node;
                node = node->right;
                delete temp;
            } else if (!node->right) {
                // Si no hay hijo derecho, sustituye el nodo actual por el hijo izquierdo.
                TreapNode<K, V>* temp = node;
                node = node->left;
                delete temp;
            } else {
                // El nodo tiene ambos hijos. Realiza la rotación adecuada para mantener las prioridades.
                if (node->left->priority < node->right->priority) {
                    rotateLeft(node);
                    remove(node->left, key);
                } else {
                    rotateRight(node);
                    remove(node->right, key);
                }
            }
        }
    }

    void printInOrder(TreapNode<K,V>* node){
            if(!node) return;
            printInOrder(node->left);
            cout <<"K:"<<node->key<<" V:"<<node->value << " Prioridad: " << node->priority << endl;
            printInOrder(node->right);
        }

    /**
     * Busca un nodo con la clave específica en el Treap.
     *
     */
    TreapNode<K, V>* findnode(TreapNode<K, V>* node, const K& key) {
        if (!node) {
            return nullptr;
        } else if (node->key == key) {
            return node;
        } else if (key < node->key) {
            return findnode(node->left, key);
        } else {
            return findnode(node->right, key);
        }
    }

   

    public:
// Constructor: Inicializa un TreapMap vacío con la raíz establecida en nullptr.
    TreapMap() : root(nullptr) {}

    /**
     * Inserta un nuevo nodo en el Treap.
     *
     * @param key   - La clave del nuevo nodo a insertar.
     * @param value - El valor asociado a la clave del nuevo nodo.
     */
    void insert(const K& key, const V& value) {
        insert(root, key, value);
    }

    /**
     * Elimina un nodo con la clave específica del Treap.
     *
     * @param key - La clave del nodo a eliminar.
     */
    void remove(const K& key) {
        remove(root, key);
    }

    /**
     * Obtiene el valor asociado a una clave específica en el Treap.
     *
     * @param key - La clave del nodo cuyo valor se quiere obtener.
     */
    void getValue(const K& key) {
        TreapNode<K, V>* node = findnode(root, key);
        if (!node) {
            // La clave no se encontró en el Treap, se devuelve nullptr.
            cout<<"\nNo encontrado";
        } else {
            cout<<node->value;
        }
    }

    /**
     * Imprime los nodos del Treap en orden (recorrido inorden).
     */
    void print() {
        printInOrder(root);
    }

};


int main() {
    srand(time(0));

    TreapMap<int, string> treap;
    treap.insert(10, "Marta");
    treap.insert(20, "Juan");
    treap.insert(5, "Felipe");
    treap.insert(8,"Andres");
    treap.insert(1,"Gabriel");
    treap.insert(2,"Alicia");
    treap.insert(6,"Robin");

    treap.print();
    treap.remove(8);
    cout<<"\n______"<<endl;
    treap.print();
    treap.getValue(5);
    treap.getValue(19);
    return 0;
}

#endif