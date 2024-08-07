DECLARE
    CURSOR cur_transactions IS
        SELECT t.customer_id, c.customer_name, t.transaction_date, t.transaction_type, t.amount
        FROM Transactions t
        JOIN Customers c ON t.customer_id = c.customer_id
        WHERE EXTRACT(MONTH FROM t.transaction_date) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.transaction_date) = EXTRACT(YEAR FROM SYSDATE)
        ORDER BY t.customer_id, t.transaction_date;

    v_customer_id Transactions.customer_id%TYPE;
    v_customer_name Customers.customer_name%TYPE;
    v_transaction_date Transactions.transaction_date%TYPE;
    v_transaction_type Transactions.transaction_type%TYPE;
    v_amount Transactions.amount%TYPE;

BEGIN
    OPEN cur_transactions;

    -- Loop through each transaction record for the current month
    LOOP
        FETCH cur_transactions INTO v_customer_id, v_customer_name, v_transaction_date, v_transaction_type, v_amount;
        EXIT WHEN cur_transactions%NOTFOUND;

        -- Print a statement for each customer transaction
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_customer_id || ', Name: ' || v_customer_name);
        DBMS_OUTPUT.PUT_LINE('Date: ' || TO_CHAR(v_transaction_date, 'YYYY-MM-DD') || 
                             ', Type: ' || v_transaction_type || 
                             ', Amount: ' || v_amount);
        DBMS_OUTPUT.PUT_LINE('---------------------------------------------------');
    END LOOP;

    CLOSE cur_transactions;
    DBMS_OUTPUT.PUT_LINE('Monthly statements generated successfully.');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        -- Optionally log error to a table
        -- INSERT INTO error_log (error_message, timestamp) VALUES (SQLERRM, SYSDATE);
END;
