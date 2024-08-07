CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id IN NUMBER,
    p_amount IN NUMBER
) RETURN BOOLEAN AS
    v_balance NUMBER;
BEGIN
    -- Retrieve the current balance of the account
    SELECT balance INTO v_balance
    FROM accounts
    WHERE account_id = p_account_id;

    -- Check if the balance is sufficient
    IF v_balance >= p_amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE; -- Account not found, treat as insufficient balance

    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        RETURN FALSE; -- Handle other exceptions and treat as insufficient balance
END HasSufficientBalance;
