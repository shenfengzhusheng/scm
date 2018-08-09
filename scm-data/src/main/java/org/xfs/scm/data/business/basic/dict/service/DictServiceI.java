package org.xfs.scm.data.business.basic.dict.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.basic.dict.po.DictPo;
import org.xfs.scm.data.business.basic.dict.vo.DictInfoVo;
import org.xfs.scm.data.business.basic.dict.vo.DictVo;

import java.util.List;

public interface DictServiceI {


    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    int removeDictPo(DictVo vo);

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    int addDictPo(DictVo vo);

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    List<DictPo> getDictPos(DictVo vo);

    Grid<DictPo> grid(DictVo vo, Integer page, Integer rows);
    DictPo getDictPo(DictVo vo);

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    int modifyDictPo(DictVo vo);
    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    List<DictInfoVo>getDictInfo(DictVo vo);

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    List<DictInfoVo> getDictType(DictVo vo);

}
