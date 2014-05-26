#include <stdio.h>
#include <unistd.h>
#include "lru.h"

using lru::LRUCache;
int main() 
{
    LRUCache test(100);
    test.Add(100, 1000);
    
    int* p = NULL;
    test.LookUp(100, &p);
    printf("result: %d\n", *p);
    return 0;
}
