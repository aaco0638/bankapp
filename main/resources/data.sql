-- Insert into customers without username
INSERT INTO customers (full_name, username) VALUES ('Mike Clarke', 'mikeclarke') ON CONFLICT (full_name) DO NOTHING;
INSERT INTO customers (full_name, username) VALUES ('Tiego Ouedraogo', 'tiego') ON CONFLICT (full_name) DO NOTHING;

-- Insert into accounts
INSERT INTO accounts (customer_id, account_number, sort_code, account_name, account_type, balance)
VALUES
(1, 123456789, 1234, 'Current Account', 'Checking', 123.99)
ON CONFLICT (account_number) DO NOTHING;

INSERT INTO accounts (customer_id, account_number, sort_code, account_name, account_type, balance)
VALUES
(1, 987654321, 1234, 'Savings Account', 'Savings', 500.00)
ON CONFLICT (account_number) DO NOTHING;

INSERT INTO accounts (customer_id, account_number, sort_code, account_name, account_type, balance)
VALUES
(2, 456789123, 1234, 'Checking Account', 'Checking', 1000.00)
ON CONFLICT (account_number) DO NOTHING;

-- Insert into transactions
INSERT INTO transactions (from_account_number, to_account_number, amount, transaction_type, transaction_timestamp)
VALUES
(123456789, 987654321, 50.00, 'TRANSFER', NOW())
ON CONFLICT DO NOTHING;

INSERT INTO transactions (from_account_number, to_account_number, amount, transaction_type, transaction_timestamp)
VALUES
(456789123, NULL, 200.00, 'WITHDRAWAL', NOW())
ON CONFLICT DO NOTHING;

INSERT INTO transactions (from_account_number, to_account_number, amount, transaction_type, transaction_timestamp)
VALUES (NULL, 456789123, 300.00, 'DEPOSIT', NOW())
ON CONFLICT DO NOTHING;

INSERT INTO banking.bank_info (sort_code) VALUES ('1234');
