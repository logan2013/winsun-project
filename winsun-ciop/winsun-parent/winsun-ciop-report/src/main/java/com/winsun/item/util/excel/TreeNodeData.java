package com.winsun.item.util.excel;

public class TreeNodeData {
	private static TreeNode root;
	
	public static TreeNode getRoot() {
		return root;
	}

	private static void setRoot(TreeNode rootNode) {
		root = rootNode;
	}

	public static TreeNode getInstallationMaintenanceInformationTableThead() {
		TreeNode treeNode = new TreeNode(-1, "", null);
		TreeNode treeNode0 = new TreeNode(0, "县分","value1", treeNode);
		TreeNode treeNode1 = new TreeNode(1, "社区经理（装维）下沉业绩", treeNode);

		TreeNode treeNode11 = new TreeNode(2, "基于故障单（单宽进融）", treeNode1);
		TreeNode treeNode12 = new TreeNode(3, "基于故障单（融合升不限量）", treeNode1);
		TreeNode treeNode13 = new TreeNode(4, "灰色房间异网赠送体验宽带","value4", treeNode1);
		TreeNode treeNode14 = new TreeNode(5, "下沉人员人均工单量","value9", treeNode1);
		TreeNode treeNode15 = new TreeNode(6, "目标人均5单完成率","value10", treeNode1);

		TreeNode treeNode111 = new TreeNode(7, "月修障工单单宽数（1+1+N片区）","value2", treeNode11);
		TreeNode treeNode112 = new TreeNode(8, "单进融量", treeNode11);
		TreeNode treeNode113 = new TreeNode(9, "当月单进融量", treeNode11);
		TreeNode treeNode121 = new TreeNode(10, "月修障工单融合数（1+1+N片区）","value6", treeNode12);
		TreeNode treeNode122 = new TreeNode(11, "依案办理数", treeNode12);
		TreeNode treeNode123 = new TreeNode(12, "当月依案办理数", treeNode12);

		TreeNode treeNode1121 = new TreeNode(13, "统计周期：30天","value3", treeNode112);
		TreeNode treeNode1131 = new TreeNode(14, "统计周期：60天","value5", treeNode113);
		TreeNode treeNode1221 = new TreeNode(15, "统计周期：30天","value7", treeNode122);
		TreeNode treeNode1231 = new TreeNode(16, "统计周期：60天","value8", treeNode123);

		treeNode.calLeavesAmount(treeNode);
		treeNode.calNodeLevel(treeNode);
		return treeNode;
	}

	public static TreeNode getInstallationMaintenanceInformationPqTableThead() {
		TreeNode treeNode = new TreeNode(-1, "", null);
		TreeNode treeNode0 = new TreeNode(0, "县分","value1", treeNode);
		TreeNode treeNode01 = new TreeNode(0, "营服","value2", treeNode);
		TreeNode treeNode02 = new TreeNode(0, "片区","value3", treeNode);
		TreeNode treeNode1 = new TreeNode(1, "社区经理（装维）下沉业绩", treeNode);

		TreeNode treeNode11 = new TreeNode(2, "基于故障单（单宽进融）", treeNode1);

		TreeNode treeNode111 = new TreeNode(7, "月修障工单单宽数（1+1+N片区）","value4", treeNode11);

		TreeNode treeNode112 = new TreeNode(8, "单进融量", treeNode11);
		TreeNode treeNode1121 = new TreeNode(13, "统计周期：30天","value5", treeNode112);

		TreeNode treeNode113 = new TreeNode(9, "当月单进融量", treeNode11);
		TreeNode treeNode1131 = new TreeNode(14, "统计周期：60天","value6", treeNode113);

		TreeNode treeNode12 = new TreeNode(3, "基于故障单（融合升不限量）", treeNode1);
		TreeNode treeNode121 = new TreeNode(10, "月修障工单融合数（1+1+N片区）","value7", treeNode12);
		TreeNode treeNode122 = new TreeNode(11, "依案办理数", treeNode12);
		TreeNode treeNode1221 = new TreeNode(15, "统计周期：30天","value8", treeNode122);

		TreeNode treeNode123 = new TreeNode(12, "当月依案办理数", treeNode12);
		TreeNode treeNode1231 = new TreeNode(16, "统计周期：60天","value9", treeNode123);

		TreeNode treeNode13 = new TreeNode(4, "灰色房间异网赠送体验宽带","value10", treeNode1);
		TreeNode treeNode14 = new TreeNode(5, "下沉人员人均工单量","value11", treeNode1);
		TreeNode treeNode15 = new TreeNode(6, "目标人均5单完成率","value12", treeNode1);



		treeNode.calLeavesAmount(treeNode);
		treeNode.calNodeLevel(treeNode);
		return treeNode;
	}

