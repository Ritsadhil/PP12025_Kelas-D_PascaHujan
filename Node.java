public class Node {
    private Produk data;
	    private Node next;
	    
	    public Node(Produk data) {
	        this.data = data;
	        this.next = null;
	    }

		public Produk getData() {
			return data;
		}

		public void setData(Produk data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
}
