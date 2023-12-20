-- ユーザーの作成・権限付与
ALTER SESSION SET CONTAINER = xepdb1;
CREATE USER spring_training_user IDENTIFIED BY systemsss;
GRANT ALL PRIVILEGES TO spring_training_user;

-- 部署テーブルの作成
CREATE TABLE department  (
  dept_id NUMBER(2) PRIMARY KEY,
  dept_name VARCHAR2(15 CHAR) NOT NULL
);

-- 社員テーブルの作成
CREATE TABLE employee (
  emp_id NUMBER(5) PRIMARY KEY,
  emp_pass VARCHAR2(16 CHAR) NOT NULL,
  emp_name VARCHAR2(30 CHAR) NOT NULL,
  gender NUMBER(1) NOT NULL,
  address VARCHAR(60 CHAR) NOT NULL,
  birthday DATE NOT NULL,
  authority NUMBER(1) NOT NULL,
  dept_id NUMBER(2) NOT NULL REFERENCES department(dept_id)
);

-- シーケンスの作成
CREATE SEQUENCE seq_emp NOCACHE;


-- 部署テーブルへのレコード登録
INSERT INTO department VALUES(1, '営業部');
INSERT INTO department VALUES(2, '経理部');
INSERT INTO department VALUES(3, '総務部');

-- 社員テーブルへのレコード登録
INSERT INTO employee VALUES(seq_emp.nextval,'1111','鈴木太郎',1,'東京都','1986/10/12',1,1);
INSERT INTO employee VALUES(seq_emp.nextval,'2222','田中二郎',1,'千葉県','1979/07/02',2,2);
INSERT INTO employee VALUES(seq_emp.nextval,'3333','渡辺花子',2,'大阪府','1988/04/23',2,2);

COMMIT;
