#include <stdio.h>
#include <unordered_map>

using std::unordered_map;
struct node
{
    int id_;
    void* p_;
    int length_;
    node():p_(NULL){}
    ~node(){if (p_) delete (char*)p_;}
};

struct list_node
{
    list_node* next_;
    list_node* pre_;
    node *data_;
    list_node():next_(NULL), pre_(NULL), data_(NULL){}
    ~list_node(){if (data_) delete data_;}
};

class LRUCache
{
public:
    LRUCache(int limit):limit_(limit),head_(NULL),end_(NULL),size_(0){}
    ~LRUCache();
    void Insert(node* p);
    node* Find(int id);
    void Print(); // for test, only for string as data
private:
    list_node* head_;
    list_node* end_;
    int limit_;
    int size_;
    unordered_map<int, list_node*> k_v_;
};

typedef unordered_map<int,list_node*>::iterator iterator;
LRUCache::~LRUCache()
{
    if (head_ == NULL) return;
    
    for (list_node* p = head_; p != NULL;) {
        list_node* q = p->next_;
        delete p;
        if (q == NULL) {
            p = NULL;
            break;
        }
        p = q;
    }
}
void LRUCache::Print()
{
    if (head_ == NULL) return;
    list_node* p = head_;
    int count  = 0;
    for (; p != NULL; p = p->next_, ++count) {
        printf("count :%d %s\n", count, (char*)p->data_->p_);
    }
}
void LRUCache::Insert(node* data)
{
    if (data == NULL) return;
    iterator iter = k_v_.find(data->id_);
    if (iter != k_v_.end()) {
        if (size_ == 1) return;
        list_node *p = iter->second;
        if (p->pre_ == NULL) return;
        p->pre_->next_ = p->next_;
        if (p->next_ != NULL) {
            p->next_->pre_ = p->pre_;
        }
        p->pre_ = NULL;
        p->next_ = head_;
        head_->pre_ = p;
        head_ = p;
    } else {
        list_node* p = NULL;
        if (limit_ == size_) {
            p = end_->pre_;
            k_v_.erase(end_->data_->id_);
            delete end_;
            end_ = p;
            p->next_ = NULL;
        } else {
            ++size_;
        }
        p = new list_node;
        p->data_ = data;
        p->next_ = head_;
        k_v_[data->id_] = p;
        if (size_ == 1) {
            head_ = p;
            end_ = head_;
            return;
        }
        head_->pre_ = p;
        head_ = p;
    }
}
node* LRUCache::Find(int id)
{
    iterator iter = k_v_.find(id);
    if (iter != k_v_.end()) {
        node* ret = iter->second->data_;
        Insert(ret);
        return ret;
    } else {
        return NULL;
    }
}

int main()
{
    LRUCache L(15);
    for (int i = 0; i < 100; ++i) {
        char* p = new char[10];
        snprintf(p, 10, "id: %d", i);
        node* n = new node;
        n->p_ = (void*)p;
        n->length_ = 10;
        n->id_ = i;
        L.Insert(n);
    }
    L.Print();
    L.Find(88);
    L.Find(98);
    L.Print();
    for (int i = 100; i > 1; --i) {
        node* p = L.Find(i);
        if (p != NULL)
            printf("%s\n", (char*)p->p_);
    }
    L.Print();
    for (int i = 0; i < 100; ++i) {
        node* p = L.Find(i);
        if (p != NULL)
            printf("%s\n", (char*)p->p_);
    }
    L.Print(); 
    L.Print(); 
    return 0;
}

