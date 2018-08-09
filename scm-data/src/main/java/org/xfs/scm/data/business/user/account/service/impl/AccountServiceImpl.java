package org.xfs.scm.data.business.user.account.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.user.account.mapper.UserBasicMapper;
import org.xfs.scm.data.business.user.account.service.AccountServiceI;
import org.xfs.scm.data.business.user.account.vo.AccountVo;
import org.xfs.scm.platform.util.components.Config;
import org.xfs.scm.platform.util.components.UrlUtil;
import org.xfs.scm.platform.util.string.StringUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountServiceI{

    @Resource
    private UserBasicMapper userBasicMapper;

    @Resource
    private Config config;

    @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Grid<AccountVo> gridAccount(AccountVo vo, Integer page, Integer rows) {
        Page<AccountVo> pages= PageHelper.startPage(page,rows);
        List<AccountVo> accountVoList=this.userBasicMapper.getAccounts(vo);
        if(!accountVoList.isEmpty()) {

            Grid<AccountVo> grid = new Grid<AccountVo>();
            grid.setRows(changeRealUrl(accountVoList));
            grid.setTotal(pages.getTotal());
            return grid;
        }
        return null;
    }
    private List<AccountVo> changeRealUrl(List<AccountVo> accountVoList){
        for(AccountVo av:accountVoList){
           if(!StringUtil.isEmpty(av.getUserHeaderUrl())){
               av.setUserHeaderUrl(config.getImagePath()+UrlUtil.urlSplit(av.getUserHeaderUrl()));
           }
        }
        return accountVoList;
    }
}
