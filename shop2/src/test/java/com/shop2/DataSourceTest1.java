package com.shop2;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@Log4j2
public class DataSourceTest1 {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        // 사전작업은 cleanup 사용
        @Cleanup
        Connection con = dataSource.getConnection();
        log.info(con);      // 연결 정보
        Assertions.assertNotNull(con);      // assert : 확인하는 method / null 인지 아닌지 확인
    }
}
