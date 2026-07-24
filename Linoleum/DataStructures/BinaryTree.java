package DataStructures;

public class BinaryTree<T> {

    private T data;
    private BinaryTree<T> r, l;
    
	public BinaryTree(T data, BinaryTree<T> l, BinaryTree<T> r) {

		this.data = data;
		this.l = l;
		this.r = r;

	}

    public BinaryTree(T data) {

		this.data = data;

    }

	public BinaryTree(BinaryTree<T> bt) {

		this.data = bt.getData();

		if (bt.hasL())
			this.l = new BinaryTree<T>(bt.getL());

		if (bt.hasR())
			this.r = new BinaryTree<T>(bt.getR());

	}

    public T getData() {

		return this.data;

	}

	public void setData(T data) {

		this.data = data;

	}

	public BinaryTree<T> getL() {

		return this.l;

	}
	
	public void setL(BinaryTree<T> l) {

		this.l = l;

	}

	public BinaryTree<T> getR() {

		return this.r;

	}

	public void setR(BinaryTree<T> r) {

		this.r = r;

	}

	public boolean hasL() {

		return (this.l != null);

	}

	public boolean hasR() {

		return (this.r != null);

	}

}