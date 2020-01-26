package com.runeachievements.db;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import static org.jooq.generated.tables.Tasks.TASKS;

@RequiredArgsConstructor
public class PostgresDbClient {

    private final DSLContext db;

    public void testDbInsert() {
        // just a quick stub for testing that everything is configured correctly

        db.insertInto(TASKS)
                .set(TASKS.UUID, "uuid")
                .set(TASKS.NAME, "achievement name")
                .set(TASKS.ICON, "icon.jpg")
                .execute();
    }

}
