DECLARE
    CURSOR cur_accounts IS
        SELECT account_id, balance
        FROM Accounts;

    v_account_id Accounts.account_id%TYPE;
    v_balance Accounts.balance%TYPE;
    v_annual_fee CONSTANT NUMBER := 50.00; -- Annual maintenance fee

BEGIN
    OPEN cur_accounts;

    -- Loop through each account
    LOOP
        FETCH cur_accounts INTO v_account_id, v_balance;
        EXIT WHEN cur_accounts%NOTFOUND;

        -- Deduct the annual maintenance fee from the balance
        UPDATE Accounts
        SET balance = balance - v_annual_fee
        WHERE account_id = v_account_id;

        DBMS_OUTPUT.PUT_LINE('Annual fee applied to Account ID: ' || v_account_id || 
                             ', New Balance: ' || (v_balance - v_annual_fee));
    END LOOP;

    CLOSE cur_accounts;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual fees applied to all accounts successfully.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        -- Optionally log error to a table
        -- INSERT INTO error_log (error_message, timestamp) VALUES (SQLERRM, SYSDATE);
END;
