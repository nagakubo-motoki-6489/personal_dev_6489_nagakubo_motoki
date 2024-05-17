-- カテゴリーテーブルデータ
INSERT INTO categories(priority) VALUES('高');
INSERT INTO categories(priority) VALUES('中');
INSERT INTO categories(priority) VALUES('低');

-- ユーザーテーブルデータ
INSERT INTO users ( email, name, password) VALUES('tanaka@aaa.com', '田中太郎', 'test123');
INSERT INTO users ( email, name, password) VALUES('yamatai@country.com', 'やまだ', 'tyekerattyo');

-- タスクテーブルデータ
INSERT INTO tasks(category_id, title, closing_date, progress, memo) VALUES(1, 'お試し', '2024-10-30', 1, 'test');
INSERT INTO tasks(category_id, title, closing_date, progress, memo) VALUES(2, 'A社打合せ資料作成', '2024-05-01', 2, 'お得意先');
INSERT INTO tasks(category_id, title, closing_date, progress, memo) VALUES(1, '部内M資料作成', '2024-05-29', 3, '早めにやろう');
INSERT INTO tasks(category_id, title, closing_date, progress, memo) VALUES(3, '備品発注', '2024-05-17', 1, '石鹸');
