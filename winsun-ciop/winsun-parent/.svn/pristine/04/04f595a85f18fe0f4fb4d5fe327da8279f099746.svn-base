package com.winsun.item.modular.compare.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 电子比算推荐套餐表
 * </p>
 *
 * @author winsun
 * @since 2018-11-09
 */
@TableName("dim_act_disc")
public class DimActDisc extends Model<DimActDisc> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 区域类型
     */
    @TableField("region_type")
    private String regionType;
    /**
     * 套餐名称
     */
    @TableField("disc_name")
    private String discName;
    /**
     * 套餐档次
     */
    @TableField("disc_value")
    private BigDecimal discValue;
    /**
     * 速率
     */
    @TableField("speed_value")
    private BigDecimal speedValue;
    /**
     * 速率描述
     */
    @TableField("speed_value_desc")
    private String speedValueDesc;
    /**
     * 国内语音
     */
    @TableField("mou_call")
    private BigDecimal mouCall;
    /**
     * 全国流量数值
     */
    @TableField("stm_data_value")
    private String stmDataValue;
    /**
     * 全国流量
     */
    @TableField("stm_data")
    private String stmData;
    /**
     * 副卡数量
     */
    @TableField("fk_value")
    private BigDecimal fkValue;
    /**
     * 副卡描述
     */
    @TableField("fk_desc")
    private String fkDesc;
    /**
     * 天翼高清（路）值
     */
    @TableField("itv_value")
    private BigDecimal itvValue;
    /**
     * 天翼高清（路）
     */
    @TableField("itv_desc")
    private String itvDesc;
    /**
     * 终端/话费补贴数值
     */
    @TableField("bt_value")
    private String btValue;
    /**
     * 终端/话费补贴
     */
    @TableField("bt_type")
    private String btType;
    /**
     * 橙分期总额
     */
    @TableField("cfq_value")
    private String cfqValue;
    /**
     * 橙分期
     */
    @TableField("cfq_desc")
    private String cfqDesc;
    /**
     * 翼支付红包数值
     */
    @TableField("yzf_hb_value")
    private String yzfHbValue;
    /**
     * 翼支付红包
     */
    @TableField("yzf_hb")
    private String yzfHb;
    /**
     * 是否过期
     */
    @TableField("is_old")
    private String isOld;
    /**
     * 是否删除
     */
    @TableField("del_flag")
    private String delFlag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType;
    }

    public String getDiscName() {
        return discName;
    }

    public void setDiscName(String discName) {
        this.discName = discName;
    }

    public BigDecimal getDiscValue() {
        return discValue;
    }

    public void setDiscValue(BigDecimal discValue) {
        this.discValue = discValue;
    }

    public BigDecimal getSpeedValue() {
        return speedValue;
    }

    public void setSpeedValue(BigDecimal speedValue) {
        this.speedValue = speedValue;
    }

    public String getSpeedValueDesc() {
        return speedValueDesc;
    }

    public void setSpeedValueDesc(String speedValueDesc) {
        this.speedValueDesc = speedValueDesc;
    }

    public BigDecimal getMouCall() {
        return mouCall;
    }

    public void setMouCall(BigDecimal mouCall) {
        this.mouCall = mouCall;
    }

    public String getStmData() {
        return stmData;
    }

    public void setStmData(String stmData) {
        this.stmData = stmData;
    }

    public BigDecimal getFkValue() {
        return fkValue;
    }

    public void setFkValue(BigDecimal fkValue) {
        this.fkValue = fkValue;
    }

    public String getFkDesc() {
        return fkDesc;
    }

    public void setFkDesc(String fkDesc) {
        this.fkDesc = fkDesc;
    }

    public BigDecimal getItvValue() {
        return itvValue;
    }

    public void setItvValue(BigDecimal itvValue) {
        this.itvValue = itvValue;
    }

    public String getItvDesc() {
        return itvDesc;
    }

    public void setItvDesc(String itvDesc) {
        this.itvDesc = itvDesc;
    }

    public String getBtType() {
        return btType;
    }

    public void setBtType(String btType) {
        this.btType = btType;
    }

    public String getCfqDesc() {
        return cfqDesc;
    }

    public void setCfqDesc(String cfqDesc) {
        this.cfqDesc = cfqDesc;
    }

    public String getYzfHb() {
        return yzfHb;
    }

    public void setYzfHb(String yzfHb) {
        this.yzfHb = yzfHb;
    }

    public String getIsOld() {
        return isOld;
    }

    public void setIsOld(String isOld) {
        this.isOld = isOld;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getStmDataValue() {
		return stmDataValue;
	}

	public void setStmDataValue(String stmDataValue) {
		this.stmDataValue = stmDataValue;
	}

	public String getBtValue() {
		return btValue;
	}

	public void setBtValue(String btValue) {
		this.btValue = btValue;
	}

	public String getCfqValue() {
		return cfqValue;
	}

	public void setCfqValue(String cfqValue) {
		this.cfqValue = cfqValue;
	}

	public String getYzfHbValue() {
		return yzfHbValue;
	}

	public void setYzfHbValue(String yzfHbValue) {
		this.yzfHbValue = yzfHbValue;
	}

	@Override
	public String toString() {
		return "DimActDisc [id=" + id + ", regionType=" + regionType
				+ ", discName=" + discName + ", discValue=" + discValue
				+ ", speedValue=" + speedValue + ", speedValueDesc="
				+ speedValueDesc + ", mouCall=" + mouCall + ", stmDataValue="
				+ stmDataValue + ", stmData=" + stmData + ", fkValue="
				+ fkValue + ", fkDesc=" + fkDesc + ", itvValue=" + itvValue
				+ ", itvDesc=" + itvDesc + ", btValue=" + btValue + ", btType="
				+ btType + ", cfqValue=" + cfqValue + ", cfqDesc=" + cfqDesc
				+ ", yzfHbValue=" + yzfHbValue + ", yzfHb=" + yzfHb
				+ ", isOld=" + isOld + ", delFlag=" + delFlag + "]";
	}

}
