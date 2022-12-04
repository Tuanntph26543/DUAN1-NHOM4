﻿CREATE DATABASE QLBANTS
go
use QLBANTS
go
CREATE TABLE BAN(
	MABAN	INT	IDENTITY(1,1)	not null,
	TENBAN NVARCHAR(50) NULL,
	SONGUOINGOIMAX INT NULL,
	TRANGTHAI INT NULL,
	CONSTRAINT BAN_PK PRIMARY KEY (MABAN)
)
CREATE TABLE GIAOCA(
	MACA	INT	IDENTITY(1,1) PRIMARY KEY	not null,
	THOIGIANNHANCA DATETIME NULL,
	THOIGIANGIAOCA DATETIME NULL,
	MANHANVIENTRONGCA INT NULL,
	MANHANVIENNHANCA INT NULL,
	TIENBANDAU MONEY NULL,
	TONGTIENTRONGCA MONEY NULL,
	THOIGIANRESET DATETIME NULL,
	MAQUANLIRESET INT NULL,
	TONGTIENRUT MONEY NULL,
	TRANGTHAI INT NULL,
	FOREIGN KEY (MANHANVIENTRONGCA) REFERENCES USERTT,
	FOREIGN KEY (MANHANVIENNHANCA) REFERENCES USERTT,
	FOREIGN KEY (MAQUANLIRESET) REFERENCES USERTT,
)
CREATE TABLE KHUYENMAI(
	MAKM	INT	IDENTITY(1,1) NOT NULL,
	TENKM NVARCHAR(50) NULL,
	SOTIENKM MONEY NULL,
	NGAYBD DATE NULL,
	NGAYKT DATE NULL,
	TRANGTHAI INT NULL,	
	CONSTRAINT KHUYENMAI_PK PRIMARY KEY (MAKM)
)
CREATE TABLE CHUCVU(
	MACV	INT	IDENTITY(1,1)NOT NULL,
	TECV NVARCHAR(50) NULL,
	CONSTRAINT CHUCVU_PK PRIMARY KEY (MACV)
)
CREATE TABLE NCC(
	MANCC	INT	IDENTITY(1,1)NOT NULL,
	TENNCC NVARCHAR(50) NULL,
	DIACHI NVARCHAR(50) NULL,
	SDT NVARCHAR(10) NULL,
	CONSTRAINT NCC_PK PRIMARY KEY (MANCC)
)


CREATE TABLE NGUYENLIEU(
	MANL	INT	IDENTITY(1,1) NOT NULL,
	TENNL NVARCHAR(50) NULL,
	MOTA NVARCHAR(50) NULL,
	CONSTRAINT NGUYENLIEU_PK PRIMARY KEY (MANL)
)
CREATE TABLE USERTT(
	MANV	INT	IDENTITY(1,1) NOT NULL,
	MACV INT NULL,
	HOTEN NVARCHAR(50) NULL,
	GIOITINH NVARCHAR(20) NULL,
	NAMSINH INT NULL,
	DIACHI NVARCHAR(50) NULL,
	CCCD NVARCHAR(12) NULL,
	SDT NVARCHAR(10) NULL,
	TAIKHOAN NVARCHAR(50) unique NULL,
	MATKHAU NVARCHAR(50) NULL,
	TRANGTHAI INT NULL,
	CONSTRAINT USERTT_FK FOREIGN KEY (MACV) REFERENCES CHUCVU,
	CONSTRAINT USERTT_PK PRIMARY KEY (MANV)
)
CREATE TABLE GIAOCA(
	MACA	INT	IDENTITY(1,1) PRIMARY KEY	not null,
	THOIGIANNHANCA DATETIME NULL,
	THOIGIANGIAOCA DATETIME NULL,
	MANHANVIENTRONGCA INT NULL,
	MANHANVIENNHANCA INT NULL,
	TIENBANDAU MONEY NULL,
	TONGTIENTRONGCA MONEY NULL,
	THOIGIANRESET DATETIME NULL,
	MAQUANLIRESET INT NULL,
	TONGTIENRUT MONEY NULL,
	TRANGTHAI INT NULL,
	FOREIGN KEY (MANHANVIENTRONGCA) REFERENCES USERTT,
	FOREIGN KEY (MANHANVIENNHANCA) REFERENCES USERTT,
	FOREIGN KEY (MAQUANLIRESET) REFERENCES USERTT,
)
CREATE TABLE KHACHHANG(
	MAKH	INT	IDENTITY(1,1)	PRIMARY KEY,
	TENKH NVARCHAR(20) NULL,
	DIACHI NVARCHAR(20) NULL,
	SDT NVARCHAR(20) NULL,
)
CREATE TABLE HOADON(
	MAHD INT	IDENTITY(1,1)NOT NULL,
	MANV INT NULL,
	MAKH INT NULL,
	TINHTRANG INT NULL,
	NGAYTAO DATETIME NULL,
	FOREIGN KEY (MANV) REFERENCES USERTT,
	FOREIGN KEY (MAKH) REFERENCES KHACHHANG,		
	CONSTRAINT HOADON_PK PRIMARY KEY (MAHD)
)
CREATE TABLE HOADONNHAPNL(
	MAHDNNL	INT	IDENTITY(1,1)NOT NULL,
	MANV INT NULL,
	NGAYNHAP DATE NULL,
	FOREIGN KEY (MANV) REFERENCES USERTT,	
	CONSTRAINT HOADONNHAPNL_PK PRIMARY KEY (MAHDNNL)
)
CREATE TABLE CHITIETNHAPNL(
	MACHITIETNNL INT IDENTITY(1,1) NOT NULL,
	MANL INT  NULL,
	MAHDNNL INT  NULL,
	MANCC INT  NULL,
	SOLUONG INT NULL,
	THANHTIEN MONEY NULL,
	FOREIGN KEY (MANL) REFERENCES NGUYENLIEU,	
	FOREIGN KEY (MAHDNNL) REFERENCES HOADONNHAPNL,	
	FOREIGN KEY (MANCC) REFERENCES NCC,	
	CONSTRAINT CHITIETNHAPNL_PK PRIMARY KEY (MACHITIETNNL)
)
CREATE TABLE SANPHAM(
	MASP	INT	IDENTITY(1,1) NOT NULL,
	MAKM INT NULL,
	TENSP NVARCHAR(50) NULL,
	ANHSP VARCHAR(150) NULL,
	GIABAN MONEY NULL,
	TRANGTHAI INT NULL,
	FOREIGN KEY (MAKM) REFERENCES KHUYENMAI,
	CONSTRAINT SANPHAM_PK PRIMARY KEY (MASP)
)

