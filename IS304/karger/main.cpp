#include <iostream>
#include <vector>
#include <random>


using namespace std; 


/** @class Edge: representa las aristas del grafo.
  * @param src: nodo origen.
  * @param dest: nodo destino.
  * @result Instancia de una ruta que une dos nodos.
  * */
class Edge {
  public:
    int src; // nodo origen.
    int dest; // nodo destino.
};


/** @class Subset: representa un conjunto de nodos, el cual absorvera mas nodos incrementando el rank.
  * @param parent: representa el nodo padre que funciona como referencia a los nodos absorvidos.
  * @param rank: representa el total de nodos absorvidos.
  * @result Instancia de un conjunto de nodos.
  * */
class Subset {
  public:
    int parent; // representa el nodo padre que funciona como referencia a los nodos absorvidos.
    int rank; // representa el total de nodos absorvidos.
};


/** @class Graph: representa un grafo no dirigido.
  * @param V: número de nodos.
  * @param E: número de aristas.
  * @param edge: vector de aristas.
  * */
class Graph {
  public:

    int V; // número de nodos.
    int E; // número de arista.
    vector<Edge> edge; // vector de aristas.


    /** Constructor base, que inicializa el grafo con un número dado de nodos y aristas,
      * ademas de hacer el recize del vector de aristas. 
      * */
    Graph(int V, int E) : V(V), E(E) {
        edge.resize(E);
    }

    
    /** Se encarga de generar un número aleatorio entre un buen rango para luego
      * usarlo para elegir de forma aleatoria una arista del nodo para iniciar su 
      * eliminación y unir los nodos que esta arista conecta.
      * */
    int randomEdge() {
      random_device rd;
      mt19937 generator(rd());
      uniform_int_distribution<int> distribution(1, 100000000);
      return distribution(generator);
    }


    /** Se encarga de encontrar los nodos a los que apunta una arista
      * tomando en cuenta el caso en el que se busque un nodo que es fucion
      * de otro y que este no sea el representante:
      * @param subsets: vector de Subsets.
      * @param i: nodo.
      * @return int: representa el conjunto al que pertenece un nodo.
      * */
    int find(vector<Subset>& subsets, int i) {
      if (subsets[i].parent != i) {
        return subsets[subsets[i].parent].parent;
      }
      return subsets[i].parent;
    }


    /** Se encarga de unir dos nodos que conectan la arista elegida aleatoriamente:
      * decide si hacer que el nodo x se convierta en nodo y o viceversa, dependiendo de 
      * cual tenga un rank mayor, lo que significa cual nodo ya tiene nodos unidos a
      * si, para hacer la union al nodo con mas uniones  (el nodo mas grande).
      * @param subsets: vector de Subsets.
      * @param x: nodo src.
      * @param y: nodo des.
      * @result nodo unido
      * */
    void Union(vector<Subset>& subsets, int x, int y) {
      if (subsets[x].rank < subsets[y].rank) {
        subsets[x].parent = y;
      } else if (subsets[x].rank > subsets[y].rank) {
        subsets[y].parent = x;
      } else {
        subsets[y].parent = x;
        subsets[x].rank++;
      }
    }


    /** Se encarga de encontrar un posible corte minimo en el grafo:
      * primero inicializa todos los nodos con la estructura del Subset,
      * almacena una variable auxiliar para guardar el número de nodos
      * existentes la cual se va a ir reduciendo devido a la union de los nodos,
      * elegidos por la arista elegida aleatoriamente, luego busca los nodos
      * que conectan las arista elegida, si los nodos a los que apunta
      * la arista son diferentes significa que la arista no apunta al mismo nodo.
      * por lo que se puede hace una union de los nodos que une la arista,
      * y se reduce el número de nodos existentes. finaliza recorriendo todas
      * las arista para ver cuantas aristas conectan los dos nodos resultantes
      * para posteriormente devolver el número de aristas que conectan los dos nodos
      * verificando que estas no apunten al mismo sitio.
      * @return representa el corte minimo hallado.
      * */
    int kargerMinCut() {
      vector<Subset> subsets(V);
      for (int v = 0; v < V; ++v) {
        subsets[v].parent = v;
        subsets[v].rank = 0;
      }

      int vertices = V;
      while (vertices > 2) {
        int i = randomEdge() % E;
        int subset1 = find(subsets, edge[i].src);
        int subset2 = find(subsets, edge[i].dest);

        if (subset1 != subset2) {
          cout << "Contrayendo arista " << edge[i].src << "-" << edge[i].dest << endl;
          vertices--;
          Union(subsets, subset1, subset2);
        }
      }

      int cutedges = 0;
      for (int i = 0; i < E; i++) {
        int subset1 = find(subsets, edge[i].src);
        int subset2 = find(subsets, edge[i].dest);
        if (subset1 != subset2) {
          cutedges++;
        }
      }

      cout << "Corte minimo hallado: " << cutedges << endl;
      return cutedges;
    }
};


int main() {
    int V = 6; // total de vertices o nodos
    int E = 8; // total de aristas o caminos que unen los nodos
    Graph graph(V, E);
    /*
               (4)



          (0)       (1)



      (2)      (3)      (5)     
    */

    graph.edge[0].src = 0;
    graph.edge[0].dest = 1;
    /*
               (4)



          (0)-------(1)



      (2)      (3)      (5)     
    */

    graph.edge[1].src = 0;
    graph.edge[1].dest = 2;
    /*
               (4)



          (0)-------(1)
          /
         /
        /
      (2)      (3)      (5)     
    */

    graph.edge[2].src = 0;
    graph.edge[2].dest = 3;
    /*
               (4)



          (0)-------(1)
          /  \
         /    \
        /      \
      (2)      (3)      (5)     
    */

    graph.edge[3].src = 1;
    graph.edge[3].dest = 3;
    /*
               (4)



          (0)-------(1)
          /  \     /
         /    \   /
        /      \ /
      (2)------(3)      (5)     
    */

    graph.edge[4].src = 2;
    graph.edge[4].dest = 3;
    /*
               (4)



          (0)-------(1)
          /  \     /
         /    \   /
        /      \ /
      (2)------(3)      (5)     
    */

    graph.edge[5].src = 0;
    graph.edge[5].dest = 4;
    /*
               (4)
              /
             /
            /
          (0)-------(1)
          /  \     /
         /    \   /
        /      \ /
      (2)------(3)      (5)     
    */

    graph.edge[6].src = 1;
    graph.edge[6].dest = 4;
    /*
               (4)
              /   \
             /     \
            /       \
          (0)-------(1)
          /  \     /
         /    \   /
        /      \ /
      (2)------(3)      (5)     
    */

    graph.edge[7].src = 3;
    graph.edge[7].dest = 5;
    /* Representación gráfica del grafo final:
               (4)
              /   \
             /     \
            /       \
          (0)-------(1)
          /  \     /
         /    \   /
        /      \ /
      (2)------(3)-----(5)     
    */

    graph.kargerMinCut();

    return 0;
}
