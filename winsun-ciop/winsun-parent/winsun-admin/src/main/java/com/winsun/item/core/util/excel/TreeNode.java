package com.winsun.item.core.util.excel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TreeNode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int parentId;//父节点id
	private int selfId;//本身id
	protected int level;//深度
	protected int leafSize;//宽度
	protected String key;
	protected String nodeName;
	protected TreeNode parentNode;//父节点
	protected List<TreeNode> childList;//子节点集合

	public TreeNode() {
		initChildList();
	}

	public TreeNode(String name) {
		this.setNodeName(name);
		initChildList();
	}

	public TreeNode(String name, TreeNode parentNode) {
		this.setNodeName(name);
		this.setParentNode(parentNode);
		this.setParentId(parentNode.selfId);
		initChildList();
	}

	public TreeNode(int id, String name, TreeNode parentNode) {
		this.setSelfId(id);
		this.setNodeName(name);
		if (parentNode != null) {
			this.setParentNode(parentNode);
			this.setParentId(parentNode.selfId);
			parentNode.addChildNode(this);
		}
		initChildList();
	}
	
	public TreeNode(int id, String name, String key, TreeNode parentNode) {
		this.setSelfId(id);
		this.setNodeName(name);
		this.setKey(key);
		if (parentNode != null) {
			this.setParentNode(parentNode);
			this.setParentId(parentNode.selfId);
			parentNode.addChildNode(this);
		}
		initChildList();
	}

	public TreeNode(TreeNode parentNode) {
		this.setParentNode(parentNode);
		this.setParentId(parentNode.selfId);
		initChildList();
	}

	public boolean isLeaf() {
		if (childList == null) {
			return true;
		} else {
			if (childList.isEmpty()) {
				return true;
			} else {
				return false;
			}
		}
	}

	/* 插入�?��child节点到当前节点中 */
	public void addChildNode(TreeNode treeNode) {
		initChildList();
		childList.add(treeNode);
		treeNode.setParentNode(this);
	}

	public void initChildList() {
		if (childList == null)
			childList = new ArrayList<TreeNode>();
	}

	public boolean isValidTree() {
		return true;
	}

	/* 返回当前节点的父辈节点集�?*/
	public List<TreeNode> getElders() {
		List<TreeNode> elderList = new ArrayList<TreeNode>();
		TreeNode parentNode = this.getParentNode();
		if (parentNode == null) {
			return elderList;
		} else {
			elderList.add(parentNode);
			elderList.addAll(parentNode.getElders());
			return elderList;
		}
	}

	/* 返回当前节点的晚辈集*/
	public List<TreeNode> getJuniors() {
		List<TreeNode> juniorList = new ArrayList<TreeNode>();
		List<TreeNode> childList = this.getChildList();
		if (childList == null) {
			return juniorList;
		} else {
			int childNumber = childList.size();
			for (int i = 0; i < childNumber; i++) {
				TreeNode junior = childList.get(i);
				juniorList.add(junior);
				juniorList.addAll(junior.getJuniors());
			}
			return juniorList;
		}
	}

	/* 返回当前节点的孩子集�?*/
	public List<TreeNode> getChildList() {
		return childList;
	}
	
	
	/* 删除节点和它下面的晚�?*/
	public void deleteNode() {
		TreeNode parentNode = this.getParentNode();
		int id = this.getSelfId();

		if (parentNode != null) {
			parentNode.deleteChildNode(id);
		}
	}

	/* 删除当前节点的某个子节点 */
	public void deleteChildNode(int childId) {
		List<TreeNode> childList = this.getChildList();
		int childNumber = childList.size();
		for (int i = 0; i < childNumber; i++) {
			TreeNode child = childList.get(i);
			if (child.getSelfId() == childId) {
				childList.remove(i);
				return;
			}
		}
	}
	
	/* 找到一颗树中某个节点 */
	public TreeNode findTreeNodeByKey(String key) {
		if (key.equals(this.getKey()))
			return this;
		if (isLeaf()) {
			return null;
		} else {
			int childNumber = childList.size();
			for (int i = 0; i < childNumber; i++) {
				TreeNode child = childList.get(i);
				TreeNode resultNode = child.findTreeNodeByKey(key);
				if (resultNode != null) {
					return resultNode;
				}
			}
			return null;
		}
	}

	/**
	 * 从左到右获取叶子的key
	 */
	public List<String> getKeyList(){
		List<String> keyList = new ArrayList<String>();
		List<TreeNode> nodeList = this.getJuniors();
		for(int i = 0,s = nodeList.size() ;i<s;i++){
			String key = nodeList.get(i).getKey();
			if(StringUtils.isNotBlank(key)){
				keyList.add(key);
			}
		}
		return keyList;
	}
	
	/* 动态的插入一个新的节点到当前树中 */
	public boolean insertJuniorNode(TreeNode treeNode) {
		int juniorParentId = treeNode.getParentId();
		if (this.parentId == juniorParentId) {
			addChildNode(treeNode);
			return true;
		} else {
			List<TreeNode> childList = this.getChildList();
			int childNumber = childList.size();
			boolean insertFlag;

			for (int i = 0; i < childNumber; i++) {
				TreeNode childNode = childList.get(i);
				insertFlag = childNode.insertJuniorNode(treeNode);
				if (insertFlag == true)
					return true;
			}
			return false;
		}
	}

	/* 找到树中某个节点 */
	public TreeNode findTreeNodeById(int id) {
		if (this.selfId == id)
			return this;
		if (childList.isEmpty() || childList == null) {
			return null;
		} else {
			int childNumber = childList.size();
			for (int i = 0; i < childNumber; i++) {
				TreeNode child = childList.get(i);
				TreeNode resultNode = child.findTreeNodeById(id);
				if (resultNode != null) {
					return resultNode;
				}
			}
			return null;
		}
	}

	/* 遍历树，层次遍历 */
	public void traverse() {
		if (selfId < 0)
			return;
//		print(this.selfId);
		//System.out.println(this.selfId + " ");
		if (this.isLeaf())
			return;
		int childNumber = childList.size();
		for (int i = 0; i < childNumber; i++) {
			TreeNode child = childList.get(i);
			child.traverse();
		}
	}

	public int calLeavesAmount(TreeNode treeNode) {
		if (treeNode.isLeaf()) {
			treeNode.setLeafSize(1);
			return 1;
		} else {
			int sum = 0;
			for (TreeNode node : treeNode.childList) {
				sum += calLeavesAmount(node);
			}
			treeNode.setLeafSize(sum);
			return sum;
		}
	}

	public void calNodeLevel(TreeNode treeNode) {

		if (treeNode.parentNode == null) {
			treeNode.setLevel(-1);
		} else
			treeNode.setLevel(treeNode.parentNode.getLevel() + 1);
		if(!treeNode.isLeaf())
			for (TreeNode node : treeNode.getChildList()) {
				calNodeLevel(node);
			}
	}
	

	public int getMaxLevel(){
		List<TreeNode> list = this.getJuniors();
		int max = 0;
		for(TreeNode node:list){
			int l = node.getLevel();
			max = max<l?l:max;
		}
		return max;
	}
	
	public void print(String content) {
		//System.out.println(content);
	}

	public void print(int content) {
		//System.out.println(String.valueOf(content));
	}

	public void setChildList(List<TreeNode> childList) {
		this.childList = childList;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getSelfId() {
		return selfId;
	}

	public void setSelfId(int selfId) {
		this.selfId = selfId;
	}

	public TreeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(TreeNode parentNode) {
		this.parentNode = parentNode;
		this.setParentId(parentNode.getSelfId());
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getLeafSize() {
		return leafSize;
	}

	public void setLeafSize(int leafSize) {
		this.leafSize = leafSize;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		String str = "TreeNode [parentId=" + parentId + ", selfId=" + selfId + ", nodeName=" + nodeName + ", leafSize="
				+ leafSize + ", key=" + key + ", level=" + level + ", \nchildList=";
		if (childList != null || !childList.isEmpty())
			for (Object obj : childList)
				str += "\n" + obj.toString();
		else
			str += "null";
		str += "]";
		return str;
	}

}