CREATE TABLE HOADONCHITIET(
	MAHOADONCT	INT	IDENTITY(1,1) NOT NULL,
	MAHD INT  null,
	MASP INT  NULL,
	MABAN INT  NULL,
	SOLUONG INT NULL,
	DONGIA MONEY NULL,
	MOTA NVARCHAR(50) null,
	FOREIGN KEY (MASP) REFERENCES SANPHAM,
	FOREIGN KEY (MABAN) REFERENCES BAN,	
	FOREIGN KEY (MAHD) REFERENCES HOADON,
	CONSTRAINT HOADONCHITIET_PK PRIMARY KEY (MAHOADONCT) 
)
--drop table KHACHHANG
select * from USERTT
select * from Ban
update USERTT set HOTEN='Đinh Đức Đạt'
select * from USERTT
delete GIAOCA
insert into GIAOCA(THOIGIANNHANCA,TIENBANDAU,TONGTIENTRONGCA,TRANGTHAI) values(GETDATE(),1000000,1000000,0)
select * from giaoca
INSERT INTO GiaoCa(THOIGIANNHANCA,TIENBANDAU,TRANGTHAI) values(GETDATE(),10000,0)
delete GIAOCA where MACA=15
order by NGAYTAO desc
select * from sanpham
select * from hoadonchitiet where masp >0
delete sanpham
delete CHUCVU
select * from SANPHAM
update SANPHAM set GIABAN=340444
update ban set TRANGTHAI=0
select * from ban
select * from usertt
insert into CHUCVU(tecv) values('nhanvien')
insert into USERTT(macv,HOTEN,gioitinh,namsinh,diachi,cccd,sdt,taikhoan,matkhau,trangthai) values(1,'Đinh Đức Đạt','Nam',2003,'hanoi','11212','0129212','tk01','tk02',1)
insert into USERTT(macv,HOTEN,gioitinh,namsinh,diachi,cccd,sdt,taikhoan,matkhau,trangthai) values(1,'NTDanh','Nam',2003,'hanoi','11212','0129212','tk02','tk03',1)

insert into KHACHHANG(TENKH,DIACHI,SDT) values('Nguyễn Thành Danh','Hà Nội','0385090080')
insert into ban(tenban,songuoingoimax,trangthai) values('Bàn 1',10,0)
insert into ban(tenban,songuoingoimax,trangthai) values('Bàn 2',20,0)

INSERT INTO KHUYENMAI(TENKM,SOTIENKM,NGAYBD,NGAYKT,TRANGTHAI) VALUES('KM1',1000,'2022-10-12','2022-12-12',1)
INSERT INTO KHUYENMAI(TENKM,SOTIENKM,NGAYBD,NGAYKT,TRANGTHAI) VALUES('KM2',2000,'2022-10-12','2022-12-12',1)
INSERT INTO KHUYENMAI(TENKM,SOTIENKM,NGAYBD,NGAYKT,TRANGTHAI) VALUES('KM3',3000,'2022-10-12','2022-12-12',1)
INSERT INTO SANPHAM(MAKM,TENSP,ANHSP,GIABAN,TRANGTHAI) VALUES(3,'Trà Đào Cam Sả','D:\DUAN1\BanTraSua_HT\src\main\java\poly\nhom4\img\1.png',60000,0)
INSERT INTO SANPHAM(MAKM,TENSP,ANHSP,GIABAN,TRANGTHAI) VALUES(3,'Trà Đào Hạnh Phúc','D:\DUAN1\BanTraSua_HT\src\main\java\poly\nhom4\img\1.png',70000,0)
INSERT INTO SANPHAM(MAKM,TENSP,ANHSP,GIABAN,TRANGTHAI) VALUES(3,'Trà Sữa','D:\DUAN1\BanTraSua_HT\src\main\java\poly\nhom4\img\2.png',80000,0)
INSERT INTO SANPHAM(MAKM,TENSP,ANHSP,GIABAN,TRANGTHAI) VALUES(3,'Tra Chanh','D:\DUAN1\BanTraSua_HT\src\main\java\poly\nhom4\img\3.png',70000,0)

insert into hoadon(manv,makh,tinhtrang,ngaytao) values(1,1,0,'2022-11-12')
insert into hoadon(manv,makh,tinhtrang,ngaytao) values(1,1,0,'2022-11-12')














