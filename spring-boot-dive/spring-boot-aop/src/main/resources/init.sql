CREATE TABLE SYS_LOG (
                         ID NUMBER(20) NOT NULL ,
                         USERNAME VARCHAR2(50 BYTE) NULL ,
                         OPERATION VARCHAR2(50 BYTE) NULL ,
                         TIME NUMBER(11) NULL ,
                         METHOD VARCHAR2(200 BYTE) NULL ,
                         PARAMS VARCHAR2(500 BYTE) NULL ,
                         IP VARCHAR2(64 BYTE) NULL ,
                         CREATE_TIME DATE NULL
);
