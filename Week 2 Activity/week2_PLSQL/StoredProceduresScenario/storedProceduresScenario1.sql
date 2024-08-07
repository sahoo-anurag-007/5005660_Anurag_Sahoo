CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    CURSOR cur_savings_accounts IS
        SELECT account_id, balance
        FROM accounts
        WHERE account_type = 'Savings';

    v_account_id accounts.account_id%TYPE;
    v_balance accounts.balance%TYPE;
    v_interest_rate CONSTANT NUMBER := 0.01; -- 1% interest rate

BEGIN
    FOR rec IN cur_savings_accounts LOOP
        v_account_id := rec.account_id;
        v_balance := rec.balance;
        
        -- Calculate and update the new balance with interest
        UPDATE accounts
        SET balance = balance + (balance * v_interest_rate)
        WHERE account_id = v_account_id;

        DBMS_OUTPUT.PUT_LINE('Updated balance for account ID ' || v_account_id ||
                             ' with interest: ' || (v_balance * v_interest_rate));
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest processing completed successfully.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        -- Log error to an error table if required
        -- INSERT INTO error_log (error_message, timestamp) VALUES (SQLERRM, SYSDATE);
END ProcessMonthlyInterest;
