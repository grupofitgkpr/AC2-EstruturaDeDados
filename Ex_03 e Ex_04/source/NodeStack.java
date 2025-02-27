package source;

public class NodeStack<E> implements Stack<E>{
	protected Node<E> top; // refer�ncia para o nodo cabe�a
	protected int size; // quantidade de elementos na pilha
	
	public NodeStack() { // constr�i uma pilha vazia
		top = null;
		size = 0;
	}

	public int size() {return size; }

	public boolean isEmpty() {
		if (top == null) return true;
		return false;
	}

	public void push(E elem) {
		Node<E> v = new Node<E>(elem, top); // cria e encadeia um nodo novo
		top = v;
		size++;
	}

	public E top() throws EmptyStackException {
		if (isEmpty()) throw new EmptyStackException("Stack is empty.");
		return top.getElement();
	}

	public E pop() throws EmptyStackException {
		Node<E> aux = top;
		
		if (isEmpty()) throw new EmptyStackException("Stack is empty.");
		E temp = top.getElement();
		top = top.getNext(); // desencadeia o nodo topo
		aux.setNext(null);
		size--;
		return temp;
	}
	
	public String verificaExpressao(String[] c1) {
		NodeStack<String> exp = new NodeStack<String>();
		for (int i = 0; i <= c1.length - 1; i++) {
			if (c1[i].equals("(") || c1[i].equals("[") || c1[i].equals("{")) {
				exp.push(c1[i]); 
			}
			else if (c1[i].equals(")") || c1[i].equals("]") || c1[i].equals("}")) {
				if (exp.isEmpty()) return "Incorreto";
				
				if (exp.top().equals("(") && c1[i].equals(")") || 
					exp.top().equals("[") && c1[i].equals("]") || 
					exp.top().equals("{") && c1[i].equals("}")) { 
					
					exp.pop();
				}
				else return "Incorreto";
			}
		}
		if (exp.isEmpty())
			return "Correto";
		else
			return "Incorreto";
	}
	
	public String toString() {
		String s;
		Node<E> p = top;
	
		s = "[";
		while (p != null) {
			s += p.getElement();
			s += ", ";
			p = p.getNext();
		}
		if (s.length() > 1)
			s = s.substring(0, s.length() - 2);
		return s + "]";
	}
}
