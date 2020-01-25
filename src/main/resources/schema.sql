CREATE TABLE "achievements" (
  "uuid" varchar PRIMARY KEY,
  "name" varchar,
  "description" varchar,
  "icon" varchar,
  "category" int
);

CREATE TABLE "series" (
  "uuid" varchar PRIMARY KEY,
  "name" varchar,
  "length" int
);

CREATE TABLE "criteria" (
  "uuid" varchar PRIMARY KEY,
  "required_count" int
);

CREATE TABLE "task" (
  "uuid" varchar PRIMARY KEY,
  "name" varchar,
  "icon" varchar,
  "count" int
);

CREATE TABLE "category" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar,
  "parent_category_id" int
);

CREATE TABLE "achievementSeries" (
  "id" SERIAL PRIMARY KEY,
  "achievement_id" varchar,
  "series_id" varchar
);

CREATE TABLE "achievementCriteria" (
  "id" SERIAL PRIMARY KEY,
  "achievement_id" varchar,
  "criteria_id" varchar
);

CREATE TABLE "metaCriteria" (
  "id" SERIAL PRIMARY KEY,
  "criteria_id" varchar,
  "subcriteria_id" varchar
);

CREATE TABLE "taskCriteria" (
  "id" SERIAL PRIMARY KEY,
  "criteria_id" varchar,
  "task_id" varchar
);

ALTER TABLE "achievements" ADD FOREIGN KEY ("category") REFERENCES "category" ("id");

ALTER TABLE "category" ADD FOREIGN KEY ("parent_category_id") REFERENCES "category" ("id");

ALTER TABLE "achievementSeries" ADD FOREIGN KEY ("achievement_id") REFERENCES "achievements" ("uuid");

ALTER TABLE "achievementSeries" ADD FOREIGN KEY ("series_id") REFERENCES "series" ("uuid");

ALTER TABLE "achievementCriteria" ADD FOREIGN KEY ("achievement_id") REFERENCES "achievements" ("uuid");

ALTER TABLE "achievementCriteria" ADD FOREIGN KEY ("criteria_id") REFERENCES "criteria" ("uuid");

ALTER TABLE "metaCriteria" ADD FOREIGN KEY ("criteria_id") REFERENCES "criteria" ("uuid");

ALTER TABLE "metaCriteria" ADD FOREIGN KEY ("subcriteria_id") REFERENCES "criteria" ("uuid");

ALTER TABLE "taskCriteria" ADD FOREIGN KEY ("criteria_id") REFERENCES "criteria" ("uuid");

ALTER TABLE "taskCriteria" ADD FOREIGN KEY ("task_id") REFERENCES "task" ("uuid");
