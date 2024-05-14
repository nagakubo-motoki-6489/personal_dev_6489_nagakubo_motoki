-- カテゴリーテーブルデータ
INSERT INTO categories(priority) VALUES('高');
INSERT INTO categories(priority) VALUES('中');
INSERT INTO categories(priority) VALUES('低');

-- ユーザーテーブルデータ
INSERT INTO users (id, email, name, password) VALUES(1, 'tanaka@aaa.com', '田中太郎', 'test123');

-- タスクテーブルデータ
INSERT INTO tasks(id, category_id, user_id, title, closing_date, progress, memo) VALUES(1, 1, 1, 'お試し', '2024-10-30', 0, 'test');
