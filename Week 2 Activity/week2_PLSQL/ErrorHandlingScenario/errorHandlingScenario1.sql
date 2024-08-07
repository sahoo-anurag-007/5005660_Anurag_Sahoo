CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
) AS
    v_from_balance NUMBER;
    v_to_balance NUMBER;
    insufficient_funds EXCEPTION;
    PRAGMA EXCEPTION_INIT(insufficient_funds, -20001);
BEGIN
    -- Check if the amount is valid
    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Transfer amount must be greater than zero.');
    END IF;

    -- Get the balance of the from_account
    SELECT balance INTO v_from_balance
    FROM accounts
    WHERE account_id = p_from_account_id
    FOR UPDATE;

    -- Check for sufficient funds
    IF v_from_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    -- Deduct the amount from the from_account
    UPDATE accounts
    SET balance = balance - p_amount
    WHERE account_id = p_from_account_id;

    -- Get the balance of the to_account
    SELECT balance INTO v_to_balance
    FROM accounts
    WHERE account_id = p_to_account_id
    FOR UPDATE;

    -- Add the amount to the to_account
    UPDATE accounts
    SET balance = balance + p_amount
    WHERE account_id = p_to_account_id;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Transfer successful from Account ID ' || p_from_account_id ||
                         ' to Account ID ' || p_to_account_id || ' Amount: ' || p_amount);

EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in Account ID ' || p_from_account_id);
        -- Log error to an error table if required
        -- INSERT INTO error_log (error_message, timestamp) VALUES ('Insufficient funds', SYSDATE);

    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        -- Log error to an error table if required
        -- INSERT INTO error_log (error_message, timestamp) VALUES (SQLERRM, SYSDATE);
END SafeTransferFunds;
