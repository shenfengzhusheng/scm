package org.xfs.scm.goods.api;

import org.xfs.scm.goods.model.GoodsModel;

public interface GoodsRpcServiceI {

    GoodsModel getGoods(String goodsCode);
}
