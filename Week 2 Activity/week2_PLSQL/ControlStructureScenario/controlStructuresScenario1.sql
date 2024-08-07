DECLARE
    CURSOR cur_customer IS
        SELECT customer_id, age, loan_interest_rate
        FROM customers
        WHERE age > 60;
        
    v_customer_id customers.customer_id%TYPE;
    v_age customers.age%TYPE;
    v_loan_interest_rate customers.loan_interest_rate%TYPE;

BEGIN
    FOR rec_customer IN cur_customer LOOP
        v_customer_id := rec_customer.customer_id;
        v_age := rec_customer.age;
        v_loan_interest_rate := rec_customer.loan_interest_rate;
        
        -- Apply 1% discount to the loan interest rate
        UPDATE customers
        SET loan_interest_rate = v_loan_interest_rate - (v_loan_interest_rate * 0.01)
        WHERE customer_id = v_customer_id;
        
        DBMS_OUTPUT.PUT_LINE('Updated interest rate for customer ID: ' || v_customer_id);
    END LOOP;

    COMMIT;
END;
