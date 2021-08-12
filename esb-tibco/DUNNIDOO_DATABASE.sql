    
/*TABLES CREATION*/
/*TABLE : DUNNING_ACTIONS*/
DROP TABLE DUNNING_ACTIONS;

CREATE TABLE DUNNING_ACTIONS (
ID VARCHAR2(36 BYTE) ,
ACTION_NAME VARCHAR2(36 BYTE)  ,
ACTION_CONTENT VARCHAR2(4000 BYTE) ,
TYPE VARCHAR2(30 BYTE),
CONSTRAINT PK_ID PRIMARY KEY (ID),
CONSTRAINT UNQ_ACTION_NAME UNIQUE (ACTION_NAME)
);

/*TABLE : ELIGIBLE_DUNNING_CUSTOMERS*/
DROP TABLE DUNNING_ELIGIBLE_CUSTOMERS ;

CREATE TABLE DUNNING_ELIGIBLE_CUSTOMERS (
CUSTOMER_ID INTEGER ,
CUSTCODE VARCHAR(32 BYTE) ,
SEGMENT VARCHAR2(100 BYTE) ,
INVOICE_AMOUNT INTEGER ,
OPEN_AMOUNT FLOAT ,
DUE_DATE DATE ,
LBC_DATE DATE ,
CCLNAME VARCHAR2(40 BYTE) ,
MSISDN VARCHAR2(100  BYTE) ,
CCEMAIL VARCHAR2(200 BYTE) ,
CONSTRAINT PK_DUNNING_ELIGIBLE_CUSTOMERS PRIMARY KEY (CUSTOMER_ID),
CONSTRAINT NTNLL_CUSTCODE CHECK (CUSTCODE IS NOT NULL),
CONSTRAINT UNQ_CUSTCODE UNIQUE (CUSTCODE),
CONSTRAINT CHK_INVOICE_AMOUNT CHECK (INVOICE_AMOUNT > 0) ,
CONSTRAINT CHK_OPEN_AMOUNT CHECK (OPEN_AMOUNT > 0) ,
CONSTRAINT NTNLL_CCLNAME CHECK (CCLNAME IS NOT NULL) ,
CONSTRAINT CHK_CCEMAIL CHECK (CCEMAIL LIKE '%@%.%')  ,
CONSTRAINT NTNLL_MSISDN CHECK (MSISDN IS NOT NULL) ,
CONSTRAINT CHK_LBC_DATE_DUE_DATE CHECK (LBC_DATE < DUE_DATE) 
);


DROP TABLE BSCS_TE ;

CREATE TABLE BSCS_TE (
CUSTOMER_ID INTEGER ,
CUSTCODE VARCHAR(32 BYTE) ,
INVOICE_AMOUNT INTEGER ,
OPEN_AMOUNT FLOAT ,
DUE_DATE DATE ,
ENTRY_DATE DATE ,
CONSTRAINT PK_DUNNING_ELIGIBLE_CUSTOMERS PRIMARY KEY (CUSTOMER_ID),
CONSTRAINT NTNLL_CUSTCODE CHECK (CUSTCODE IS NOT NULL),
CONSTRAINT UNQ_CUSTCODE UNIQUE (CUSTCODE),
CONSTRAINT CHK_INVOICE_AMOUNT CHECK (INVOICE_AMOUNT > 0) ,
CONSTRAINT CHK_OPEN_AMOUNT CHECK (OPEN_AMOUNT > 0) ,
CONSTRAINT CHK_LBC_DATE_DUE_DATE CHECK (ENTRY_DATE < DUE_DATE) 
);



/*INSERTION DES DATA*/

/*LES SMS*/
INSERT INTO DUNNING_ACTIONS VALUES 
('1' , 'SMS1' , '333|concat("Cher client, le montant de votre facture est de " ,
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:INVOICE_AMOUNT,
" DZD, vous avez jusqu''au " , 
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:DUE_DATE, 
" pour effectuer le paiement.")' , 'SMS' );

INSERT INTO DUNNING_ACTIONS VALUES 
('2' , 'SMS2' , '333|concat("Cher client, la facture du " ,
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:LBC_DATE ,
" n''est toujours pas r",tib:hex-to-string("C3A9" , "UTF-8"),"gl",tib:hex-to-string("C3A9" , "UTF-8"),"e, afin d''",tib:hex-to-string("C3A9" , "UTF-8"),"viter la restriction de vos appels sortants, pri",tib:hex-to-string("C3A8" , "UTF-8"),"re de vous en acquitter avant le  "  , 
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:DUE_DATE,".")' 
, 'SMS' );

INSERT INTO DUNNING_ACTIONS VALUES 
('3' , 'SMS3' , '333|concat("Cher client, la facture du " ,
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:LBC_DATE,
" n''est toujours pas r",tib:hex-to-string("C3A9" , "UTF-8"),"gl",tib:hex-to-string("C3A9" , "UTF-8"),"e, vos appels sortants risquent la restriction.")' , 'SMS' );

INSERT INTO DUNNING_ACTIONS VALUES 
('4' , 'SMS4' , '333|concat("Cher client, suite au non-paiement de votre facture du " ,
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:LBC_DATE,
", nous sommes dans le regret de vous informer que vos appels sortants sont restreints.")' , 'SMS' );

INSERT INTO DUNNING_ACTIONS VALUES 
('5' , 'SMS5' , '333|concat("Cher client, vos dus s''",tib:hex-to-string("C3A9" , "UTF-8"),"l",tib:hex-to-string("C3A8" , "UTF-8"),"vent ",tib:hex-to-string("C3A0" , "UTF-8")," " ,
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:OPEN_AMOUNT, 
" DZD, nous vous prions de les payer dans les plus brefs d",tib:hex-to-string("C3A9" , "UTF-8"),"lais." 
)' , 'SMS' );