	public static TreeNode getFreeTrialBroadbandListTableThead() {
		TreeNode treeNode = new TreeNode(-1, "", null);
		TreeNode treeNode0 = new TreeNode(0, "免费体验宽带入网清单", treeNode);
//		TreeNode treeNode1 = new TreeNode(1, "分局标识","value1", treeNode0);
		TreeNode treeNode2 = new TreeNode(2, "工单类型","value2", treeNode0);
		TreeNode treeNode3 = new TreeNode(3, "入网月份","value3", treeNode0);
		TreeNode treeNode4 = new TreeNode(4, "分局","value4", treeNode0);
		TreeNode treeNode5 = new TreeNode(5, "营服","value5", treeNode0);
		TreeNode treeNode6 = new TreeNode(6, "网格编码","value6", treeNode0);
		TreeNode treeNode7 = new TreeNode(7, "网格名称","value7", treeNode0);
		TreeNode treeNode8 = new TreeNode(8, "受理时间","value8", treeNode0);
		TreeNode treeNode9 = new TreeNode(9, "状态时间","value9", treeNode0);
		TreeNode treeNode10 = new TreeNode(10, "套餐编码","value10", treeNode0);
		TreeNode treeNode11 = new TreeNode(11, "接入号","value11", treeNode0);
		TreeNode treeNode12 = new TreeNode(12, "揽装工号","value12", treeNode0);
		TreeNode treeNode13 = new TreeNode(13, "揽装人","value13", treeNode0);
		TreeNode treeNode14 = new TreeNode(14, "协装工号","value14", treeNode0);
		TreeNode treeNode15 = new TreeNode(15, "协装人","value15", treeNode0);
		TreeNode treeNode16 = new TreeNode(16, "装机地址","value16", treeNode0);
		TreeNode treeNode17 = new TreeNode(17, "联系人","value17", treeNode0);
		TreeNode treeNode18 = new TreeNode(18, "联系人电话","value18", treeNode0);
		TreeNode treeNode19 = new TreeNode(19, "用户名","value19", treeNode0);
		TreeNode treeNode20 = new TreeNode(20, "当前速率","value20", treeNode0);
		TreeNode treeNode21 = new TreeNode(21, "当前状态","value21", treeNode0);
		treeNode.calLeavesAmount(treeNode);
		treeNode.calNodeLevel(treeNode);
		return treeNode;
	}

	public static TreeNode getObstacleUserDeclarationListTableThead() {
		TreeNode treeNode = new TreeNode(-1, "", null);
		TreeNode treeNode0 = new TreeNode(0, "障碍用户申告", treeNode);

		TreeNode treeNode1 = new TreeNode(1, "接入号","acc_nbr", treeNode0);
		TreeNode treeNode2 = new TreeNode(2, "归档日期","create_date", treeNode0);
		TreeNode treeNode3 = new TreeNode(3, "分局","subst_name", treeNode0);
		TreeNode treeNode4 = new TreeNode(4, "营服","branch_name", treeNode0);
		TreeNode treeNode5 = new TreeNode(5, "片区","comm_name", treeNode0);
		TreeNode treeNode6 = new TreeNode(6, "网格编码","bevy_cust_code", treeNode0);
		TreeNode treeNode7 = new TreeNode(7, "网格名称","bevy_cust_name", treeNode0);
		TreeNode treeNode8 = new TreeNode(8, "进融号码数","yd_num", treeNode0);
		TreeNode treeNode9 = new TreeNode(9, "进融揽装工号","yd_salestaff_id", treeNode0);
		TreeNode treeNode10 = new TreeNode(10, "进融揽装人名称","yd_salestaff_name", treeNode0);
		TreeNode treeNode11 = new TreeNode(11, "进融协销人工号","yd_salestaff_id_xz", treeNode0);
		TreeNode treeNode12 = new TreeNode(12, "进融协销人名称","yd_salestaff_name_xz", treeNode0);
		TreeNode treeNode13 = new TreeNode(13, "是否装维揽装","is_lz", treeNode0);
		TreeNode treeNode14 = new TreeNode(14, "协销人是否装维揽装","is_xz", treeNode0);
		TreeNode treeNode15 = new TreeNode(15, "进融渠道大类","yd_channel_type_2011", treeNode0);
		TreeNode treeNode16 = new TreeNode(16, "进融渠道小类","yd_channel_subtype_2011", treeNode0);
		TreeNode treeNode17 = new TreeNode(17, "进融渠道日报小类","yd_channel_subtype_rb", treeNode0);
		TreeNode treeNode18 = new TreeNode(18, "进融入网时间","yd_create_date", treeNode0);
		TreeNode treeNode19 = new TreeNode(19, "是否单宽","is_dk", treeNode0);
		TreeNode treeNode20 = new TreeNode(20, "是否依案办理","is_yabl", treeNode0);
		TreeNode treeNode21 = new TreeNode(21, "套餐价值是否提升","is_tcvalts", treeNode0);

		treeNode.calLeavesAmount(treeNode);
		treeNode.calNodeLevel(treeNode);
		return treeNode;
	}


}
