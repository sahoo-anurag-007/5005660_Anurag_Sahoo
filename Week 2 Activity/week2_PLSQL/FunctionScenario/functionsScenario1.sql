CREATE OR REPLACE FUNCTION CalculateAge(
    p_date_of_birth IN DATE
) RETURN NUMBER AS
    v_age NUMBER;
BEGIN
    -- Calculate the age based on the current date and date of birth
    v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_date_of_birth) / 12);

    RETURN v_age;
END CalculateAge;
