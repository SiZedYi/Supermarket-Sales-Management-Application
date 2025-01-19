Use [Supermarket Sales Management]
-- Bảng Customers
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    ContactName NVARCHAR(100),
    Address NVARCHAR(255),
    City NVARCHAR(100),
    Region NVARCHAR(100),
    Country NVARCHAR(100),
    Phone NVARCHAR(20)
);

-- Bảng Suppliers
CREATE TABLE Suppliers (
    SupplierID INT PRIMARY KEY,
    CompanyName NVARCHAR(100),
    ContactName NVARCHAR(100),
    Address NVARCHAR(255),
    Phone NVARCHAR(20),
    Country NVARCHAR(100)
);

-- Bảng Categories
CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY,
    CategoryName NVARCHAR(100),
    Description NVARCHAR(MAX),
    Picture VARBINARY(MAX)
);

-- Bảng Products
CREATE TABLE Products (
    ProductID INT PRIMARY KEY,
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
CREATE TABLE [User] (
    UserID INT PRIMARY KEY,
    Name NVARCHAR(100),
    SoDienThoai NVARCHAR(20),
    NgaySinh DATE,
    CCCD NVARCHAR(20),
    GioiTinh NVARCHAR(10),
    Email NVARCHAR(100)
);

-- Bảng Account
CREATE TABLE Account (
    UserID INT PRIMARY KEY,
    Password NVARCHAR(100),
    FOREIGN KEY (UserID) REFERENCES [User](UserID)
);

-- Bảng SalesAgent
CREATE TABLE SalesAgent (
    AgentID INT PRIMARY KEY,
    UserID INT,
    FOREIGN KEY (UserID) REFERENCES [User](UserID)
);

-- Bảng Invoices
CREATE TABLE Invoices (
    InvoiceID INT PRIMARY KEY,
    CustomerID INT,
    UserID INT,
    OrderDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (UserID) REFERENCES [User](UserID)
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
