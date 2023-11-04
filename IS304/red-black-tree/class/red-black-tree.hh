#ifndef __RBT_HH__
#define __RBT_HH__

#include <iostream>
#include <vector>
#include <cassert>

using namespace std;

template <typename Key, typename Value> class RBT {
  private:

    enum class Color {
      RED
      BLACK
    };

    class Node {
      private:

        Key key;
        Value value;
        Color color;
        Node *parent, *left, *right;

      public:

        Node(Key k, Value v, Color n_color, Node* l, Node* r) {
          key = k;
          value = v;
          color = n_color;
          left = l;
          right = r;
          if (left != nullptr) left->parent = this;
          if (right != nullptr) right->parent = this;
          parent = nullptr;
        }

        Node* getParent(void) const {
          return parent;
        }

        void setParent(Node* newParent) {
          parent = newParent;
        }

        Node* getLeft(void) const {
          return left;
        }

        void setLeft(Node* newLeft) {
          left = newLeft;
        }

        Node* getRight(void) const {
          return right;
        }

        void setRight(Node* newRight) {
          right = newRight;
        }

        bool isBlack() const {
          return color == Color::BLACK;
        }

        void setBlack() {
          color = Color::BLACK;
        }

        bool isRed() const {
          return color == Color::RED;
        }

        void setRed() {
          color = Color::RED;
        }

        Key getKey(void) const {
          return key;
        }

        const Value& getValue(void) const {
          return value;
        }

        Value& getValue(void) {
          return value;
        }

        void setValue(Value newValue) {
          value = newValue;
        }

        bool hasRightChild(void) const {
          return right != nullptr;
        }
        bool hasLeftChild(void) const {
          return left != nullptr;
        }

        bool isLeaf(void) const {
          return !hasLeftChild() && !hasRightChild();
        }
    };

  private:

    Node* root_;
    int size_;

  public:

    RBT(void) : root_(nullptr), size_(0) { }

    int size(void) const {
      return size_;
    }

    bool empty(void) const {
      return root_ == nullptr;
    }

  private:

    bool hasParent(Node* n) const {
      return n->getParent() != nullptr;
    }

    bool hasGrandparent(Node* n) const {
      return n->getParent()->getParent() != nullptr;
    }

    bool isLeftChild(Node* n) const {
      assert(n != nullptr);
      assert(hasParent(n));
      return n == n->getParent()->getLeft();
    }

    bool isRightChild(Node* n) const {
      assert(n != nullptr);
      assert(hasParent(n));
      return n == n->getParent()->getRight();
    }

    Node* grandparent(Node* n) const {
      assert(n != nullptr);
      assert(hasParent(n));
      assert(hasGrandparent(n));
      return n->getParent()->getParent();
    }

    Node* sibling(Node* n) const {
      assert(n != nullptr);
      assert(hasParent(n));
      if (isLeftChild(n)) { 
        return n->getParent()->getRight();
      } else {
        assert(isRightChild(n));
        return n->getParent()->getLeft();
      }
    }

    Node* uncle(Node* n) const {
      assert(n != nullptr);
      assert(hasParent(n));
      return sibling(n->getParent());
    }

    Node* findHelper(Key key) const {
      Node* n = root_;
      while (n != nullptr) {
        if (key == n->getKey()) {
          return n;
        } else if (key < n->getKey()) {
          n = n->getLeft();
        } else {
          assert(key > n->getKey());
          n = n->getRight();
        }
      }
      return n;
    }

  public:

    pair<bool, Value> find(Key key) const {
      Node* n = findHelper(key);
      if (n == nullptr) return make_pair(false, Value());
      return make_pair(true, n->getValue());
    }

    void rotateLeft(Node* n) {
      Node* r = n->getRight();
      replaceNode(n, r);
      n->setRight(r->getLeft());
      if (r->getLeft() != nullptr) {
        r->getLeft()->setParent(n);
      }
      r->setLeft(n);
      n->setParent(r);
    }

    void rotateRight(Node* n) {
      Node* l = n->getLeft();
      replaceNode(n, l);
      n->setLeft(l->getRight());
      if (l->getRight() != nullptr) {
        l->getRight()->setParent(n);
      }
      l->setRight(n);
      n->setParent(l);
    }

    void replaceNode(Node* oldn, Node* newn) {
      if (oldn->getParent() == nullptr) {
        root_ = newn;
      } else {
        if (isLeftChild(oldn)) {
          oldn->getParent()->setLeft(newn);
        } else {
          assert(isRightChild(oldn));
          oldn->getParent()->setRight(newn);
        }
      }
      if (newn != nullptr) {
        newn->setParent(oldn->getParent());
      }
    }

  public:

    void insert(Key key, Value value) {
      Node* inserted_node = new Node(key, value, Color::RED, nullptr, nullptr);
      if (root_ == nullptr) {
        root_ = inserted_node;
        size_++;
      } else {
        Node* n = root_;
        bool inserted = false;
        while (!inserted) {
          if (key == n->getKey()) {

            n->setValue(value);
            return;
          } else if (key < n->getKey()) {
            if (!n->hasLeftChild()) {

              n->setLeft(inserted_node);
              size_++;
              inserted = true;
            } else { 
              n = n->getLeft();
            }
          } else {
            assert(key > n->getKey());
            if (!n->hasRightChild()) {

              n->setRight(inserted_node);
              size_++;
              inserted = true;
            } else {
              n = n->getRight();
            }
          }
        }
        inserted_node->setParent(n);
      }
      insertCase1(inserted_node);
    }

  private:

    void insertCase1(Node* n) {
        if (!hasParent(n))
        n->setBlack();
        else
        insertCase2(n);
    }

    void insertCase2(Node* n) {
        if (n->getParent()->isBlack())
        return;
        else
        insertCase3(n);
    }

    void insertCase3(Node* n) {
        Node* u = uncle(n);
        if (u != nullptr && u->isRed()) {

        n->getParent()->setBlack();
        u->setBlack();
        grandparent(n)->setRed();
        insertCase1(grandparent(n));
        } else

        insertCase4(n);
    }

    void insertCase4(Node* n) {
        if (isRightChild(n) && isLeftChild(n->getParent())) {
        rotateLeft(n->getParent());
        n = n->getLeft();
        } else if (isLeftChild(n) && isRightChild(n->getParent())) {
        rotateRight(n->getParent());
        n = n->getRight();
        }
        insertCase5(n);
    }

    void insertCase5(Node* n) {
        n->getParent()->setBlack();
        grandparent(n)->setRed();
        if (isLeftChild(n) && isLeftChild(n->getParent())) {
        rotateRight(grandparent(n));
        } else {
        assert(isRightChild(n) && isRightChild(n->getParent()));
        rotateLeft(grandparent(n));
        }
    }

    private:
    /**
     * @brief finds the smallest key in the tree rooted at @a n.
     */
    Node* findMin(Node* n) {
        if (n->hasLeftChild()) return findMin(n->getLeft());
        return n;
    }

    /**
     * @brief Returns the successor of node @a x in the tree.
     *
     * The successor of node @a x is the node with the smallest key greater than
     * @a x.
     */
    Node* successor(Node* x) {
        if (x->hasRightChild()) return findMin(x->getRight());
        Node* y = x->getParent();
        while (y != nullptr && x == y->getRight()) {
        x = y;
        y = y->getParent();
        }
        return y;
    }

    /**
     * @brief Finds the smallest node in the tree that greater than or equal to @a
     * key
     */
    Node* findLowerBound(Key key) {
        Node* n = root_;
        Node* last = nullptr;
        while (n != nullptr && key != n->getKey()) {
        last = n;
        if (key < n->getKey()) {
            n = n->getLeft();
        } else {
            assert(key > n->getKey());
            n = n->getRight();
        }
        }
        /** At this point n has either two values:
         * - n == nullptr: key was not found in the tree. In this case last has the
         *   element of the tree that is greater than key.
         *
         * - n != nullptr: key is in the tree and n points to it.
         */
        if (n == nullptr) {
        assert(last != nullptr);
        return last;
        } else {
        return n;
        }
    }

    public:
    vector<Value> findInterval(Key lb, Key ub) {
        Node* first = findLowerBound(lb);
        assert(first != nullptr);
        vector<Value> result;
        while (first->getKey() <= ub) {
        result.push_back(first->getValue());
        first = successor(first);
        }
        return result;
    }

    public:
    void print() {
        if (empty())
        std::cout << "Empty tree\n";
        else
        std::cout << "\\begin{tikzpicture}\n"
                    << "\\graph[binary tree layout, nodes={draw,circle}]{\n";
        print(root_, 0);
        std::cout << "\n};\n";
    }

    void print(Node* n, size_t indent) {

        Key data = n->getKey();
        // for (auto i : indent) std::cout << ' ';
        if (n->isLeaf()) {
        std::cout << "{" << data << "}\n";
        }
        std::cout << data << "-> {";
        if (n->hasLeftChild()) print(n->getLeft());
        if (n->hasRightChild()) print(n->getRight());
        std::cout << "\n}\n";
    }

    /*
    * Print
    */
    void print_tree() {
        print_tree_helper(root_, 0);
        puts("");
    }

    private:
    void print_tree_helper(Node* n, int indent) {
        int i;
        if (n == nullptr) {
        fputs("<empty tree>", stdout);
        return;
        }
        if (n->getRight() != nullptr) {
        print_tree_helper(n->getRight(), indent + INDENT_STEP);
        }
        for (i = 0; i < indent; i++) fputs(" ", stdout);
        if (n->isBlack())
        cout << n->getKey() << endl;
        else
        cout << "<" << n->getKey() << ">" << endl;
        if (n->getLeft() != nullptr) {
        print_tree_helper(n->getLeft(), indent + INDENT_STEP);
        }
    }
};

#endif