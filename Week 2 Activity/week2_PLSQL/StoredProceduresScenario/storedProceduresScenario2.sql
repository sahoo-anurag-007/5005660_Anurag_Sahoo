CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department_id IN NUMBER,
    p_bonus_percentage IN NUMBER
) AS
    CURSOR cur_employees IS
        SELECT employee_id, salary
        FROM employees
        WHERE department_id = p_department_id;

    v_employee_id employees.employee_id%TYPE;
    v_salary employees.salary%TYPE;

BEGIN
    -- Check if the bonus percentage is valid
    IF p_bonus_percentage <= 0 THEN
        RAISE_APPLICATION_ERROR(-20006, 'Bonus percentage must be greater than zero.');
    END IF;

    FOR rec IN cur_employees LOOP
        v_employee_id := rec.employee_id;
        v_salary := rec.salary;

        -- Update the salary with the bonus percentage
        UPDATE employees
        SET salary = salary + (salary * p_bonus_percentage / 100)
        WHERE employee_id = v_employee_id;

        DBMS_OUTPUT.PUT_LINE('Updated salary for Employee ID ' || v_employee_id ||
                             ' with bonus percentage: ' || p_bonus_percentage);
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Employee bonus update completed successfully.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        -- Log error to an error table if required
        -- INSERT INTO error_log (error_message, timestamp) VALUES (SQLERRM, SYSDATE);
END UpdateEmployeeBonus;
