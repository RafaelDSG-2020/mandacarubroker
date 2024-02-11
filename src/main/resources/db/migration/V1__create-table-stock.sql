CREATE TABLE stock(
                      id VARCHAR(255) PRIMARY KEY,
                      symbol VARCHAR(50) NOT NULL,
                      company_name VARCHAR(255) NOT NULL,
                      price DOUBLE PRECISION NOT NULL
);