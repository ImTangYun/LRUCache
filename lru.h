#include <stdint.h>
#include <map>
#include <queue>
#include "lru.h"

using std::map;
using std::queue;

namespace lru
{
//template<typename T>
#define T int
LRUCache::LRUCache(uint32_t capacity)
{
    capacity_ = capacity;
	size_ = 0;
}
uint32_t LRUCache::size() const
{
    return size_;
}
void LRUCache::Add(const uint32_t &key, const T &vlaue)
{
    if (size_ < capacity_) {
        key_.push_back(key);
        T *tem = new T(vlaue);
        cache_[key] = tem;
		++size_;
    } else {
        uint32_t t = key_.front();
        key_.pop_front();
        map<uint32_t, T*>::iterator ret = cache_.find(key);
		if (ret != cache_.end()) {
			delete ret->second;
			cache_.erase(t);
		}
    }
}
int LRUCache::LookUp(uint32_t key, T** value)
{
    map<uint32_t, T*>::iterator ret = cache_.find(key);
    if (ret != cache_.end()) {
        *value = ret->second;
        return 0;
    }
    return -1;
}
void LRUCache::SetCapacity(uint32_t capacity)
{
    capacity_ = capacity;
}
}
