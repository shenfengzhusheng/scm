package org.xfs.scm.data.business.user.shipper.contact.mapper;

import org.xfs.scm.data.business.user.shipper.contact.vo.BusiContactVo;

import java.util.List;

public interface BusiContactMapper {

    int removeBusiContact(BusiContactVo record);

    int addBusiContact(BusiContactVo record);

    List<BusiContactVo> getBusiContacts(BusiContactVo record);

    int modifyBusiContact(BusiContactVo record);

}