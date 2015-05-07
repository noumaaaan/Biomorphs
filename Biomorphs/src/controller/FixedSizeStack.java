package controller;

import java.util.LinkedList;

/**
 * Stack with a fixed size. Silently removes any item that fall outside the maximum capacity.
 * 
 * @author Alex Luckett <lucketta@aston.ac.uk> Mohammed Hussain Ahmed<ahmedmh@aston.ac.uk> Jurgen Hajdini<hajdinij@aston.ac.uk> 
 * Serialises objects of type T.
 * 
 *  
 * @param <T> type of class
 */

public class FixedSizeStack<T> {
	private LinkedList<T> baseList;
	private int capacity;

	public FixedSizeStack(int capacity) {
		this.capacity = capacity;
		this.baseList = new LinkedList<T>();
	}
	
	public void push(T element) {
		baseList.add(element);
		
		if(baseList.size() > capacity) {
			baseList.removeFirst();
		}
	}
	
	public T pop() {
		if(baseList.size() == 0) {
			return null;
		}
		
		T element = baseList.removeLast();
		
		return element;
	}
	
	public int size(){
		return baseList.size();
		
	}
}
