
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
	
INSERT INTO Account (Id, DateOpened, Client) VALUES
	(1, '2006-10-01', 444444444),
	(2, '2006-10-15', 222222222);

INSERT INTO Transaction (Id, Fee, DateTime, PricePerShare) VALUES
	(1, 5, '2007-01-01 01:15:12', 34.23),
	(2, 5, '2007-01-01 01:25:12', 90);
 
INSERT INTO Stock (StockSymbol, CompanyName, Type, PricePerShare, NumShares) VALUES
	("GM", "General Motors", "automotive", 34.23, 1000),
	("IBM", "IBM", "computer", 91.41, 500),
	("F", "Ford", "automotive", 9.0, 750);
    
INSERT INTO `Order` (NumShares, PricePerShare, Id, DateTime,
Percentage, PriceType, OrderType, StockId) VALUES
	(75, 34.23, 1, '2007-01-01 01:15:12', NULL, 'Market', 'Buy', "GM"),
	(10, 100, 2, '2007-01-01 01:25:12', 0.1, '‘TrailingStop’', 'Sell', "IBM");


INSERT INTO HasStock (AccountId, StockId, NumShares) VALUES
	(1, "GM", 250),
	(1, "F", 100),
	(2, "IBM", 50);

INSERT INTO Trade (AccountId, BrokerId, TransactionId, 
OrderId, StockId) VALUES
	(1, 1, 1, 1, "GM"),
    (2, 1, 2, 2, "IBM");
        