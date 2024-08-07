DECLARE
    CURSOR cur_loans IS
        SELECT l.loan_id, l.due_date, c.customer_id, c.customer_name
        FROM loans l
        JOIN customers c ON l.customer_id = c.customer_id
        WHERE l.due_date BETWEEN SYSDATE AND SYSDATE + 30;
        
    v_loan_id loans.loan_id%TYPE;
    v_due_date loans.due_date%TYPE;
    v_customer_id customers.customer_id%TYPE;
    v_customer_name customers.customer_name%TYPE;

BEGIN
    FOR rec_loans IN cur_loans LOOP
        v_loan_id := rec_loans.loan_id;
        v_due_date := rec_loans.due_date;
        v_customer_id := rec_loans.customer_id;
        v_customer_name := rec_loans.customer_name;
        
        -- Print reminder message
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ID ' || v_loan_id || ' for customer ' || v_customer_name || 
                             ' (ID: ' || v_customer_id || ') is due on ' || TO_CHAR(v_due_date, 'DD-MON-YYYY'));
    END LOOP;
END;
