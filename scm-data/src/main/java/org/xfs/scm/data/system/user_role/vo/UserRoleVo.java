package org.xfs.scm.data.system.user_role.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class UserRoleVo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6850926897189830539L;


	@NotNull(message = "用户Id不能为空！")
    private Long userId;


    private List<Integer> rids;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Integer> getRids() {
        return rids;
    }

    public void setRids(List<Integer> rids) {
        this.rids = rids;
    }
}
