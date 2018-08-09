package org.xfs.core.business.test.mapper;

import java.util.List;

import org.xfs.core.business.test.po.TestPo;

public interface TestMapper {


	public int addTest(TestPo vo);

    public int modifyTest(TestPo vo);
   // @Select("select  id, name, code, version FROM tb_test WHERE  id=#{id}")
    public List<TestPo> selectTest(TestPo vo);

    public int removeTest(TestPo vo);


}
