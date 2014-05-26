#include <stdint.h>
#include <map>
#include <deque>

using std::map;
using std::deque;

namespace lru
{
#define T int
//template<typename T>
class LRUCache
{
    public:
        LRUCache(uint32_t capacity);
        ~LRUCache(){};
        int LookUp(uint32_t key, T** value);
        void Add(const uint32_t &key, const T &vlaue);
        void SetCapacity(uint32_t capacity);
        uint32_t size() const;
    private:
        deque<uint32_t> key_;
        map<uint32_t, T*> cache_;
        uint32_t capacity_;
        uint32_t size_;
};
}
