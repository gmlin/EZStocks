
INSERT INTO User (Username, Password, SSN, LastName, FirstName, Address, City, State, ZipCode, PhoneNumber) VALUES
	("yshang", "123", 111111111, "Yang", "Shang", "123 Success Street", "Stony Brook", "NY", 11790, 5166328959),
	("dvictor", "123", 222222222, "Du", "Victor", "456 Fortune Road", "Stony Brook", "NY", 11790, 5166324360),
	("sjohn", "123", 333333333, "Smith", "John", "789 Peace Blvd.", "Los Angeles", "CA", 93536, 3154434321),
	("plewis", "123", 444444444, "Philip", "Lewis", "135 Knowledge Lane", "Stony Brook", "NY", 11794, 5166668888),
	("sdavid", "123", 123456789, "Smith", "David", "123 College road", "Stony Brook", "NY", 11790, 5162152345),
	("wdavid", "123", 789123456, "Warren", "David", "456 Sunken Street", "Stony Brook", "NY", 11794, 6316329987);

INSERT INTO Employee (Id, StartDate, HourlyRate, Type) VALUES
	(123456789, '2005-11-01', 60, "Broker"),
	(789123456, '2006-02-02', 50, "Manager");

INSERT INTO Client (Id, Email, CC, Rating) VALUES
	(111111111, "syang@cs.sunysb.edu", 1234567812345678, 1),
	(222222222, "vicdu@cs.sunysb.edu", 5678123456781234, 1),
	(333333333, "jsmith@ic.sunysb.edu", 2345678923456789, 1),
	(444444444, "pml@cs.sunysb.edu", 6789234567892345, 1);
	
INSERT INTO Account (Client, AccountNum, DateOpened) VALUES
	(444444444, 1, '2006-10-01'),
	(222222222, 1, '2006-10-15');

INSERT INTO Transaction (Id, Fee, DateTime, PricePerShare) VALUES
	(1, 5, '2007-01-01 01:15:12', 34.23),
	(2, 5, '2007-01-01 01:25:12', 90);
 
INSERT INTO Stock (Symbol, Company, Type, PricePerShare, NumShares) VALUES
	("GM", "General Motors", "automotive", 34.23, 1000),
	("IBM", "IBM", "computer", 91.41, 500),
	("F", "Ford", "automotive", 9.0, 750);
    
INSERT INTO `Order` (Id, Stock, NumShares, DateTime, PricePerShare,
Percentage, PriceType, OrderType) VALUES
	(1, "GM", 75, '2007-01-01 01:15:12', 34.23, NULL, 'Market', 'Buy'),
	(2, "IBM", 10, '2007-01-01 01:25:12', 100, 0.1, 'TrailingStop', 'Sell');

INSERT INTO AccountStock (Client, AccountNum, Stock, NumShares) VALUES
	(444444444, 1, "GM", 250),
	(444444444, 1, "F", 100),
	(222222222, 1, "IBM", 50);

INSERT INTO Trade (Client, AccountNum, Employee, Transaction, 
`Order`, Stock) VALUES
	(444444444, 1, 123456789, 1, 1, "GM"),
    (222222222, 1, 123456789, 2, 2, "IBM");
        