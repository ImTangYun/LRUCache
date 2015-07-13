package main.java.com.candycloud.lru_cache;

import java.util.HashMap;
import java.util.List;

public class LruCache<T1, T2> {
	
	private HashMap<T1, ListNode<T1, T2>> map;
	private DoubleLinkedList<T1, T2> queue;
	private long limits;
	
	public LruCache(long capacity) {
		this.limits = capacity;
		map = new HashMap<T1, ListNode<T1, T2>>();
		queue = new DoubleLinkedList<T1, T2>();
	}

	public LruCache() {
		this.limits = Long.MAX_VALUE;
		map = new HashMap<T1, ListNode<T1, T2>>();
		queue = new DoubleLinkedList<T1, T2>();
	}

	public void put(T1 key, T2 value) {
		
		if (exists(key)) {
			map.get(key).setValue(value);
			return;
		}
		if (map.size() >= limits) {
			removeOldest();
			ListNode<T1, T2> node = new ListNode<T1, T2>(key, value);
			map.put(key, node);
			queue.insertAfter(queue.getHead(), node);
		} else {
			ListNode<T1, T2> node = new ListNode<T1, T2>(key, value);
			map.put(key, node);
			queue.insertAfter(queue.getHead(), node);
		}
	}
	
	public T2 get(T1 key) {
		if (!exists(key)) {
			return null;
		}
		return map.get(key).getValue();
	}
	
	public boolean exists(T1 key) {
		
		boolean ret = map.containsKey(key);
		if (!ret) {
			return false;
		} else {
			ListNode<T1, T2> node = map.get(key);
			queue.remove(node);
			queue.insertAfter(queue.getHead(), node);
			return true;
		}
	}
	
	private void removeOldest() {
		ListNode<T1, T2> oldest = queue.getTail().getPre();
		queue.remove(oldest);
		map.remove(oldest.getKey());
	}
	
	public void setCapicity(long capacity) {
		this.limits = capacity;
	}
	
	public final List<ListNode<T1, T2>> getAll() {
		return queue.getAll();
	}
}