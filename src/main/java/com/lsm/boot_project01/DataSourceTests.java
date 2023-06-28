package com.lsm.boot_project01;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

@Log4j2

public class DataSourceTests {
    @Autowired
    private DataSource dataSource;

}
