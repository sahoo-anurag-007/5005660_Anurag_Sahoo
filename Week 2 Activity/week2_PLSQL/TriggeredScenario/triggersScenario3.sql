CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
    insufficient_funds EXCEPTION;
    negative_deposit EXCEPTION;
BEGIN
    -- Retrieve the current balance of the account
    SELECT balance INTO v_balance
    FROM accounts
    WHERE account_id = :NEW.account_id
    FOR UPDATE; -- Lock the account row to prevent concurrent modifications

    -- Check for withdrawal rules
    IF :NEW.transaction_type = 'Withdrawal' THEN
        IF :NEW.amount > v_balance THEN
            RAISE insufficient_funds;
        END IF;
    END IF;

    -- Check for deposit rules
    IF :NEW.transaction_type = 'Deposit' THEN
        IF :NEW.amount <= 0 THEN
            RAISE negative_deposit;
        END IF;
    END IF;

EXCEPTION
    WHEN insufficient_funds THEN
        -- Raise an application error if the withdrawal exceeds the balance
        RAISE_APPLICATION_ERROR(-20009, 'Error: Withdrawal amount exceeds account balance.');

    WHEN negative_deposit THEN
        -- Raise an application error if the deposit amount is not positive
        RAISE_APPLICATION_ERROR(-20010, 'Error: Deposit amount must be positive.');
END CheckTransactionRules;
