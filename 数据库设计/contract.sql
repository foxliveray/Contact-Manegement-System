
drop database if exists contractdb;

/*==============================================================*/
/* Database: contractdb                                         */
/*==============================================================*/
create database contractdb;

use contractdb;

/*==============================================================*/
/* Table: t_customer                                            */
/*==============================================================*/
create table customer
(
   id                   int not null primary key auto_increment, -- Auto-increase ID, a unique identifier, primary key
   num                  varchar(20) not null, -- customer number
   name                 varchar(40) not null, -- customer name
   address              varchar(200) not null,-- customer address
   tel                  varchar(20) not null,-- customer phone
   fax                  varchar(20),-- customer fax
   code                 varchar(10),-- customer postcode
   bank                 varchar(50),-- bank name
   account              varchar(50),-- bank account
   del                  int not null default 0 -- Delete mark: 0-Not deleted,1-Deleted
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table user
(
   id                   int not null primary key auto_increment, -- Auto-increase ID, a unique identifier, primary key
   name                 varchar(40) not null, -- user name
   password             varchar(20) not null,-- password
   del                  int not null default 0 -- Delete mark: 0-Not deleted,1-Deleted
);

/*==============================================================*/
/* Table: t_contract                                            */
/*==============================================================*/
create table contract
(
   id                   int not null primary key auto_increment, -- Auto-increase ID, a unique identifier, primary key
   user_id              int,-- user id
   customer             varchar(40),-- customer
   num                  varchar(20) not null,-- contract number
   name                 varchar(40) not null, -- contract name
   beginTime            date not null,-- begin time
   endTime              date not null,-- end time
   content              text not null,-- contract content
   del                  int not null default 0 -- Delete mark: 0-Not deleted,1-Deleted
);

/*==============================================================*/
/* Table: t_contract_process                                    */
/*==============================================================*/
create table contract_process
(
   id                   int not null primary key auto_increment, -- Auto-increase ID, a unique identifier, primary key
   con_id               int not null,-- contract ID, quote t_contract table
   user_id              int not null,-- user ID, quote t_user table
   type                 int not null,-- operation type: 1-Countersign, 2-Approve, 3-Sign
   state                int not null default 0,-- operation state: 1-Unfinished, 2-Completed, 3-Rejected
   content              text,-- operation content
   time                 timestamp  not null default CURRENT_TIMESTAMP,-- operation time
   del                  int not null default 0, -- Delete mark: 0-Not deleted,1-Deleted
   constraint FK_contract_process foreign key (con_id)
      references contract (id) ,
   constraint FK_process_user foreign key (user_id)
      references user (id) 
);

/*==============================================================*/
/* Table: t_contract_state                                      */
/*==============================================================*/
create table contract_state
(
   id                   int not null primary key auto_increment, -- Auto-increase ID, a unique identifier, primary key
   con_id               int not null,-- contract ID, quote t_contract table
   type                 int not null,-- operation type: 1-Draft, 2-Countersigned, 3-Finalized, 4-Approved, 5-Signed
   time                 timestamp not null default CURRENT_TIMESTAMP,-- operation time
   del                  int not null default 0, -- Delete mark: 0-Not deleted,1-Deleted
   constraint FK_belong foreign key (con_id)
      references contract (id) 
);

/*==============================================================*/
/* Table: t_contract_attachment                                 */
/*==============================================================*/
create table contract_attachment
(
   id                   int not null primary key auto_increment, -- Auto-increase ID, a unique identifier, primary key
   con_id               int not null,-- contract id
   fileName             varchar(40) not null, -- attachment name
   path                 varchar(100) not null,-- attachment path
   type                 varchar(10) not null,-- attachment type
   uploadTime           timestamp not null default CURRENT_TIMESTAMP,-- upload date
   del                  int not null default 0 -- Delete mark: 0-Not deleted,1-Deleted
);

/*==============================================================*/
/* Table: t_function                                            */
/*==============================================================*/
create table function
(
   id                   int not null primary key auto_increment, -- Auto-increase ID, a unique identifier, primary key
   num                  varchar(10) not null,-- function number
   name                 varchar(40) not null,-- function name
   URL                  varchar(200),-- function path
   description          varchar(200),-- function description
   del                  int not null default 0 -- Delete mark: 0-Not deleted,1-Deleted
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table role
(
   id                   int not null primary key auto_increment, -- Auto-increase ID, a unique identifier, primary key
   name                 varchar(40) not null,-- role name
   description          varchar(200),-- role description
   function_ids         varchar(500),-- function id combination
   del                  int not null default 0 -- Delete mark: 0-Not deleted,1-Deleted
);

/*==============================================================*/
/* Table: t_right                                               */
/*==============================================================*/
create table t_right
(
   id                   int not null primary key auto_increment, -- Auto-increase ID, a unique identifier, primary key
   user_id              int not null,-- user ID, quote t_user table
   role_id              int not null,-- role ID, quote t_role table
   description          varchar(200),-- right description
   del                  int not null default 0, -- Delete mark: 0-Not deleted,1-Deleted
   constraint FK_right_u foreign key (user_id)
      references user (id) ,
   constraint FK_right_r foreign key (role_id)
      references role (id) 
);

/*==============================================================*/
/* Table: t_log                                                 */
/*==============================================================*/
create table log
(
   id                   int not null primary key auto_increment, -- Auto-increase ID, a unique identifier, primary key
   user_id              int not null,-- operator id
   time                 timestamp not null default CURRENT_TIMESTAMP,-- operation time
   content              text not null,-- log content
   del                  int not null default 0 -- Delete mark: 0-Not deleted,1-Deleted
);