package together.news.common.collection;

import java.util.ArrayList;

public class NodeTree<E extends NodeTree.NodeExecutor> {
	public Node<E> root=null;
	public NodeTree(Node<E> root){
		this.root=root;
	}
	public Node<E> getRoot(){
		return root;
	}
	public void executeTree(String input,ArrayList<String> output){
		executeNode(root,input,output);
		
	}
	private void executeNode(Node<E> node,String input,ArrayList<String> output){
		//execute current node
		ArrayList<String> result=node.nodeData.execute(input);
		//execute all child node
		ArrayList<Node<E>> childrenNode = node.getChildren();
		if(childrenNode==null){//leaf node
			output.addAll(result);
		}else{
			for(int i=0;i<childrenNode.size();i++){
					executeNode(childrenNode.get(i),result.get(i),output);//assume the order of children is the same as the input value
			}
		}
		
	}
	
	public static class Node<T>{
		public T nodeData=null;
		public ArrayList<Node<T>> children=new ArrayList<Node<T>>();
		public Node(T data){
			this.nodeData=data;
		}
		public Node<T> addNext(Node<T> child){
			this.children.add(child);
			return this;
		}
		public ArrayList<Node<T>> getChildren(){
			return this.children;
		}
	}
	public static interface NodeExecutor{
		ArrayList<String> execute(String content);
	}
}
