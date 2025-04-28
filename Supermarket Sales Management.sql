USE supermarketdb;

-- Bảng Customers
CREATE TABLE Customers (
                           CustomerID INT PRIMARY KEY AUTO_INCREMENT,
                           ContactName NVARCHAR(100),
                           Address NVARCHAR(255),
                           City NVARCHAR(100),
                           Region NVARCHAR(100),
                           Country NVARCHAR(100),
                           Phone NVARCHAR(20)
);

-- Bảng Suppliers
CREATE TABLE Suppliers (
                           SupplierID INT PRIMARY KEY AUTO_INCREMENT,
                           CompanyName NVARCHAR(100),
                           ContactName NVARCHAR(100),
                           Address NVARCHAR(255),
                           Phone NVARCHAR(20),
                           Country NVARCHAR(100)
);

-- Bảng Categories
CREATE TABLE Categories (
                            CategoryID INT PRIMARY KEY AUTO_INCREMENT,
                            CategoryName NVARCHAR(100),
                            Description NVARCHAR(500),
                            Picture VARBINARY(500)
);

-- Bảng Products
CREATE TABLE Products (
                          ProductID INT PRIMARY KEY AUTO_INCREMENT,
                          ProductName NVARCHAR(100),
                          SupplierID INT,
                          CategoryID INT,
                          QuantityPerUnit NVARCHAR(50),
                          UnitPrice DECIMAL(10, 2),
                          UnitsInStock INT,
                          UnitsOnOrder INT,
                          ReorderLevel INT,
                          Discontinued BIT,
                          FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID),
                          FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

-- Bảng User (UserID là string)
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
                         Password NVARCHAR(100)
);

-- Bảng Employee
CREATE TABLE Employee (
                          UserID VARCHAR(20) PRIMARY KEY,
                          FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Bảng SalesAgent
CREATE TABLE SalesAgent (
                            AgentID INT PRIMARY KEY AUTO_INCREMENT,
                            UserID VARCHAR(20),
                            FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Bảng Invoices
CREATE TABLE Invoices (
                          InvoiceID INT PRIMARY KEY AUTO_INCREMENT,
                          CustomerID INT,
                          UserID VARCHAR(20),
                          OrderDate DATE,
                          FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
                          FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Bảng InvoiceDetails
CREATE TABLE InvoiceDetails (
                                InvoiceID INT,
                                ProductID INT,
                                UnitPrice DECIMAL(10, 2),
                                Quantity INT,
                                Discount DECIMAL(5, 2),
                                PRIMARY KEY (InvoiceID, ProductID),
                                FOREIGN KEY (InvoiceID) REFERENCES Invoices(InvoiceID),
                                FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Insert dữ liệu mẫu

-- Customers
INSERT INTO Customers (ContactName, Address, City, Region, Country, Phone) VALUES
                                                                               ('Nguyễn Văn A', '123 Trần Hưng Đạo', 'Hà Nội', 'Miền Bắc', 'Việt Nam', '0905123456'),
                                                                               ('Trần Thị B', '456 Lê Lợi', 'TP.HCM', 'Miền Nam', 'Việt Nam', '0905789456'),
                                                                               ('Lê Văn C', '789 Nguyễn Huệ', 'Đà Nẵng', 'Miền Trung', 'Việt Nam', '0905345678');

-- Suppliers
INSERT INTO Suppliers (CompanyName, ContactName, Address, Phone, Country) VALUES
                                                                              ('Công ty A', 'Mr. Long', '123 Lý Thường Kiệt', '0904112233', 'Việt Nam'),
                                                                              ('Công ty B', 'Ms. Hạnh', '234 Hoàng Hoa Thám', '0904333444', 'Việt Nam'),
                                                                              ('Công ty C', 'Mr. Tùng', '345 Điện Biên Phủ', '0904555566', 'Việt Nam');

-- Categories
INSERT INTO Categories (CategoryName, Description, Picture) VALUES
                                                                ('Thực phẩm khô', 'Các loại đồ khô như mì, bánh, ngũ cốc.', NULL),
                                                                ('Đồ uống', 'Nước ngọt, nước khoáng, bia...', NULL),
                                                                ('Đồ tươi sống', 'Rau củ, thịt cá...', NULL);

-- Products
INSERT INTO Products (ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued) VALUES
                                                                                                                                                   ('Mì tôm Hảo Hảo', 1, 1, '30 gói/thùng', 105000, 100, 20, 10, 0),
                                                                                                                                                   ('Nước suối Aquafina', 2, 2, '24 chai/lốc', 85000, 200, 15, 20, 0),
                                                                                                                                                   ('Cá hồi phi lê', 3, 3, '500g/gói', 220000, 50, 10, 5, 0);

-- User
INSERT INTO User (UserID, Name, SoDienThoai, NgaySinh, CCCD, GioiTinh, Email) VALUES
                                                                                  ('U1', 'Nguyễn Văn D', '0981234567', '1990-01-01', '001199000123', 'Nam', 'd.nguyen@example.com'),
                                                                                  ('U2', 'Phạm Thị E', '0987654321', '1992-05-10', '001199000456', 'Nữ', 'e.pham@example.com'),
                                                                                  ('U3', 'Lê Hữu F', '0932345678', '1988-08-20', '001199000789', 'Nam', 'f.le@example.com'),
                                                                                  ('SA1', 'Sales Admin', '0912345678', '1995-01-01', '001199001111', 'Nam', 'salesadmin@example.com');

-- Account
INSERT INTO Account (UserID, Password) VALUES
                                           ('U1', 'matkhau1'),
                                           ('U2', 'matkhau2'),
                                           ('U3', 'matkhau3'),
                                           ('SA1', 'matkhauSA1');

-- Employee
INSERT INTO Employee (UserID) VALUES ('U2');

-- SalesAgent (AgentID tự tăng)
INSERT INTO SalesAgent (UserID) VALUES
                                    ('U1'),
                                    ('U2');

-- Invoices (InvoiceID tự tăng)
INSERT INTO Invoices (CustomerID, UserID, OrderDate) VALUES
                                                         (1, 'U1', '2024-01-10'),
                                                         (2, 'U2', '2024-02-15'),
                                                         (3, 'U3', '2024-03-20');

-- InvoiceDetails
INSERT INTO InvoiceDetails VALUES
                               (1, 1, 3500, 10, 0),
                               (1, 2, 3500, 5, 0),
                               (2, 2, 3500, 8, 0.1),
                               (3, 3, 220000, 2, 0);
