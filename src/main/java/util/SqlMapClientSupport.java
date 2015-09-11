package util;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * SqlMapClient工具类，用于获取SqlMapClient实例
 * @author reniaL
 */
public class SqlMapClientSupport {
    
    private static final String RESOURCE = "ibatis2.xml";
    
    private static final SqlMapClient SQL_MAP;
    
    static {
        try {
            InputStream is = Resources.getResourceAsStream(RESOURCE);
            SQL_MAP = SqlMapClientBuilder.buildSqlMapClient(new InputStreamReader(is));
        } catch (Exception e) {
            throw new RuntimeException("error building SqlMapClient", e);
        }
    }
    
    /**
     * 获取SqlMapClient实例
     */
    public static SqlMapClient getSqlMapClient() {
        return SQL_MAP;
    }
    
}
