package test.java.com.candycloud.lru_cache;

import java.util.List;

import main.java.com.candycloud.lru_cache.ListNode;
import main.java.com.candycloud.lru_cache.LruCache;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LruCache<String, String> cache = new LruCache<String, String>(3);
		cache.put("test", "testvalue");
		cache.put("terst2", "sdfsdf");
		cache.put("terst3", "sdfsdf");
		cache.put("terst4", "sdfsdf");
		
		print(cache.getAll());
		cache.get("terst2");
		print(cache.getAll());
		
	}
	
	public static void print(List<ListNode<String, String>> list) {
		for (ListNode<String, String> node:list) {
			System.out.println(node.getKey());
		}
	}

}
