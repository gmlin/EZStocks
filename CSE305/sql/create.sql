CREATE Table User (
	Username VARCHAR(20) NOT NULL,
	Password VARCHAR(20) NOT NULL,
	SSN INTEGER NOT NULL,
	LastName VARCHAR(20) NOT NULL,
	FirstName VARCHAR(20) NOT NULL,
	Address VARCHAR(30) NOT NULL,
	City VARCHAR(20) NOT NULL,
	State CHAR(2) NOT NULL,
	ZipCode INTEGER NOT NULL,
	PhoneNumber BIGINT NOT NULL,
	PRIMARY KEY (Username),
	UNIQUE (SSN)
);

CREATE Table Client (
	Id INTEGER NOT NULL,
	Email VARCHAR(30) NOT NULL,
	CC BIGINT NOT NULL,
	Rating INTEGER NOT NULL,
	PRIMARY KEY (Id),
	FOREIGN KEY (Id) REFERENCES User (SSN)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	CONSTRAINT CheckClientId
		CHECK (Id > 0 AND Id < 1000000000)
);

CREATE Table Employee (
	Id INTEGER NOT NULL,
	StartDate DATE NOT NULL,
	HourlyRate DOUBLE NOT NULL,
	Type VARCHAR(10) NOT NULL,
	PRIMARY KEY (Id),
	FOREIGN KEY (Id) REFERENCES User (SSN)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	CONSTRAINT CheckEmpId
		CHECK (Id > 0 AND Id < 1000000000),
	CONSTRAINT CheckEmpType 
		CHECK (Type IN ('Broker', 'Manager'))
);

CREATE Table Account (
	Client INTEGER NOT NULL,
	AccountNum INTEGER NOT NULL,
	DateOpened DATE NOT NULL,
	PRIMARY KEY (Client, AccountNum),
	FOREIGN KEY (Client) REFERENCES Client (Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
);

CREATE Table Transaction (
	Id INTEGER NOT NULL,
	Fee DOUBLE NOT NULL,
	DateTime DATETIME NOT NULL,
	PricePerShare DOUBLE NOT NULL,
	PRIMARY KEY (Id)
);

CREATE Table Stock (
	Symbol VARCHAR(5) NOT NULL,
	Company VARCHAR(20) NOT NULL,
	Type VARCHAR(20) NOT NULL,
	PricePerShare DOUBLE NOT NULL,
	NumShares INTEGER NOT NULL,
	PRIMARY KEY (Symbol)
);

CREATE Table `Order` (
	Id INTEGER NOT NULL,
	Stock VARCHAR(5) NOT NULL,
	NumShares INTEGER NOT NULL,
	DateTime DATETIME NOT NULL,
	PricePerShare DOUBLE,
	Percentage DOUBLE,
	PriceType VARCHAR(20),
	OrderType CHAR(1),
	PRIMARY KEY (Id),
	FOREIGN KEY (Stock) REFERENCES Stock (Symbol)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	CONSTRAINT CheckPriceType
		CHECK (PriceType IN (‘Market’, ‘MarketOnClose’, ‘TrailingStop’, ‘HiddenStop’)),
	CONSTRAINT CheckOrderType
		CHECK (OrderType IN (‘B’, ‘S’))
);

CREATE Table AccountStock (
	Client INTEGER NOT NULL,
	AccountNum INTEGER NOT NULL,
	Stock VARCHAR(5) NOT NULL,
	NumShares INTEGER NOT NULL,
	PRIMARY KEY (Client, AccountNum, Stock),
	FOREIGN KEY (Client, AccountNum) REFERENCES Account (Client, AccountNum)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	FOREIGN KEY (Stock) REFERENCES Stock (Symbol)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
);

CREATE Table Trade (
	Client INTEGER NOT NULL,
	AccountNum INTEGER NOT NULL,
	Employee INTEGER NOT NULL,
	Transaction INTEGER NOT NULL,
	`Order` INTEGER NOT NULL,
	Stock VARCHAR(5) NOT NULL,
	PRIMARY KEY (Client, AccountNum, Employee, Transaction, `Order`, Stock),
	FOREIGN KEY (Client, AccountNum) REFERENCES Account (Client, AccountNum)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	FOREIGN KEY (Employee) REFERENCES Employee (Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	FOREIGN KEY (Transaction) REFERENCES Transaction (Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	FOREIGN KEY (`Order`) REFERENCES `Order` (Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	FOREIGN KEY (Stock) REFERENCES Stock (Symbol)
		ON DELETE NO ACTION
		ON UPDATE CASCADE
);