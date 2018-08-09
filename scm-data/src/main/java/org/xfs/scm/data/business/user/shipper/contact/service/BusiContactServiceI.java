package org.xfs.scm.data.business.user.shipper.contact.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.user.shipper.contact.vo.BusiContactVo;

import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/27 0027.21:32
 * version:1.0
 */
public interface BusiContactServiceI {

    int removeBusiContact(BusiContactVo vo);

    boolean save(BusiContactVo vo);

    List<BusiContactVo> getBusiContacts(BusiContactVo vo);

    BusiContactVo getBusiContact(BusiContactVo vo);

    Grid<BusiContactVo> grid(BusiContactVo vo, int page , int rows);

    int modifyBusiContact(BusiContactVo vo);
}
