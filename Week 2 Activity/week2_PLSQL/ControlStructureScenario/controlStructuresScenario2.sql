DECLARE
    CURSOR cur_customer IS
        SELECT customer_id, balance
        FROM customers
        WHERE balance > 10000;
        
    v_customer_id customers.customer_id%TYPE;
    v_balance customers.balance%TYPE;

BEGIN
    FOR rec_customer IN cur_customer LOOP
        v_customer_id := rec_customer.customer_id;
        v_balance := rec_customer.balance;
        
        -- Set IsVIP flag to TRUE
        UPDATE customers
        SET IsVIP = TRUE
        WHERE customer_id = v_customer_id;
        
        DBMS_OUTPUT.PUT_LINE('Promoted to VIP status for customer ID: ' || v_customer_id);
    END LOOP;

    COMMIT;
END;
