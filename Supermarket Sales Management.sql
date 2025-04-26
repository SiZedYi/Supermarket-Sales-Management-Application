use supermarketdb;
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

-- Bảng User
CREATE TABLE User (
                      UserID INT PRIMARY KEY AUTO_INCREMENT,
                      Name NVARCHAR(100),
                      SoDienThoai NVARCHAR(20),
                      NgaySinh DATE,
                      CCCD NVARCHAR(20),
                      GioiTinh NVARCHAR(10),
                      Email NVARCHAR(100)
);

-- Bảng Account
CREATE TABLE Account (
                         UserID INT PRIMARY KEY AUTO_INCREMENT,
                         Password NVARCHAR(100),
                         FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Bảng SalesAgent
CREATE TABLE SalesAgent (
                            AgentID INT PRIMARY KEY AUTO_INCREMENT,
                            UserID INT,
                            FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Bảng Invoices
CREATE TABLE Invoices (
                          InvoiceID INT PRIMARY KEY AUTO_INCREMENT,
                          CustomerID INT,
                          UserID INT,
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

INSERT INTO Customers VALUES
                          (1, 'Nguyễn Văn A', '123 Trần Hưng Đạo', 'Hà Nội', 'Miền Bắc', 'Việt Nam', '0905123456'),
                          (2, 'Trần Thị B', '456 Lê Lợi', 'TP.HCM', 'Miền Nam', 'Việt Nam', '0905789456'),
                          (3, 'Lê Văn C', '789 Nguyễn Huệ', 'Đà Nẵng', 'Miền Trung', 'Việt Nam', '0905345678');

INSERT INTO Suppliers VALUES
                          (1, 'Công ty A', 'Mr. Long', '123 Lý Thường Kiệt', '0904112233', 'Việt Nam'),
                          (2, 'Công ty B', 'Ms. Hạnh', '234 Hoàng Hoa Thám', '0904333444', 'Việt Nam'),
                          (3, 'Công ty C', 'Mr. Tùng', '345 Điện Biên Phủ', '0904555566', 'Việt Nam');

INSERT INTO Categories VALUES
                           (1, 'Thực phẩm khô', 'Các loại đồ khô như mì, bánh, ngũ cốc.', NULL),
                           (2, 'Đồ uống', 'Nước ngọt, nước khoáng, bia...', NULL),
                           (3, 'Đồ tươi sống', 'Rau củ, thịt cá...', NULL);

INSERT INTO Products VALUES
                         (1, 'Mì tôm Hảo Hảo', 1, 1, '30 gói/thùng', 105000, 100, 20, 10, 0),
                         (2, 'Nước suối Aquafina', 2, 2, '24 chai/lốc', 85000, 200, 15, 20, 0),
                         (3, 'Cá hồi phi lê', 3, 3, '500g/gói', 220000, 50, 10, 5, 0);

INSERT INTO User VALUES
                     (1, 'Nguyễn Văn D', '0981234567', '1990-01-01', '001199000123', 'Nam', 'd.nguyen@example.com'),
                     (2, 'Phạm Thị E', '0987654321', '1992-05-10', '001199000456', 'Nữ', 'e.pham@example.com'),
                     (3, 'Lê Hữu F', '0932345678', '1988-08-20', '001199000789', 'Nam', 'f.le@example.com');

INSERT INTO Account VALUES
                        (1, 'matkhau1'),
                        (2, 'matkhau2'),
                        (3, 'matkhau3');
INSERT INTO Employee VALUES (2)
INSERT INTO SalesAgent VALUES
                           (101, 1),
                           (102, 2);

INSERT INTO Invoices VALUES
                         (1001, 1, 1, '2024-01-10'),
                         (1002, 2, 2, '2024-02-15'),
                         (1003, 3, 3, '2024-03-20');

INSERT INTO InvoiceDetails VALUES
                               (1001, 1, 3500, 10, 0),
                               (1001, 2, 3500, 5, 0),
                               (1002, 2, 3500, 8, 0.1),
                               (1003, 3, 220000, 2, 0);
