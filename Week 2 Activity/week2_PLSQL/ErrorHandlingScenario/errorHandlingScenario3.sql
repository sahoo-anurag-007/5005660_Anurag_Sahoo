CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id IN NUMBER,
    p_customer_name IN VARCHAR2,
    p_age IN NUMBER,
    p_balance IN NUMBER
) AS
    duplicate_customer EXCEPTION;
    PRAGMA EXCEPTION_INIT(duplicate_customer, -20005);
BEGIN
    -- Insert the new customer
    INSERT INTO customers (customer_id, customer_name, age, balance)
    VALUES (p_customer_id, p_customer_name, p_age, p_balance);

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Customer added successfully with ID: ' || p_customer_id);

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_customer_id || ' already exists.');
        -- Log error to an error table if required
        -- INSERT INTO error_log (error_message, timestamp) VALUES ('Duplicate customer ID', SYSDATE);

    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        -- Log error to an error table if required
        -- INSERT INTO error_log (error_message, timestamp) VALUES (SQLERRM, SYSDATE);
END AddNewCustomer;
