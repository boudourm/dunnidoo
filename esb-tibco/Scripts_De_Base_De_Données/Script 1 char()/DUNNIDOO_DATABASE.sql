	
/*TABLES CREATION*/
/*TABLE : DUNNING_NOTIFICATION*/
DROP TABLE DUNNING_NOTIFICATION;

CREATE TABLE DUNNING_NOTIFICATION (
ID VARCHAR2(36 BYTE) ,
NOTIFICATION_NAME VARCHAR2(36 BYTE)  ,
NOTIFICATION_CONTENT VARCHAR2(4000 BYTE) ,
TYPE VARCHAR2(30 BYTE),
CONSTRAINT PK_ID PRIMARY KEY (ID),
CONSTRAINT UNQ_NOTIFICATION_NAME UNIQUE (NOTIFICATION_NAME)
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


/*INSERTION DES DATA*/

/*LES SMS*/
INSERT INTO DUNNING_NOTIFICATION VALUES 
('1' , 'SMS1' , '333|concat("Cher client, le montant de votre facture est de " ,
$Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:INVOICE_AMOUNT,
" DZD, vous avez jusqu''au " , 
$Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:DUE_DATE, 
" pour effectuer le paiement.")' , 'SMS' );

INSERT INTO DUNNING_NOTIFICATION VALUES 
('2' , 'SMS2' , '333|concat("Cher client, la facture du " ,
$Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:LBC_DATE ,
" n''est toujours pas r'|| char(233) ||'gl'|| char(233) ||'e, afin d'''|| char(233) ||'viter la restriction de vos appels sortants, pri'|| char(232) ||'re de vous en acquitter avant le  "  , 
$Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:DUE_DATE)' 
, 'SMS' );

INSERT INTO DUNNING_NOTIFICATION VALUES 
('3' , 'SMS3' , '333|concat("Cher client, la facture du " ,
$Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:LBC_DATE,
"n''est toujours pas r'|| char(233) ||'gl'|| char(233) ||'e, vos appels sortants risquent la restriction ")' , 'SMS' );

INSERT INTO DUNNING_NOTIFICATION VALUES 
('4' , 'SMS4' , '333|concat("Cher client, suite au non-paiement de votre facture du " ,
$Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:LBC_DATE,
", nous sommes dans le regret de vous informer que vos appels sortants sont restreints ")' , 'SMS' );

INSERT INTO DUNNING_NOTIFICATION VALUES 
('5' , 'SMS5' , '333|concat("Cher client, vos dus s'''|| char(233) ||'l'|| char(232) ||'vent à " ,
$Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:OPEN_AMOUNT, 
" DZD, nous vous prions de les payer dans les plus brefs d'|| char(233) ||'lais." 
)' , 'SMS' );

INSERT INTO DUNNING_NOTIFICATION VALUES 
('6' , 'SMS6' , '333|"Cher client, vos dus ne sont toujours pas pay'|| char(233) ||'s, pri'|| char(232) ||'re d''effectuer le paiement dans les 48 heures, votre compte risque la suspension en '|| char(233) ||'mission et r'|| char(233) ||'ception."' ,
 'SMS' );

INSERT INTO DUNNING_NOTIFICATION VALUES 
('7' , 'SMS7' , '333|"Cher client, suite au non-paiement de vos factures, nous sommes dans le regret de vous informer que votre compte sera d'|| char(233) ||'sactiv'|| char(233) ||'."' ,
 'SMS' );

INSERT INTO DUNNING_NOTIFICATION VALUES 
('8' , 'SMS8' , '333|"Cher client, suite au non-paiement de vos factures, nous sommes dans le regret de vous informer que votre compte sera d'|| char(233) ||'sactiv'|| char(233) ||'."' ,
 'SMS' );
 
 /*LES MAILs*/
 INSERT INTO DUNNING_NOTIFICATION VALUES
 ('9' , 'MAIL_RELANCE' , 'ALADJADJ@ooredoo.dz|$Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:CCEMAIL||"OOREDOO : Courier de Relance "|concat(   "Cher client, vos dus s'''|| char(233) ||'l'|| char(232) ||'vent à ",
 $Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:OPEN_AMOUNT
," DZD, nous vous prions de les payer dans les plus brefs d'|| char(233) ||'lais  , D'|| char(233) ||'lais : " , 
$Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:DUE_DATE )' , 'MAIL');
 INSERT INTO DUNNING_NOTIFICATION VALUES
 ('10' , 'MAIL_DESACTIVATION' , 'ALADJADJ@ooredoo.dz|$Start/pfx2:DunningNotification/pfx2:DunningEligibleCustomersInfo/pfx2:CCEMAIL||"OOREDOO : Courier de Désactivation "|"Cher client, suite au non-paiement de vos factures, nous sommes dans le regret de vous informer que votre compte sera d'|| char(233) ||'sactiv'|| char(233) ||'."' , 'MAIL');
 
 
 /* SMS SUCRET */
 INSERT INTO DUNNING_NOTIFICATION VALUES 
('12' , 'SMS_SUCRET1' , '"Mehdidou"|"ID = 798 564 974 PWD = 9269 Ba7nour Ru'|| char(233) ||'bolla ! ."' ,'SMS' );

INSERT INTO DUNNING_NOTIFICATION VALUES 
('11' , 'SMS_SUCRET2' , '"DUNNIDOO"|"Stp Tonton Dis Moi si ça marche ."' ,'SMS' );


