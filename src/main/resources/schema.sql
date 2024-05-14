-- 各種テーブル削除
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tasks;

-- categories テーブルを作成するクエリ
CREATE TABLE categories (
id SERIAL PRIMARY KEY,
priority TEXT
);

-- users テーブルを作成するクエリ
CREATE TABLE users (
id SERIAL PRIMARY KEY,
email TEXT,
name TEXT,
password TEXT
);

-- tasks テーブルを作成するクエリ
CREATE TABLE tasks (
id SERIAL PRIMARY KEY,
category_id INTEGER,
user_id INTEGER,
title TEXT,
closing_date DATE,
progress INTEGER,
memo TEXT
);
