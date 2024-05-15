-- カテゴリーテーブルデータ
INSERT INTO categories(priority) VALUES('高');
INSERT INTO categories(priority) VALUES('中');
INSERT INTO categories(priority) VALUES('低');

-- ユーザーテーブルデータ
INSERT INTO users ( email, name, password) VALUES('tanaka@aaa.com', '田中太郎', 'test123');

-- タスクテーブルデータ
INSERT INTO tasks(category_id, title, closing_date, progress, memo) VALUES(1, 'お試し', '2024-10-30', 0, 'test');
