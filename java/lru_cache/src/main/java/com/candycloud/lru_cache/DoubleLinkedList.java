package main.java.com.candycloud.lru_cache;

import java.util.ArrayList;
import java.util.List;

public class DoubleLinkedList<T1, T2> {
	
	private ListNode<T1, T2> head;
	private ListNode<T1, T2> tail;
	
	public ListNode<T1, T2> getHead() {
		return head;
	}

	public void setHead(ListNode<T1, T2> head) {
		this.head = head;
	}

	public ListNode<T1, T2> getTail() {
		return tail;
	}

	public void setTail(ListNode<T1, T2> tail) {
		this.tail = tail;
	}

	public DoubleLinkedList() {
		head = new ListNode<T1, T2>(null, null);
		tail = new ListNode<T1, T2>(null, null);
		head.setNext(tail);
		tail.setPre(head);
	}
	
	public void remove(ListNode<T1, T2> node) {
		if (node == head || node == tail || node == null) {
			return;
		}

		node.getPre().setNext(node.getNext());
		node.getNext().setPre(node.getPre());
	}
	
	public void insertAfter(ListNode<T1, T2> relative, ListNode<T1, T2> node) {
		if (node == null || relative == null || relative == tail) {
			return;
		}
		node.setPre(relative);
		node.setNext(relative.getNext());
		node.getPre().setNext(node);
		node.getNext().setPre(node);
	}
	
	public void insertAfter(ListNode<T1, T2> relative, T1 key, T2 data) {
		if (key == null || data == null || relative == null || relative == tail) {
			return;
		}
		ListNode<T1, T2> node = new ListNode<T1, T2>(key, data);
		insertAfter(relative, node);
	}
	
	public void insertBefore(ListNode<T1, T2> relative, ListNode<T1, T2> node) {
		if (node == null || relative == null || relative == head) {
			return;
		}
		node.setNext(relative);
		node.setPre(relative.getPre());
		node.getNext().setPre(node);
		node.getPre().setNext(node);
	}
	
	public void insertBefore(ListNode<T1, T2> relative, T1 key, T2 data) {
		if (key == null || data == null || relative == null || relative == head) {
			return;
		}
		ListNode<T1, T2> node = new ListNode<T1, T2>(key, data);
		insertBefore(relative, node);
	}
	
	public List<ListNode<T1, T2>> getAll() {
		List<ListNode<T1, T2>> list = new ArrayList<ListNode<T1, T2>>();
		ListNode<T1, T2> node = getHead().getNext();
		for (; node != getTail(); node = node.getNext()) {
			list.add(node);
		}
		return list;
	}
}
