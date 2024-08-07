CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id IN NUMBER,
    p_percentage IN NUMBER
) AS
    v_current_salary NUMBER;
    employee_not_found EXCEPTION;
    PRAGMA EXCEPTION_INIT(employee_not_found, -20003);
BEGIN
    -- Check if the percentage is valid
    IF p_percentage <= 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Percentage increase must be greater than zero.');
    END IF;

    -- Get the current salary of the employee
    SELECT salary INTO v_current_salary
    FROM employees
    WHERE employee_id = p_employee_id
    FOR UPDATE;

    -- Update the salary with the given percentage
    UPDATE employees
    SET salary = salary + (salary * p_percentage / 100)
    WHERE employee_id = p_employee_id;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Salary updated for Employee ID ' || p_employee_id ||
                         ' by ' || p_percentage || '%');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_employee_id || ' not found.');
        -- Log error to an error table if required
        -- INSERT INTO error_log (error_message, timestamp) VALUES ('Employee not found', SYSDATE);

    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        -- Log error to an error table if required
        -- INSERT INTO error_log (error_message, timestamp) VALUES (SQLERRM, SYSDATE);
END UpdateSalary;
