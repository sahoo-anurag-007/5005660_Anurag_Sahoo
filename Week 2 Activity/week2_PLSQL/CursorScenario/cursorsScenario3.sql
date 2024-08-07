DECLARE
    CURSOR cur_loans IS
        SELECT loan_id, interest_rate
        FROM Loans;

    v_loan_id Loans.loan_id%TYPE;
    v_interest_rate Loans.interest_rate%TYPE;
    v_new_interest_rate NUMBER;

BEGIN
    OPEN cur_loans;

    -- Loop through each loan
    LOOP
        FETCH cur_loans INTO v_loan_id, v_interest_rate;
        EXIT WHEN cur_loans%NOTFOUND;

        -- Calculate the new interest rate based on the policy
        IF v_interest_rate <= 5 THEN
            v_new_interest_rate := v_interest_rate + 1;
        ELSE
            v_new_interest_rate := v_interest_rate * 1.05;
        END IF;

        -- Update the loan with the new interest rate
        UPDATE Loans
        SET interest_rate = v_new_interest_rate
        WHERE loan_id = v_loan_id;

        DBMS_OUTPUT.PUT_LINE('Updated Loan ID: ' || v_loan_id || 
                             ', New Interest Rate: ' || v_new_interest_rate);
    END LOOP;

    CLOSE cur_loans;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Loan interest rates updated successfully.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        -- Optionally log error to a table
        -- INSERT INTO error_log (error_message, timestamp) VALUES (SQLERRM, SYSDATE);
END;
