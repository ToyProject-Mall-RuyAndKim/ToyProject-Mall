CREATE TABLE tb_user(
	user_idx INT PRIMARY KEY auto_increment
	, user_id VARCHAR(25) NOT null
	, user_password VARCHAR(100) NOT null
	, user_name VARCHAR(10) NOT null
	, user_rank VARCHAR(10) NOT null
	, user_nickname VARCHAR(10) NOT null
	, user_email VARCHAR(30)
	, user_post_number VARCHAR(6) NOT null
	, user_address VARCHAR(30) NOT null
	, user_address_detail VARCHAR(30)
	, user_phone_number VARCHAR(12) NOT null
	, user_begin_date date
	, user_grant VARCHAR(10) NOT null
	);
CREATE TABLE tb_product(
	product_idx INT PRIMARY KEY auto_increment
	,product_name VARCHAR(30) NOT null
	,product_price INT NOT null
	,product_category INT NOT NULL 
	,product_img_path VARCHAR(200)
	,product_img_name VARCHAR(100)
	,product_begin_date date
    ,FOREIGN KEY(product_category) REFERENCES tb_product_category(category)
);
CREATE TABLE tb_product_category(
    category_name VARCHAR(20)  NOT null
    ,category int	PRIMARY KEY NOT null#1차카테고리
    ,category_ref INT#2차카테고리
);
CREATE TABLE tb_basket(

);
INSERT INTO tb_product_category(category_name,category,category_ref)VALUES("testCategory",100,NULL)
INSERT INTO tb_product_category(category_name,category,category_ref)VALUES("testCategory1",101,100)
SELECT * FROM tb_user
SELECT * FROM tb_product
SELECT * FROM tb_product_category
