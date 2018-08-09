package org.xfs.scm.data.business.busi.order.mapper;

import com.github.abel533.mapper.Mapper;
import org.xfs.scm.data.business.busi.order.vo.BusiOrderGoodsVo;

import java.util.List;

public interface BusiOrderGoodsMapper extends Mapper<BusiOrderGoodsVo> {
    int removeBusiOrderGoods(BusiOrderGoodsVo record);

    int addBusiOrderGoods(BusiOrderGoodsVo record);

    List<BusiOrderGoodsVo> getBusiOrderGoodss(BusiOrderGoodsVo record);

    int modfiyBusiOrderGoods(BusiOrderGoodsVo record);

}