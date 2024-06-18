#ifndef HEAP_HH
#define HEAP_HH

#include <iostream>
#include <vector>
#include <cassert>

using std::cout;
using std::vector;
using std::swap;

template<typename T>
class MinHeap{
    private:
    //arreglo almacena los elementos del heap
    vector<T> heap;

    //Indices para acceder en el arreglo

    //Aceder al padre de un nodo en la posicion i del arreglo
    unsigned int parent_index(unsigned int i){
        return (i-1)/2;
    }

    //acceder al hijo izquierdo
    unsigned int left_index(unsigned int i){
        return 2*i + 1;
    }

    //acceder al hijo derecho
    unsigned int right_index(unsigned int i){
        return 2*i + 2;
    }


    public:

    //Retornar si esta vacio
    bool isempty()const{
        return heap.empty();
    }

    //Retornar tamaño
    unsigned int size()const{
        return heap.size();
    }

    //add: para insertar un elemento
    void add(const T& d){
        heap.push_back(d);
        heapify_up(size()-1);
    }

    private:
    //restaurar propiedad del heap al insertar elemento, burbujeo arriba
    //Reorganizar el heap después de agregar un elemento
    void heapify_up(unsigned int index) {
        // Calcular el índice del padre del elemento en la posición 'index'
        unsigned int p_index = parent_index(index);

        // Mientras no estemos en la raíz
        while (index > 0) {
            // Comparar el elemento en 'index' con su padre ('p_index')
            if (heap[index] < heap[p_index]) {
                // Si el elemento en 'index' es menor que su padre, los intercambiamos
                swap(heap[index], heap[p_index]);
            }

            // Actualizamos 'index' al índice de su padre
            index = p_index;

            // Recalculamos 'p_index' al índice del nuevo padre del elemento
            p_index = parent_index(index);
        }
    }

    public:

    //remueve el elemento minimo
    void remove(){
        assert(!isempty());
        heap[0]=heap.back();
        heap.pop_back();
        heapify_down(0);
    }

    //Remueve el elemento minimo y retorna una copia
    T pop(){
        T root =heap[0];
        heap[0]=heap.back();
        heap.pop_back();
        heapify_down(0);
        return root;
    }


    private:

    // Función para reorganizar el heap después de eliminar un elemento
    void heapify_down(unsigned int index) {
        // Calcular los índices de los hijos izquierdo y derecho
        unsigned int left_i = left_index(index);
        unsigned int right_i = right_index(index);

        // Inicialmente, consideramos que el elemento actual es el más pequeño
        unsigned int smallest_i = index;

        // Comprobamos si el hijo izquierdo existe y es menor que el elemento actual
        if (left_i < size() && heap[left_i] < heap[smallest_i]) {
            // Si el hijo izquierdo es menor, actualizamos 'smallest_i'
            smallest_i = left_i;
        }

        // Comprobamos si el hijo derecho existe y es menor que el elemento actual o el hijo izquierdo
        if (right_i < size() && heap[right_i] < heap[smallest_i]) {
            // Si el hijo derecho es menor, actualizamos 'smallest_i'
            smallest_i = right_i;
        }

        // Si 'smallest_i' no es igual a 'index', significa que encontramos un hijo más pequeño
        if (smallest_i != index) {
            // Intercambiamos el elemento actual con el hijo más pequeño
            swap(heap[index], heap[smallest_i]);
           
            // Llamamos a 'heapify_down' recursivamente en el hijo más pequeño
            heapify_down(smallest_i);
        }
    }



    public:
    // Reemplaza el elemento raíz con un nuevo elemento 'd' y restaura la propiedad de min-heap.
    void replace(const T& d){
        heap[0] = d;
        heapify_down(0); // Restaurar la propiedad de min-heap comenzando desde la raíz.
    }

    // Devuelve una referencia al elemento mínimo en el montículo (elemento raíz).
    const T& peek() const{
        return heap[0];
    }

    // Imprime los elementos en el montículo.
    void printheap(){
        cout << "\n Array: ";
        for (const T& item : heap) {
            cout << item << " ";
        }
        cout<<"\n";
    }


    // Método para convertir un arreglo en un min-heap
    void heapify(vector<T> arr) {
        // Reemplazar el contenido actual del heap con el contenido del arreglo
        heap = arr;
        // Comenzar desde el último nodo que tiene hijos
        for (int i = size() / 2 - 1; i >= 0; i--) {
            heapify_down(i);
        }
    }

};

void prueba(){
    MinHeap<int> bh;
    bh.add(7);
    bh.add(12);
    bh.add(15);
    bh.add(19);
    bh.add(22);
    bh.add(17);
    bh.add(16);
    bh.add(26);
    bh.add(33);
    bh.add(29);
    bh.add(41);
    bh.add(18);
    bh.add(31);
    bh.add(50);


    bh.printheap();

    bh.add(32);

    bh.printheap();

    bh.remove();

    bh.printheap();

    cout<<"------";
}

void example_sort() {
    MinHeap<int> myheap;

    myheap.heapify({4,3,5,1,2,8,7,12,9});

    myheap.printheap();

    vector<int> v1;

    while(!myheap.isempty()){
        v1.push_back(myheap.pop());
    }

    for(int i=0;i<v1.size();i++){
        cout<<" "<<v1[i];
    }
}

#endif