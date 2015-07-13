package main.java.com.candycloud.lru_cache;

public class ListNode<T1, T2> {
	
	private T1 key;
	private T2 value;
	private ListNode<T1, T2> pre = null;
	private ListNode<T1, T2> next = null;

	public ListNode(T1 key, T2 value) {
		this.key = key;
		this.value = value;
	}
	public T1 getKey() {
		return key;
	}
	public void setKey(T1 key) {
		this.key = key;
	}
	public T2 getValue() {
		return value;
	}
	public void setValue(T2 value) {
		this.value = value;
	}
	public ListNode<T1, T2> getPre() {
		return pre;
	}
	public void setPre(ListNode<T1, T2> pre) {
		this.pre = pre;
	}
	public ListNode<T1, T2> getNext() {
		return next;
	}
	public void setNext(ListNode<T1, T2> next) {
		this.next = next;
	}


}