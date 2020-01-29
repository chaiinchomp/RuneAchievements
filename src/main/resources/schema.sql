CREATE TABLE "achievements" (
  "uuid" varchar PRIMARY KEY,
  "name" varchar,
  "description" varchar,
  "icon" varchar,
  "required_count" int,
  "series_ordinal" int,
  "series_id" varchar,
  "category_id" int
);

CREATE TABLE "series" (
  "uuid" varchar PRIMARY KEY,
  "name" varchar,
  "length" int
);

CREATE TABLE "tasks" (
  "uuid" varchar PRIMARY KEY,
  "name" varchar,
  "icon" varchar
);

CREATE TABLE "categories" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar,
  "parent_category_id" int
);

CREATE TABLE "metaAchievement" (
  "id" SERIAL PRIMARY KEY,
  "achievement_id" varchar,
  "subachievement_id" varchar
);

CREATE TABLE "achievementTasks" (
  "id" SERIAL PRIMARY KEY,
  "achievement_id" varchar,
  "task_id" varchar
);

ALTER TABLE "achievements" ADD FOREIGN KEY ("series_id") REFERENCES "series" ("uuid");

ALTER TABLE "achievements" ADD FOREIGN KEY ("category_id") REFERENCES "categories" ("id");

ALTER TABLE "categories" ADD FOREIGN KEY ("parent_category_id") REFERENCES "categories" ("id");

ALTER TABLE "metaAchievement" ADD FOREIGN KEY ("achievement_id") REFERENCES "achievements" ("uuid");

ALTER TABLE "metaAchievement" ADD FOREIGN KEY ("subachievement_id") REFERENCES "achievements" ("uuid");

ALTER TABLE "achievementTasks" ADD FOREIGN KEY ("achievement_id") REFERENCES "achievements" ("uuid");

ALTER TABLE "achievementTasks" ADD FOREIGN KEY ("task_id") REFERENCES "tasks" ("uuid");
