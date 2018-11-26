package com.winsun.item.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.winsun.item.modular.system.dao.RelationMapper;
import com.winsun.item.modular.system.model.Relation;
import com.winsun.item.modular.system.service.IRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author winsun123
 * @since 2018-02-22
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {

}
