package org.xfs.scm.platform.config.data.db.mybatis;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

public class BoundSqlSqlSource implements SqlSource {
    private BoundSql boundSql;
    public BoundSqlSqlSource(BoundSql boundSql) {
        this.boundSql = boundSql;
    }
    public BoundSql getBoundSql(Object parameterObject) {
        return boundSql;
    }
}
