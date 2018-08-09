package org.xfs.scm.data.business.basic.dict.mapper;

import org.xfs.scm.data.business.basic.dict.po.DictPo;
import org.xfs.scm.data.business.basic.dict.vo.DictInfoVo;
import org.xfs.scm.data.business.basic.dict.vo.DictVo;

import java.util.List;

public interface DictPoMapper {
    int removeDictPo(DictVo vo);


    int addDictPo(DictVo vo);

    List<DictPo> getDictPos(DictVo vo);

    List<DictInfoVo>getDictInfo(DictVo vo);


    List<DictInfoVo> getDictType(DictVo vo);


    int modifyDictPo(DictVo vo);

}