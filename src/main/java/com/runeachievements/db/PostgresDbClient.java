package com.runeachievements.db;

import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp.BasicDataSource;

@RequiredArgsConstructor
public class PostgresDbClient {

    private final BasicDataSource dataSource;

}