INSERT INTO DUNNING_ACTIONS VALUES 
('6' , 'SMS6' , '333|concat("Cher client, vos dus ne sont toujours pas pay",tib:hex-to-string("C3A9" , "UTF-8"),"s, pri",tib:hex-to-string("C3A8" , "UTF-8"),"re d''effectuer le paiement dans les 48 heures, votre compte risque la suspension en ",tib:hex-to-string("C3A9" , "UTF-8"),"mission et r",tib:hex-to-string("C3A9" , "UTF-8"),"ception.")' ,
 'SMS' );

INSERT INTO DUNNING_ACTIONS VALUES 
('7' , 'SMS7' , '333|"Cher client, suite au non-paiement de vos factures, nous sommes dans le regret de vous informer que vos appels sortants et entrants sont suspendus."' ,
 'SMS' );

INSERT INTO DUNNING_ACTIONS VALUES 
('8' , 'SMS8' , '333|concat("Cher client, suite au non-paiement de vos factures, nous sommes dans le regret de vous informer que votre compte sera d",tib:hex-to-string("C3A9" , "UTF-8"),"sactiv",tib:hex-to-string("C3A9" , "UTF-8"),".")' ,
 'SMS' );
 
 /*LES MAILs*/
 INSERT INTO DUNNING_ACTIONS VALUES
 ('9' , 'MAIL_RELANCE' , 'ALADJADJ@ooredoo.dz|$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:CCEMAIL||"OOREDOO : Courier de Relance "|concat(   "Cher client, vos dus s''",tib:hex-to-string("C3A9" , "UTF-8"),"l",tib:hex-to-string("C3A8" , "UTF-8"),"vent ",tib:hex-to-string("C3A0" , "UTF-8")," ",
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:OPEN_AMOUNT
," DZD, nous vous prions de les payer dans les plus brefs d",tib:hex-to-string("C3A9" , "UTF-8"),"lais  , D",tib:hex-to-string("C3A9" , "UTF-8"),"lais : " , 
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:DUE_DATE,".")' , 'MAIL');

 INSERT INTO DUNNING_ACTIONS VALUES
 ('10' , 'MAIL_DESACTIVATION' , 'ALADJADJ@ooredoo.dz|$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:CCEMAIL||concat("OOREDOO : Courier de D",tib:hex-to-string("C3A9" , "UTF-8"),"sactivation ")|concat("Cher client, suite au non-paiement de vos factures, nous sommes dans le regret de vous informer que votre compte sera d",tib:hex-to-string("C3A9" , "UTF-8"),"sactiv",tib:hex-to-string("C3A9" , "UTF-8"),".")' , 'MAIL');
 
 INSERT INTO DUNNING_ACTIONS VALUES 
 ('11' ,'BAROUT' , '14|49|2|Restriction des appels sortants' , 'BAR' );
 
 INSERT INTO DUNNING_ACTIONS VALUES 
 ('12' , 'BARIN' , '51|||Restriction des appels entrants' , 'BAR');
 
INSERT INTO DUNNING_ACTIONS VALUES
('13' , 'DESACTIVATION' , '4|33||' , 'DESACTIVATION') ;

INSERT INTO DUNNING_ACTIONS VALUES 
('14' 'IVR1' , '||concat(   "Cher client, vos dus s''",tib:hex-to-string("C3A9" , "UTF-8"),"l",tib:hex-to-string("C3A8" , "UTF-8"),"vent ",tib:hex-to-string("C3A0" , "UTF-8")," ",
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:OPEN_AMOUNT
," DZD, nous vous prions de les payer dans les plus brefs d",tib:hex-to-string("C3A9" , "UTF-8"),"lais  , D",tib:hex-to-string("C3A9" , "UTF-8"),"lais : " , 
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:DUE_DATE,".")' , 'IVR');

INSERT INTO DUNNING_ACTIONS VALUES 
('15' 'IVR2' , '||concat(   "Cher client, vos dus s''",tib:hex-to-string("C3A9" , "UTF-8"),"l",tib:hex-to-string("C3A8" , "UTF-8"),"vent ",tib:hex-to-string("C3A0" , "UTF-8")," ",
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:OPEN_AMOUNT
," DZD, nous vous prions de les payer dans les plus brefs d",tib:hex-to-string("C3A9" , "UTF-8"),"lais  , D",tib:hex-to-string("C3A9" , "UTF-8"),"lais : " , 
$Start/pfx2:DunningAction/pfx2:DunningEligibleCustomersInfo/pfx2:DUE_DATE,".")' , 'IVR');

 
 /* NOTIFS SUCRETTES */
 INSERT INTO DUNNING_ACTIONS VALUES 
('13' , 'SMS_SUCRET' , '"0555354165"|"ID = 798 564 974 PWD = 525jpn  YO  ! ."' ,'SMS' );

 INSERT INTO DUNNING_ACTIONS VALUES 
('14' , 'MAIL_SUCRET' , 'ALADJADJ@ooredoo.dz|$Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:CCEMAIL||"YO !!"|"ID = 798 564 974 PWD = 525jpn  YO  !"' ,'MAIL' );



INSERT INTO DUNNING_ACTIONS VALUES 
('15' , 'SMS_SUCRET2' , '"DUNNIDOO"|"Stp Tonton Dis Moi si ça marche ."' ,'SMS' );


SELECT * FROM DUNNING_ACTIONS