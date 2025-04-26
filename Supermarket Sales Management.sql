USE supermarketdb;

-- Bảng Customers
CREATE TABLE Customers (
    CustomerID VARCHAR(20) PRIMARY KEY,
    ContactName NVARCHAR(100),
    Address NVARCHAR(255),
    City NVARCHAR(100),
    Region NVARCHAR(100),
    Country NVARCHAR(100),
    Phone NVARCHAR(20)
);

-- Bảng Suppliers
CREATE TABLE Suppliers (
    SupplierID VARCHAR(20) PRIMARY KEY,
    CompanyName NVARCHAR(100),
    ContactName NVARCHAR(100),
    Address NVARCHAR(255),
    Phone NVARCHAR(20),
    Country NVARCHAR(100)
);

-- Bảng Categories
CREATE TABLE Categories (
    CategoryID VARCHAR(20) PRIMARY KEY,
    CategoryName NVARCHAR(100),
    Description NVARCHAR(500),
    Picture VARBINARY(500)
);

-- Bảng Products
CREATE TABLE Products (
    ProductID VARCHAR(20) PRIMARY KEY,
    ProductName NVARCHAR(100),
    SupplierID VARCHAR(20),
    CategoryID VARCHAR(20),
    QuantityPerUnit NVARCHAR(50),
    UnitPrice DECIMAL(10, 2),
    UnitsInStock INT,
    UnitsOnOrder INT,
    ReorderLevel INT,
    Discontinued BIT,
    FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID),
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

-- Bảng User
CREATE TABLE User (
    UserID VARCHAR(20) PRIMARY KEY,
    Name NVARCHAR(100),
    SoDienThoai NVARCHAR(20),
    NgaySinh DATE,
    CCCD NVARCHAR(20),
    GioiTinh NVARCHAR(10),
    Email NVARCHAR(100)
);

-- Bảng Account
CREATE TABLE Account (
    UserID VARCHAR(20) PRIMARY KEY,
    Password NVARCHAR(100),
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Bảng Employee
CREATE TABLE Employee (
    UserID VARCHAR(20) PRIMARY KEY,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Bảng SalesAgent
CREATE TABLE SalesAgent (
    AgentID VARCHAR(20) PRIMARY KEY,
    UserID VARCHAR(20),
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Bảng Invoices
CREATE TABLE Invoices (
    InvoiceID VARCHAR(20) PRIMARY KEY,
    CustomerID VARCHAR(20),
    UserID VARCHAR(20),
    OrderDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Bảng InvoiceDetails
CREATE TABLE InvoiceDetails (
    InvoiceID VARCHAR(20),
    ProductID VARCHAR(20),
    UnitPrice DECIMAL(10, 2),
    Quantity INT,
    Discount DECIMAL(5, 2),
    PRIMARY KEY (InvoiceID, ProductID),
    FOREIGN KEY (InvoiceID) REFERENCES Invoices(InvoiceID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Insert dữ liệu mẫu
INSERT INTO Customers VALUES
    ('C1', 'Nguyễn Văn A', '123 Trần Hưng Đạo', 'Hà Nội', 'Miền Bắc', 'Việt Nam', '0905123456'),
    ('C2', 'Trần Thị B', '456 Lê Lợi', 'TP.HCM', 'Miền Nam', 'Việt Nam', '0905789456'),
    ('C3', 'Lê Văn C', '789 Nguyễn Huệ', 'Đà Nẵng', 'Miền Trung', 'Việt Nam', '0905345678');

INSERT INTO Suppliers VALUES
    ('S1', 'Công ty A', 'Mr. Long', '123 Lý Thường Kiệt', '0904112233', 'Việt Nam'),
    ('S2', 'Công ty B', 'Ms. Hạnh', '234 Hoàng Hoa Thám', '0904333444', 'Việt Nam'),
    ('S3', 'Công ty C', 'Mr. Tùng', '345 Điện Biên Phủ', '0904555566', 'Việt Nam');

INSERT INTO Categories VALUES
    ('CAT1', 'Thực phẩm khô', 'Các loại đồ khô như mì, bánh, ngũ cốc.', NULL),
    ('CAT2', 'Đồ uống', 'Nước ngọt, nước khoáng, bia...', NULL),
    ('CAT3', 'Đồ tươi sống', 'Rau củ, thịt cá...', NULL);

INSERT INTO Products VALUES
    ('P1', 'Mì tôm Hảo Hảo', 'S1', 'CAT1', '30 gói/thùng', 105000, 100, 20, 10, 0),
    ('P2', 'Nước suối Aquafina', 'S2', 'CAT2', '24 chai/lốc', 85000, 200, 15, 20, 0),
    ('P3', 'Cá hồi phi lê', 'S3', 'CAT3', '500g/gói', 220000, 50, 10, 5, 0);

INSERT INTO User VALUES
    ('U1', 'Nguyễn Văn D', '0981234567', '1990-01-01', '001199000123', 'Nam', 'd.nguyen@example.com'),
    ('U2', 'Phạm Thị E', '0987654321', '1992-05-10', '001199000456', 'Nữ', 'e.pham@example.com'),
    ('U3', 'Lê Hữu F', '0932345678', '1988-08-20', '001199000789', 'Nam', 'f.le@example.com'),
    ('SA1', 'Sales Admin', '0912345678', '1995-01-01', '001199001111', 'Nam', 'salesadmin@example.com');

-- Insert Accounts
INSERT INTO Account VALUES
    ('U1', 'matkhau1'),
    ('U2', 'matkhau2'),
    ('U3', 'matkhau3'),
    ('SA1', 'matkhauSA1');

-- Insert Employee
INSERT INTO Employee VALUES ('U2');

-- Insert SalesAgent
INSERT INTO SalesAgent VALUES
    ('SA101', 'U1'),
    ('SA102', 'U2');

-- Insert Invoices
INSERT INTO Invoices VALUES
    ('INV1001', 'C1', 'U1', '2024-01-10'),
    ('INV1002', 'C2', 'U2', '2024-02-15'),
    ('INV1003', 'C3', 'U3', '2024-03-20');

-- Insert InvoiceDetails
INSERT INTO InvoiceDetails VALUES
    ('INV1001', 'P1', 3500, 10, 0),
    ('INV1001', 'P2', 3500, 5, 0),
    ('INV1002', 'P2', 3500, 8, 0.1),
    ('INV1003', 'P3', 220000, 2, 0);
