CREATE SEQUENCE smdb_main.company_user_id_seq START with 1;
CREATE TABLE smdb_main.COMPANY_USER (
  COMPANY_USER_ID BIGINT DEFAULT nextval('smdb_main.company_user_id_seq') NOT NULL,
  CODE VARCHAR(25),
  FULL_NAME VARCHAR(255),
  OWNERSHIP VARCHAR(255),
  SHORT_NAME VARCHAR(255),
  CONSTRAINT PK__company_user_id PRIMARY KEY (COMPANY_USER_ID)
);

CREATE SEQUENCE smdb_main.user_id_seq START with 1;
CREATE TABLE smdb_main.USER (
  USER_ID BIGINT DEFAULT nextval('smdb_main.user_id_seq') NOT NULL,
  USERNAME VARCHAR(255) NOT NULL,
  EMAIL VARCHAR(255),
  FIRST_NAME VARCHAR(255),
  LAST_NAME VARCHAR(255),
  PASSWORD VARCHAR(500),
  ROLE VARCHAR(255),
  PHONE_NUMBER VARCHAR(255),
  IS_COMPANY BOOLEAN NOT NULL,
  COMPANY_DATA_ID BIGINT,
  DISCOUNT INT NOT NULL DEFAULT 0,
  DATE_OF_REGISTRATION TIMESTAMP NOT NULL,
  TIMESTAMP_OF_PASS_REC DATE,
  PASS_REC_CODE VARCHAR(255),
  ACCOUNT_NON_EXPIRED BOOLEAN NOT NULL,
  ACCOUNT_NON_LOCKED BOOLEAN NOT NULL,
  CREDENTIALS_NON_EXPIRED BOOLEAN NOT NULL,
  ENABLED BOOLEAN NOT NULL,
  CONSTRAINT PK__user_id PRIMARY KEY (USER_ID),
  CONSTRAINT UK__user_username UNIQUE (USERNAME),
  CONSTRAINT UK__user_email UNIQUE (EMAIL),
  CONSTRAINT FK__user_company_data_id FOREIGN KEY (COMPANY_DATA_ID)
        REFERENCES smdb_main.COMPANY_USER (COMPANY_USER_ID)
);

CREATE SEQUENCE smdb_main.product_group_id_seq START with 1;
CREATE TABLE smdb_main.PRODUCT_GROUP (
  PRODUCT_GROUP_ID BIGINT DEFAULT nextval('smdb_main.product_group_id_seq') NOT NULL,
  NAME VARCHAR(255) NOT NULL,
  PARENT_ID BIGINT,
  CONSTRAINT PK__product_group_id PRIMARY KEY (PRODUCT_GROUP_ID),
  CONSTRAINT FK__product_group_parent_id FOREIGN KEY (PARENT_ID)
        REFERENCES smdb_main.PRODUCT_GROUP (PRODUCT_GROUP_ID)
);

CREATE SEQUENCE smdb_main.product_id_seq START with 1;
CREATE TABLE smdb_main.PRODUCT (
  PRODUCT_ID BIGINT DEFAULT nextval('smdb_main.product_id_seq') NOT NULL,
  ARTICLE VARCHAR(255),
  DESCRIPTION VARCHAR(255),
  MAIN_PICTURE VARCHAR(255),
  MEASUREMENT_UNITS VARCHAR(255),
  NAME VARCHAR(255),
  PRICE FLOAT NOT NULL,
  PRODUCT_GROUP_ID BIGINT,
  NUMBER_OF_RATINGS BIGINT NOT NULL DEFAULT 0,
  RATING FLOAT NOT NULL DEFAULT 0,
  CONSTRAINT PK__product_id PRIMARY KEY (PRODUCT_ID),
  CONSTRAINT UK__product_article UNIQUE (ARTICLE),
  CONSTRAINT FK__product_group_id FOREIGN KEY (PRODUCT_GROUP_ID)
        REFERENCES smdb_main.PRODUCT_GROUP (PRODUCT_GROUP_ID)
);

CREATE SEQUENCE smdb_main.invoice_id_seq START with 1;
CREATE TABLE smdb_main.INVOICE (
  INVOICE_ID BIGINT DEFAULT nextval('smdb_main.invoice_id_seq') NOT NULL,
  DATE TIMESTAMP NOT NULL,
  NOTE VARCHAR(255),
  PAYED FLOAT NOT NULL,
  PAYMENT_TYPE VARCHAR(255),
  STATUS VARCHAR(255),
  SUM FLOAT NOT NULL,
  CUSTOMER_ID BIGINT,
  CONSTRAINT PK__invoice_id PRIMARY KEY (INVOICE_ID),
  CONSTRAINT FK__invoice_customer_id FOREIGN KEY (CUSTOMER_ID)
        REFERENCES smdb_main.USER (USER_ID)
);

CREATE SEQUENCE smdb_main.product_to_buy_id_seq START with 1;
CREATE TABLE smdb_main.PRODUCT_TO_BUY (
  PRODUCT_TO_BUY_ID BIGINT DEFAULT nextval('smdb_main.product_to_buy_id_seq') NOT NULL,
  NUMBER INT NOT NULL,
  INVOICE_ID BIGINT NOT NULL,
  PRODUCT_ID BIGINT NOT NULL,
  CONSTRAINT PK__product_to_buy_id PRIMARY KEY (PRODUCT_TO_BUY_ID),
  CONSTRAINT FK__product_to_buy_invoice FOREIGN KEY (INVOICE_ID)
        REFERENCES smdb_main.INVOICE (INVOICE_ID),
  CONSTRAINT FK__product_to_buy_product FOREIGN KEY (PRODUCT_ID)
        REFERENCES smdb_main.PRODUCT (PRODUCT_ID)
);

CREATE SEQUENCE smdb_main.image_id_seq START with 1;
CREATE TABLE smdb_main.IMAGE (
  IMAGE_ID BIGINT DEFAULT nextval('smdb_main.image_id_seq') NOT NULL,
  PICTURE VARCHAR(255) NOT NULL,
  PRODUCT_ID BIGINT NOT NULL,
  CONSTRAINT PK__image_id PRIMARY KEY (IMAGE_ID),
  CONSTRAINT FK__image_product FOREIGN KEY (PRODUCT_ID)
        REFERENCES smdb_main.PRODUCT (PRODUCT_ID)
);

CREATE SEQUENCE smdb_main.review_id_seq START with 1;
CREATE TABLE smdb_main.REVIEW (
  REVIEW_ID BIGINT DEFAULT nextval('smdb_main.review_id_seq') NOT NULL,
  DATE TIMESTAMP,
  RATING FLOAT NOT NULL,
  COMMENT VARCHAR(255),
  PRODUCT_ID BIGINT,
  USER_ID BIGINT,
  CONSTRAINT PK__review_id PRIMARY KEY (REVIEW_ID),
  CONSTRAINT FK__review_product FOREIGN KEY (PRODUCT_ID)
        REFERENCES smdb_main.PRODUCT (PRODUCT_ID),
  CONSTRAINT FK__review_user FOREIGN KEY (USER_ID)
        REFERENCES smdb_main.USER (USER_ID)
);